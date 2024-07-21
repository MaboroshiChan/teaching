(*#utop_prompt_dummy
  let _ = UTop.set_show_box false *)

(************************************************************************)
(* First-class functions *)
let double x = x * 2
let incr x = x + 1
let funcs = [ double; incr ]

let rec apply_funcs (fs, x) =
  match fs with
  | [] -> x
  | f :: fs' -> apply_funcs (fs', f x)

(* binds foo to 201 *)
let foo = apply_funcs (funcs, 100)

(* binds bar to 202 *)
let bar = apply_funcs (List.rev funcs, 100)

;;


(* it should really bother us to write these functions separately *)
let rec increment_n_times_bothersome (n, x) =
  if n = 0 then x else 1 + increment_n_times_bothersome (n - 1, x)

let rec double_n_times_bothersome (n, x) =
  if n = 0 then x else 2 * double_n_times_bothersome (n - 1, x)

let rec tail_n_times_bothersome (n, xs) =
  if n = 0 then xs else List.tl (tail_n_times_bothersome (n - 1, xs))

;;




(* much better: abstract the common pieces into a function *)
let rec n_times (f, n, x) =
  if n = 0 then x else f (n_times (f, n - 1, x))

(* we can now implement the 3 bothersome functions above in one line each *)
let increment_n_times (n, x) = n_times (incr, n, x)
let double_n_times (n, x) = n_times (double, n, x)
let tail_n_times (n, xs) = n_times (List.tl, n, xs)

(* and nothing stops us from using n_times in places we didn't originally plan *)
let triple x = 3 * x
let triple_n_times (n, x) = n_times (triple, n, x)

(* polymorphic but not higher order *)
let rec len xs =
  match xs with
  | [] -> 0
  | _ :: xs' -> 1 + len xs'

let r = len [ 1; 2 ]
let r = len [ "a"; "b" ]

(* higher order but not polymorphic *)
let rec times_until_zero (f, x) =
  if x = 0 then 0 else 1 + times_until_zero (f, f x) ;;

let f x = x - 2 in times_until_zero (f, 10)
(* This fails:
let f x = x ^ "1"
let r = times_until_zero (f, 10) *)

;;





(************************************************************************)
(* Anonymous functions *)

(* two very useful and very common higher-order functions *)
let rec map (f, xs) =
  match xs with [] -> [] | x :: xs' -> f x :: map (f, xs')

let rec filter (f, xs) =
  match xs with
  | [] -> []
  | x :: xs' ->
    if f x then x :: filter (f, xs') else filter (f, xs')

(* motivating anonymous functions *)
(* if we just need is_even for only_evens, better to use local function *)
let only_evens2 xs =
  let is_even x = x mod 2 = 0 in
  filter (is_even, xs)

(* actually, we could define it *right* where we need it *)
let only_evens3 xs =
  filter ((let is_even x = x mod 2 = 0 in is_even), xs)

(* seems kind of silly to have a let expression whose body is the variable...
   can we do better? *)

(* not like this. a let binding is not an expression! *)
(* let only_evens4 xs = filter ((let is_even x = x mod 2 = 0), xs) *)
(* NOTE: let != let in! *)

(* but an anonymous function is an expression! *)
let only_evens5 xs = filter ((fun x -> x mod 2 = 0), xs)

(* bad style: the 'if e then true else false' of functions *)
(* "unnecessary function wrapping" *)
let tail_n_times_bad_style (n, xs) =
  n_times ((fun ys -> List.tl ys), n, xs)

(* good style *)
let tail_n_times_good_style (n, xs) =
  n_times (List.tl, n, xs)

;;


(* returning functions: look at the types! *)
let double_or_triple f =
  if f 7 then fun x -> 2 * x else fun x -> 3 * x
let roundabout_double = double_or_triple (fun x -> x - 3 = 4)
let roundabout_nine = (double_or_triple (fun x -> x = 42)) 3

;;


(* higher-order functions over our own recursive variant types *)
type exp =
  | Const of int
  | Negate of exp
  | Add of exp * exp
  | Multiply of exp * exp

let rec true_of_all_constants (f, e) =
  match e with
  | Const n -> f n
  | Negate e' -> true_of_all_constants (f, e')
  | Add (e1, e2) ->
      true_of_all_constants (f, e1) && true_of_all_constants (f, e2)
  | Multiply (e1, e2) ->
      true_of_all_constants (f, e1) && true_of_all_constants (f, e2)

let all_even_constants e =
  true_of_all_constants ((fun x -> x mod 2 = 0), e)
