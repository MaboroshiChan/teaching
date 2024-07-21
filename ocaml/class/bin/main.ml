open Printf

let even n = n mod 2 = 0
let x = [3; 7; 2]

let rec fib a b n = if n = 0 then a else fib (a + b) a (n - 1)

let rec fib2 a b n = if n = 0 then a else fib2 (n * a + (n - 1) * b) (a) (n - 1)

let rec gcd a b = if b = 0 then a else gcd(b,a % b)

let rec sum100 acc n = if n = 0 then 1 else sum100 (acc + n) (n - 1)

let max_list list = ""

let apply f n = f n

let y = (fun x -> x + 1) 

let y_2 = y 2
let applied_y = apply y 2

let rec max_list' (list: int list) (cur : int) = match list with
      | [] -> -100
      | x :: xs -> if x > cur then max_list' xs x else max_list' xs cur

let rec fold_left (f, acc, xs) =
  match xs with
  | [] -> acc
  | x :: xs' -> fold_left (f, (f (acc, x)), xs')

module type M = sig
 val func : int -> int
end

module type Hello_type = sig
 val hello : int -> int
end

module Hello : Hello_type = struct
  let hello_value = 12
  let hello x = x + 1
end

module type Hello_type_sub = sig
  include Hello_type
  val hi : int -> int
end 

module Hello_impl: Hello_type_sub = struct
  let hello n = n + 1
  let hi n = n
end

let () = printf "This is my number %d" 12