# 3c

A->B = -A V B

-| (A->B) V (B->A)
-(A->B) -> (B->A)
Assume: -(A->B)
-(-A v B)
(A ^ -B)
    Assume B
    A                           And_Elem 6
    end
(B->A) 9 conditional proof
end conditional proof

(a)
(A->B) v (A->C) |- A->(BvC)
Assume A
    case (A->B):
        B                       MP 17 18 
        (B v C)                 DI-Left 19
    case (A->C):
        C                       MP 17 21
        (B v C)                 DI-Right 22
end conditional proof & Disjunctive elimination.

(b)
(A->B) v (A->C) |- A->(BvC)
Assume A:
    Assume -B:
        A ^ -B              CI 29 30
        -(-(A ^ -B))        NG 31
        -(-A v B)           DM 32
        -(A->B)             MI 33
        (A->C)              DI 34 28
        C                   MP 35 29
    end
    -B->C                   CP 30-37
    (--B)vC                 MI 38
    BvC                     DN 39
end
7
⊢ ∀𝑥∀𝑦∀𝑧(¬(𝑥 = 𝑦) → ¬(𝑧 = 𝑥 ∧ 𝑧 = 𝑦))
Goal:
∀𝑦∀𝑧(¬𝑥 = 𝑦 → ¬(𝑧 = 𝑥 ∧ 𝑧 = 𝑦))     UE 44
∀𝑧(¬𝑥 = 𝑦 → ¬(𝑧 = 𝑥 ∧ 𝑧 = 𝑦))       UE 45
(¬𝑥 = 𝑦 → ¬(𝑧 = 𝑥 ∧ 𝑧 = 𝑦))         UE 46
(𝑧 = 𝑥 ∧ 𝑧 = 𝑦) -> (𝑥 = 𝑦)          Trans 49

*Proof*
Assume (𝑧 = 𝑥 ∧ 𝑧 = 𝑦):
    z=x                                CI 51 
    z=y                                CI 51    
    x=y                                SUB 52 53
end
𝑧 = 𝑥 ∧ 𝑧 = 𝑦 -> x=y                    CP 51-56
-(x=y) -> -(𝑧 = 𝑥 ∧ 𝑧 = 𝑦)              Trans 56
∀x∀y∀z(¬𝑥 = 𝑦 → ¬(𝑧 = 𝑥 ∧ 𝑧 = 𝑦))       UI UI UI 57

(b)
∀𝑥∃𝑦(𝑅𝑥𝑦 ∨ 𝑅𝑦𝑥) ⊢ ∀𝑥(¬𝑅𝑥𝑥 → ∃𝑧(¬𝑥 = 𝑧))
*Proof*
    Assume ∀𝑧(𝑥 = 𝑧)
        ∃𝑦(𝑅𝑥𝑦 ∨ 𝑅𝑦𝑥)         UE 61
        (𝑅𝑥𝑦 ∨ 𝑅𝑦𝑥)           EE 64
        x = y                UE 63 
        Rxx v Rxx            SUB 66 65
        Rxx                  
    end CP 63-69
    ∀𝑧(𝑥 = 𝑧) -> Rxx
    ∀𝑥(∀𝑧(𝑥 = 𝑧) -> Rxx)
    ∀𝑥(-Rxx->-∀𝑧(𝑥 = 𝑧))
    ∀𝑥(-Rxx->∃𝑧-(𝑥 = 𝑧))