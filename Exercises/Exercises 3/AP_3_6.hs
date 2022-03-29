import Data.Char (toUpper)

titlecaseR s = unwords (f (words s)) where
    f [] = []
    f (w:ws) = (toUpper (head w) : tail w) : f ws

titlecaseC :: String -> String
titlecaseC s = unwords $ map (\x -> toUpper(head x): tail x) $ words s

main = do
    print (titlecaseC "ciao mondo come stai")