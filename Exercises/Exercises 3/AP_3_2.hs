sumOddR [] = 0
sumOddR (l:ls) =  if odd l then l + sumOddR ls else sumOddR ls

sumOddC :: Integral p => [p] -> p
sumOddC [] = 0
sumOddC (l:ls) = sum (filter odd (l:ls))

main = do
    print (sumOddR [1,2,3,4,5,6,7])
    print (sumOddR [])
    print (sumOddC [1,2,3,4,5,6,7])
    print (sumOddC [])