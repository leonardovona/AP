data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a) | Div (Expr a) (Expr a)

safeEval :: Integral a => Expr a -> Maybe a
safeEval (Const x) = Just x
safeEval (Sum x y) = do
    x' <- safeEval x 
    y' <- safeEval y
    return (x' + y')
safeEval (Mul x y) = do
    x' <- safeEval x 
    y' <- safeEval y
    return (x' * y')
safeEval (Div x y) = case safeEval x of
    Nothing -> Nothing
    Just x' -> case safeEval y of
        Nothing  -> Nothing
        Just y' -> if y' == 0 then Nothing else Just (div x' y')


main = do
    print ( safeEval (Div (Mul (Const 5) (Const 3)) (Const 4)) )
    print ( safeEval (Div (Mul (Const 5) (Const 3)) (Const 0)) )