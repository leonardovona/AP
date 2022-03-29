data Expr a = Const a | Sum (Expr a) (Expr a) | Mul (Expr a) (Expr a)


toList (Const x) = "Const (" ++ show x ++ ")"
toList (Sum a b) = "Sum ("++ toList a ++ ", " ++ toList b ++ ")"
toList (Mul a b) = "Mul (" ++ toList a ++ ", " ++ toList b ++ ")"

class Functor f where
    fmap :: (a -> b) -> f a -> f b

instance Main.Functor Expr where
    fmap f (Const a) = Const (f a)
    fmap f (Sum a b) = Sum (Main.fmap f a) (Main.fmap f b)
    fmap f (Mul a b) = Mul (Main.fmap f a) (Main.fmap f b)

main = do
    print ( toList (Main.fmap (+1) (Sum (Mul (Const 2) (Const 3)) (Const 4))) ) 