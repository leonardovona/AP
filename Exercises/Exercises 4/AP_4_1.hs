data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a)

eval :: Num a => Expr a -> a
eval (Const x) = x
eval (Sum x y) = eval x + eval y
eval (Mul x y) = eval x * eval y

main = do
    print ( eval (Sum (Mul (Const 2) (Const 3)) (Const 4)) )