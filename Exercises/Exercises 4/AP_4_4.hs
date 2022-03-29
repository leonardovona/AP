data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a)

instance Foldable Expr where
   foldr f z (Const x) = f x z
   foldr f z (Sum a b) = foldr f (foldr f z b) a
   foldr f z (Mul a b) = foldr f (foldr f z b) a

main = do
    print (foldr (+) 0 (Sum (Mul (Const 2) (Const 3)) (Const 4)))