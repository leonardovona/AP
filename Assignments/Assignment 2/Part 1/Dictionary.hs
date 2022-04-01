{-
Leonardo Vona
545042

Advanced Programming Assignment 2
Exercise 1

dd/mm/2022

Dictionary module.

A Dictionary contains a list of pairs whose first component is the key, and the second
component is the list of elements associated with that key.

All the operations that return a Dictionary ensure that the result is well-formed. A
Dictionary is well-formed iff it does not contain two different pairs (k, vs) and
(k’, vs’) with k = k'.

Note:
  - ( x ) is a name of a parameter
  - [ y ] is a type
-}
module Dictionary
  ( Dictionary,
    empty,
    Dictionary.insert,
    Dictionary.lookup,
    keys,
    values,
    merge,
  )
where

-- Useful functions for Dictionary equality
import Data.List (nub, sort)

--  Concrete Haskell definition of the Dictionary type constructor
data Dictionary a b = Dict [(a, [b])]
  deriving (Show)

-- Constructor of the empty Dictionary
empty :: Dictionary a b
empty = Dict []

{-
Input:
  - ( dict ): Dictionary where to insert the new key-value pair [ Dictionary a b ]
  - ( k ): Key associated with the value to insert [ a ]
  - ( v ): Value to insert into the dictionary [ b ]

Output:
  A dictionary expanding ( dict ) by adding to the list associated with key ( k ) the
  value ( v ) [ Dictionary a b ]

Requires:
  - [ a ] is an instance of the typeclass [ Eq ]
  - The input dictionary ( dict ) is well-formed
-}
insert :: Eq a => Dictionary a b -> a -> b -> Dictionary a b
insert (Dict l) k v = Dict (_insert l k v)
  where
    _insert [] k v = [(k, [v])]
    _insert ((k', l') : ls) k v = if k' == k then (k', v : l') : ls else (k', l') : _insert ls k v

{-
Input:
  - ( dict ): Dictionary where to look the values associated with the key ( k )
    [ Dictionary a b ]
  - ( k ): Key associated with the list of values to retrieve [ a ]

Output:
  The list of values associated with key ( k ) present into dictionary ( dict ) if
  present, Nothing otherwise [ Maybe [b] ]

Requires:
  - [ a ] is an instance of the typeclass [ Eq ]
  - The input dictionary ( dict ) is well-formed
-}
lookup :: Eq a => Dictionary a b -> a -> Maybe [b]
lookup (Dict l) k = Prelude.lookup k l

{-
Input:
  ( dict ): Dictionary from which retrieve the keys [ Dictionary a b ]

Output:
  The list of keys relative to dictionary ( dict ) [ [a] ]

Requires:
  The input dictionary ( dict ) is well-formed
-}
keys :: Dictionary a b -> [a]
keys (Dict []) = []
keys (Dict ((k, l) : xs)) = k : keys (Dict xs)

{-
Input:
  ( dict ): Dictionary from which retrieve the values [ Dictionary a b ]

Output:
  The list of values relative to dictionary ( dict ) [ [b] ]

Requires:
  The input dictionary ( dict ) is well-formed
-}
values :: Dictionary a b -> [b]
values (Dict []) = []
values (Dict ((k, l) : xs)) = l ++ values (Dict xs)

{-
Input:
  - ( dict1 ): Dictionary to merge [ Dictionary a b ]
  - ( dict2 ): Dictionary to merge [ Dictionary a b ]

Output:
  The dictionary obtained by merging the contents of ( dict1 ) and ( dict2 )
  [ Dictionary a b ]

Requires:
  - [ a ] is an instance of the typeclass [ Eq ]
  - The input dictionaries ( dict1 ) and ( dict2 ) are well-formed
-}
merge :: Eq a => Dictionary a b -> Dictionary a b -> Dictionary a b
merge d (Dict l) = case l of
  [] -> d
  (k, []) : ls -> merge d (Dict ls)
  (k, v : vs) : ls -> merge (Dictionary.insert d k v) (Dict ((k, vs) : ls))

{-
Description:

  Function supporting equalValues. Verifies if the lists of values associated to a
  specific key relative to two distinct dictionaries are equal.

Input:
  - ( dict1 ): Dictionary where to retrieve the list of values associated to key ( k ), to
    be compared with the one retrieved from ( dict2 ) [ Dictionary a b ]
  - ( dict2 ): Dictionary where to retrieve the list of values associated to key ( k ), to
    be compared with the one retrieved from ( dict1 ) [ Dictionary a b ]
  - ( k ): Pivot key [ a ]

Output:
  True iff the list of values associated to key ( k ) from dictionary ( dict1 ) is equal
  to the list of values associated to key ( k ) from dictionary ( dict2 ) [ Bool ]

Requires:
  - [ a ] is an instance of the typeclass [ Eq ]
  - [ b ] is an instance of the typeclass [ Ord ]
  - The input dictionary ( dict1 ) is well-formed
  - The input dictionary ( dict2 ) is well-formed
-}
singleKeyEqualValues :: (Eq a, Ord b) => Dictionary a b -> Dictionary a b -> a -> Bool
singleKeyEqualValues d1 d2 k = case (Dictionary.lookup d1 k, Dictionary.lookup d2 k) of
  (Nothing, _) -> False
  (_, Nothing) -> False
  (Just v1, Just v2) -> sort (nub v1) == sort (nub v2)

{-
Description:
  Function supporting dictionary equality. Verifies if, for each key, the lists of values
  from both dictionaries are equal.

Input:
  - ( dict1 ): Dictionary where to retrieve the list of values associated to key ( k ), to
    be compared with the one retrieved from ( dict2 ) [ Dictionary a b ]
  - ( dict2 ): Dictionary where to retrieve the list of values associated to key ( k ), to
    be compared with the one retrieved from ( dict1 ) [ Dictionary a b ]
  - ( k ): List of keys of ( dict1 ) and ( dict2 ) [ [a] ]

Output:
  True iff, for each key from the list of keys ( k ), the associated lists of values from
  dictionaries ( dict1 ) and ( dict2 ) are equal [ Bool ]

Requires:
  - [ a ] is an instance of the typeclass [ Eq ]
  - [ b ] is an instance of the typeclass [ Ord ]
  - The input dictionary ( dict1 ) is well-formed
  - The input dictionary ( dict2 ) is well-formed
  - ( dict1 ) and ( dict 2 ) must share the same list of keys.
-}
equalValues :: (Eq a, Ord b) => Dictionary a b -> Dictionary a b -> [a] -> Bool
equalValues d1 d2 ks = case ks of
  [] -> True
  k : ks' -> singleKeyEqualValues d1 d2 k && equalValues d1 d2 ks'

{-
Returns true iff both dictionary have the same key list and, for each key, the lists of
associated values from both dictionaries are equal. Two lists of values are equal iff they
contain the same elements, disregarding possibles repetitions.
-}
instance (Ord a, Ord b) => Eq (Dictionary a b) where
  d1 == d2 = sort (keys d1) == sort (keys d2) && equalValues d1 d2 (sort (keys d1))