countVowelPaliR [] = 0
countVowelPaliR (x:xs) = 
    if reverse x == x 
        then countVowels x + countVowelPaliR xs 
        else countVowelPaliR xs
    where
        countVowels [] = 0
        countVowels (y:ys) = 
            if y `elem` "aeiouy" 
                then 1 + countVowels ys 
                else countVowels ys

countVowelPaliC xs = sum $ map (\x -> 
    if reverse x == x 
        then foldr (\z y -> if z `elem` "aeiouy" then y + 1 else y) 0 x
        else 0) xs

main = do
    print ( countVowelPaliR ["anna", "banana", "civic", "mouse"] )
    print ( countVowelPaliR [] )
    print ( countVowelPaliC ["anna", "banana", "civic", "mouse"] )
    print ( countVowelPaliC ([]::[String]) )