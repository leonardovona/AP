
totalLengthR [] = 0
totalLengthR (x:xs) = if head x == 'A' then length x + totalLengthR xs else totalLengthR xs

totalLengthC xs = sum (map (\x -> if head x == 'A' then length x else 0) xs)

main = do
    print( totalLengthR ["Ciao", "Mondo", "Ancora", "Avventura"])
    print( totalLengthR [])
    print( totalLengthR ["Ciao", "Mondo"])
    print( totalLengthR ["Adesso", "Avanti", "Ancora", "Avventura"])
    print( totalLengthC ["Ciao", "Mondo", "Ancora", "Avventura"])
    print( totalLengthC ([]::[[Char]]))
    print( totalLengthC ["Ciao", "Mondo"])
    print( totalLengthC ["Adesso", "Avanti", "Ancora", "Avventura"])