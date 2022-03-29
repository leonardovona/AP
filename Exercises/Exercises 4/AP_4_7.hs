data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a)

eval :: Num a => Expr a -> a
eval (Const x) = x
eval (Sum x y) = eval x + eval y
eval (Mul x y) = eval x * eval y

evalPrint :: (Num a, Show a) => Expr a -> IO ()
evalPrint e = print (eval e)

evalPrintSub :: (Show a, Num a) => Expr a -> IO a
evalPrintSub (Const x) = do
    print x
    return x

evalPrintSub (Sum x y) = do
    s1 <- evalPrintSub x
    s2 <- evalPrintSub y
    print (s1 + s2)
    return (s1 + s2)

evalPrintSub (Mul x y) = do
    s1 <- evalPrintSub x
    s2 <- evalPrintSub y
    print (s1 * s2)
    return (s1 * s2)

main = do
    evalPrint (Sum (Mul (Const 2) (Const 3)) (Const 4))
    evalPrintSub (Sum (Mul (Const 2) (Const 3)) (Const 4))