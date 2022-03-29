myMap :: (a -> b) -> [a] -> [b]
myMap f = foldl (\ys x -> ys ++ [f x]) []

main = do
    print (myMap (3*) [1,2,3,4])