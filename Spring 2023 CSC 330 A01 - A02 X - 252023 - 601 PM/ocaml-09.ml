(*#utop_prompt_dummy
  let _ = UTop.set_show_box false *)

(************************************************************************)
(* Currying *)

let sorted3_tuple (x, y, z) =
  x <= y && y <= z
let _ = sorted3_tuple (7, 9, 11)

let sorted3 = fun x -> (fun y -> (fun z ->
  x <= y && y <= z))
let _ = ((sorted3 7) 9) 11
let _ = sorted3 7 9 11

let sorted3_spaces_fun = fun x y z ->
  x <= y && y <= z
let _ = sorted3_spaces_fun 7 9 11

let sorted3_spaces x y z =
  x <= y && y <= z
let _ = sorted3_spaces 7 9 11

let is_positive = sorted3_spaces 0 0

(* fold again, but now curried *)
(* this exact definition is in the stdlib at List.fold_left *)
let rec fold_left f acc xs =
  match xs with
  | [] -> acc
  | x :: xs' -> fold_left f (f acc x) xs'

let sum_meh_style xs =
  (fold_left (fun acc x -> acc + x) 0) xs

let sum_good_style =
  fold_left (fun acc x -> acc + x) 0

let remove_negs_meh xs = List.filter (fun x -> x >= 0) xs

(* partial application and currying allows us to use "variable-style" let *)
let remove_negs = List.filter (fun x -> x >= 0)

let remove_all n = List.filter (fun x -> x <> n)
let remove_zeros = remove_all 0

let increment_all = List.map (fun x -> x + 1)

(* in the library as List.exists *)
let rec exists p xs =
  match xs with
  | [] -> false
  | x :: xs' -> p x || exists p xs'

let exists p xs = List.fold_left (fun acc x -> acc || p x) false xs
let exists p = List.fold_left (fun acc x -> acc || p x) false

(* in the library as List.for_all *)
let forall p = List.fold_left (fun acc x -> acc && p x) true

let has_zero = exists (fun x -> x = 0)
let no_zeros = forall (fun x -> x <> 0)

(* currying is the norm in OCaml, even if partial application is unlikely *)
let rec append xs ys =
  match xs with
  | [] -> ys
  | x::xs' -> x :: append xs' ys

;;





(******** Value restriction *********)

let remove_empty_lists = List.filter (fun x -> List.length x > 0)
(* look at the type, it's not 'a list list -> 'a list list as we would expect *)

let _ = remove_empty_lists [ []; [true] ]
(* *now* look at the type!! *)

(* let _ = remove_empty_lists [ []; [1] ] *)
(* oh no - that didn't work! *)

;;



(* workaround if you want polymorphics: go back to "bad" style :\ *)
let remove_empty_lists xs = List.filter (fun x -> List.length x > 0) xs
let _ = remove_empty_lists [ []; [1] ]
let _ = remove_empty_lists [ []; [true] ]

;;






(************************************************************************)
(* Composition *)

(* function composition (right-to-left, like math) *)
let compose =
  fun f -> fun g -> (fun x -> f (g x))
let compose_shorter f g x = f (g x)

(* infix notation for composition *)
let (%) = compose

(* unnecessary function wrapping again here: *)
let double_plus_one x = compose (fun x -> x + 1) (fun x -> 2 * x) x
let r = double_plus_one 3

(* "variable-style" let binding is better style *)
let double_plus_one = compose (fun x -> x + 1) (fun x -> 2 * x)
let r = double_plus_one 3

(* also nice to use the infix operator *)
let double_plus_one = (fun x -> x + 1) % (fun x -> 2 * x)
let r = double_plus_one 3

;;



let sqrt_of_abs i = Float.sqrt (float_of_int (Int.abs i))
let sqrt_of_abs i = (Float.sqrt % float_of_int % Int.abs) i
let sqrt_of_abs = Float.sqrt % float_of_int % Int.abs
let r = sqrt_of_abs (-8)

(* pipeline operator (left-to-right) *)
(* actually built-in to OCaml, but here's the definition *)
let (|>) x f = f x

let sqrt_of_abs i =
  i |> Int.abs
    |> float_of_int
    |> Float.sqrt
let r = sqrt_of_abs (-8)

;;


(* also left-to-right *)
let pipeline_option f g =
  fun x ->
    match f x with
    | None -> None
    | Some y -> g y

let sqrt_if_positive =
  pipeline_option (fun i -> if i > 0 then Some (float_of_int i) else None)
                  ((fun x -> Some x) % sqrt)

let r = sqrt_if_positive 3
let r = sqrt_if_positive (-3)

;;


(* converting between curried and tupled versions of a function *)
let curried_of_paired f x y = f (x, y)
let paired_of_curried f (x, y) = f x y

(* more verbose but less "tricky" versions of the above: *)
(*
let curried_of_paired f = fun x -> fun y -> f (x, y)
let paired_of_curried f = fun (x, y) -> (f x) y
*)

(* check out how much the types tell us above and here: *)
let swap_tupled f (x, y) = f (y, x)
let swap_curried f x y = f y x

let rec range (lo, hi) =
  if lo > hi
  then []
  else lo :: range (lo + 1, hi)

(* no problem using range as curried even though it's tupled --
   convert it
*)
let countup = (curried_of_paired range) 0
let _ = countup 5

;;





(************************************************************************)
(* References and callbacks *)

let x = ref 42
let y = ref 42
let z = x
let _ = x := 43
let w = !x + !z
(* does not typecheck: *)
(* let _ = x + 1 *)

;;






(* Callback library *)
let callbacks : (int -> unit) list ref = ref []

let on_key_event f = (* only library binding that clients should use *)
  callbacks := f :: !callbacks

let rec iter f xs = (* like map, but produces no data.  (In the library as List.iter) *)
  match xs with
  | [] -> ()
  | x :: xs' -> let _ = f x in iter f xs'

let do_key_event i =
  List.iter (fun f -> f i) !callbacks
(* end of callback library implementation *)

;;


(* Callback client *)
let times_pressed = ref 0

(* actually built-in, but here's the definition *)
let incr int_ref =
  int_ref := !int_ref + 1

let () = on_key_event (fun _ -> incr times_pressed)

let r = !times_pressed (* callback has not been called yet *)

;;


(* imagine these happened from "the outside world" *)
let () = do_key_event 97
let () = do_key_event 98
let () = do_key_event 99

(* we can see our callback was called *)
let r = !times_pressed

;;


let print_if_pressed i =
  on_key_event (fun j ->
      if i = j then print_endline ("pressed " ^ string_of_int i) else ())

let () = do_key_event 97
let () = print_if_pressed 122
let () = do_key_event 98
let () = do_key_event 122
let _ = !times_pressed

;;





(************************************************************************)
(* References and callbacks *)
type set =
  S of { insert : int -> set; member : int -> bool; size : unit -> int; }

let empty_set =
  let rec make_set xs = (* xs is a "private field" in result *)
    let contains i = List.mem i xs in (* contains is a "private method" *)
    S { insert = (fun i -> if contains i then make_set xs else make_set (i :: xs))
      ; member = contains
      ; size   = (fun () -> List.length xs) }
  in
  make_set []

(* client *)
let sz =
  let S s1 = empty_set in
  let S s2 = s1.insert 34 in
  let S s3 = s2.insert 34 in
  let S s4 = s3.insert 19 in
  s4.size ()
