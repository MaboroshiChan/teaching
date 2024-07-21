(*#utop_prompt_dummy
  let _ = UTop.set_show_box false *)

(************************************************************************)
(* Lexical scope *)

let x = 1
let f y = x + y
let x = 2
let y = 3
let z = f (x + y)

let x = 1
let f y =
  let x = y + 1 in
  fun q -> x + y + q
let x = 3
let g = f 4
let y = 5
let z = g 6

let f g =
  let x = 3 in
  g 2
let x = 4
let h y = x + y
let z = f h

;;





(* Lexical scope examples *)

(* f1 and f2 are always the same, no matter where used *)
let f1 y =
  let x = y + 1 in
  fun z -> x + y + z

let f2 y =
  let q = y + 1 in
  fun z -> q + y + z

let h1 = f1 7
let h2 = f2 7
let x = 17  (* irrelevant *)
let a1 = h1 4
let a2 = h2 4

;;


(* f3 and f4 are always the same, no matter what argument is passed in *)
let f3 g =
  let x = 3 in  (* irrelevant *)
  g 2

let f4 g =
  g 2

let x = 17
let a3 = f3 (fun y -> x + y)
let a4 = f4 (fun y -> x + y)
let a5 = f3 (fun y -> 17 + y)
let a6 = f4 (fun y -> 17 + y)

;;


(* under dynamic scope, the call to g below would:
    - try to add a string x to an int
    - have an unbound variable y
   even though g type checked! *)
let x = "hi"
let g = f1 7
let z = g 4

;;


(* lexical scope examples to leverage the rules *)
(* lexical scope very useful with higher-order functions *)
(* our first example of "currying"; will study and use
   more soon *)
let rec filter f =
  fun xs ->
    match xs with
    | [] -> []
    | x :: xs' -> if f x then x :: ((filter f) xs') else ((filter f) xs')

let greater_than x =
  fun y -> x < y

let is_positive =
  greater_than 0

let only_positives =
  filter is_positive

let all_greater (xs, n) =
  (filter (greater_than n)) xs

(* let's start using that e1 e2 e3 is (e1 e2) e3 *)
let all_shorter (xs, s) =
  filter (fun x -> String.length x < String.length s) xs

let all_shorter' (xs, s) = (* why is this potentially faster? *)
  let n = String.length s in
  filter (fun x -> String.length x < n) xs

let r = all_shorter (["a"; "bb"; "ccc"; "dddd"], "eee")
let r = all_shorter' (["a"; "bb"; "ccc"; "dddd"], "eee")

;;







let rec fold_left (f, acc, xs) =
  match xs with
  | [] -> acc
  | x :: xs' -> fold_left (f, (f (acc, x)), xs')

(* examples that do not capture private data *)
let sum xs =
  fold_left ((fun (acc, x) -> acc + x), 0, xs)

let only_positives xs =
  fold_left ((fun (acc, x) -> acc && x >= 0), true, xs)

(* examples that *do* capture private data *)
let count_in_range (lo, hi, xs) =
  fold_left
    ((fun (acc, x) -> acc + (if lo <= x && x < hi then 1 else 0)),
     0,
     xs)

let are_all_shorter (xs, s) =
  let n = String.length s in
  fold_left ((fun (acc, x) -> acc && String.length x < n), true, xs)

let forall (f, xs) =
  fold_left ((fun (acc, x) -> acc && f x), true, xs)

let only_positives' xs =
  forall ((fun x -> x > 0), xs)

let are_all_shorter' (xs, s) =
  let n = String.length s in
  forall ((fun x -> String.length x < n), xs)
