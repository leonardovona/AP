{-
Leonardo Vona
545042

Advanced Programming Assignment 2
Exercise 2

dd/mm/2022

TestDict

Tests the Dictionary module.

Note:
  - ( x ) is a name of a parameter
  - [ y ] is a type
-}
import Data.Char ()
import Data.List (sort)
import Dictionary

-- Returns the ciao of a String.
ciao :: String -> String
ciao = sort

{-
Input:
  ( fn ): Filename of the text file containing a list of words, one per line [ FilePath ]

Output:
  A dictionary containing the words of the input file ( fn ) as values, using for each
  word its ciao as key [ IO (Dictionary String String) ]

Requires:
  ( fn ) is an existing text file containing a single word for each line
-}
readDict :: FilePath -> IO (Dictionary String String)
readDict fn = do
  contents <- readFile fn
  {-
    1) The content of the file is split in lines
    2) Incrementally, each line (which represents a word), is inserted to the dictionary
       using as key its ciao
  -}
  let d = foldr (\x y -> Dictionary.insert y (ciao x) x) empty (lines contents)
  return d

{-
Input:
  - ( dict ): Dictionary from which retrieve the keys and the length of the list of
    associated values [ Dictionary String String ]
  - ( fn ): Filename of the file where the data elaborated from ( dict ) is 
    written [ FilePath ]

Requires:
  TestDict has the permission to write into ( fn )

Effects:
  ( fn ) will contain, one per line, each key of the dictionary ( dict ) and the length of
  the list of associated values, separated by a space
-}
writeDict :: Dictionary String String -> FilePath -> IO ()
writeDict d fn = do
  let k = keys d
  {- 
    For each key of the dictionary a pair <key, length of the list of associated values>
    is created 
  -}
  let z = zip k (map (maybe 0 length . Dictionary.lookup d) k)
  {-
    1) Each pair is first turned into a string like "{key} {length of values list}"
    2) Then strings will be in turn reduced to a single string containing one pair per
       line
    3) Finally the resulting string is written to the file
  -}
  writeFile fn (foldr ((\x y -> x ++ "\n" ++ y) . (\(x, y) -> x ++ " " ++ show y)) "" z)

main :: IO ()
main = do
  -- Loads the dictionaries d1, d2, d3, d4
  d1 <- readDict "aux_files/anagram.txt"
  d2 <- readDict "aux_files/anagram-s1.txt"
  d3 <- readDict "aux_files/anagram-s2.txt"
  d4 <- readDict "aux_files/margana2.txt"
  -- Checks that d1 and d4 have the same keys
  print ("keys d1 == keys d4 ? " ++ show (keys d1 == keys d4))
  -- Checks that d1 and d4 are not equal
  print ("d1 == d4 ? " ++ show (d1 == d4))
  -- Checks that d1 is equal to the merge of d1 and d4
  print ("d1 == merge d2 d3 ? " ++ show(d1 == merge d2 d3))
  -- Writes d1 and d4 to anag-out.txt and gana-out.txt, respectively
  writeDict d1 "aux_files/anag-out.txt"
  writeDict d4 "aux_files/gana-out.txt"