data Var = X | Y | Z
data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a) | Id Var

instance Show Var where
    show X = "X"
    show Y = "Y"
    show Z = "Z"

toList (Const x) = "Const (" ++ show x ++ ")"
toList (Sum a b) = "Sum ("++ toList a ++ ", " ++ toList b ++ ")"
toList (Mul a b) = "Mul (" ++ toList a ++ ", " ++ toList b ++ ")"
toList (Id a) = "ID (" ++ show a ++ ")"

subst::(Expr a, Expr a, Expr a) -> Expr a -> Expr a
subst (x, y, z) (Const a) = Const a
subst (x, y, z) (Sum a b) = Sum (subst (x, y, z) a) (subst (x, y, z) b)
subst (x, y, z) (Mul a b) = Mul (subst (x, y, z) a) (subst (x, y, z) b)
subst (x, y, z) (Id a) = case a of
    X -> x
    Y -> y
    Z -> z 

eval :: Num a => Expr a -> Maybe a
eval (Const x) = Just x
eval (Sum x y) = case eval x of
    Nothing -> Nothing 
    Just x' -> case eval y of
        Nothing -> Nothing 
        Just y' -> Just (x' + y')
eval (Mul x y) = case eval x of
    Nothing -> Nothing 
    Just x' -> case eval y of
        Nothing -> Nothing 
        Just y' -> Just (x' * y')
eval (Id a) = Nothing


recEval :: Num a => (Expr a, Expr a, Expr a) -> Expr a -> a
recEval _ (Const a) = a
recEval (x, y, z) (Sum a b) = recEval (x, y, z) a + recEval (x, y, z) b
recEval (x, y, z) (Mul a b) = recEval (x, y, z) a * recEval (x, y, z) b
recEval (x, y, z) e = recEval (x, y, z) $ subst (x, y, z) e

main = do
    print ( toList (subst (Const 11, Const 12, Const 13) (Sum (Mul (Const 2) (Const 3)) (Id Y))))
    print ( eval (Sum (Mul (Const 2) (Const 3)) (Id Y)))
    print ( recEval (Const 11, Const 12, Const 13) (Sum (Mul (Const 2) (Const 3)) (Id Y)))
    print ( (eval . subst (Const 11, Const 12, Const 13)) (Sum (Mul (Const 2) (Const 3)) (Id Y)))
    print ( recEval (Const 11, Const 12, Const 13) (Sum (Mul (Const 2) (Const 3)) (Id Y)))
    