filterOddR [] = []
filterOddR (x:xs) = f (x:xs) True where 
    f [] _ = []
    f (x:xs) b = if b then x:f xs (not b) else f xs (not b)

filterOddC xs = [i | (i, j) <- zip xs [1..], odd j]

main = do
    print( filterOddR [1..20] )
    print( filterOddR ['a'..'z'] )
    print( filterOddR ([]::[Int]) )
    print( filterOddC [1..20] )
    print( filterOddC ['a'..'z'] )
    print( filterOddC ([]::[Int]) )