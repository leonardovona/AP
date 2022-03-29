data Var = X | Y | Z
data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a) | Id Var

instance Show Var where
    show X = "X"
    show Y = "Y"
    show Z = "Z"


instance Show a => Show (Expr a) where
    show (Const a) = "Const (" ++ show a ++ ")"
    show (Sum a b) = "Sum ("++ show a ++ ", " ++ show b ++ ")"
    show (Mul a b) = "Mul (" ++ show a ++ ", " ++ show b ++ ")"
    show (Id a) = "ID (" ++ show a ++ ")"

main = do
    print (Sum (Mul (Const 2) (Const 3)) (Id Y))