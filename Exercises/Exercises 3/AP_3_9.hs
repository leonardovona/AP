data IntTree = Leaf Int | Node (Int, IntTree, IntTree)

toList :: IntTree -> [Int]
toList (Leaf x) = [x]
toList (Node (x, t1, t2)) = [x] ++ toList t1 ++toList t2

tmap :: (Int -> Int) -> IntTree -> IntTree
tmap f (Leaf x) = Leaf (f x)
tmap f (Node (x, t1, t2)) = Node (f x, tmap f t1, tmap f t2)

succTree = tmap (+1)

sumSucc (Leaf x) = x
sumSucc (Node (x, t1, t2)) = x + sumSucc t1 + sumSucc t2
main = do
    print "Original tree"
    print [4,3,1,2,5,6,7]
    print "tmap"
    print (toList $ tmap (2*) (Node(4, Node(3, Leaf 1, Leaf 2), Node(5, Leaf 6, Leaf 7))))
    print "succTree"
    print (toList $ succTree (Node(4, Node(3, Leaf 1, Leaf 2), Node(5, Leaf 6, Leaf 7))))
    print "sumSucc"
    print (sumSucc (Node(4, Node(3, Leaf 1, Leaf 2), Node(5, Leaf 6, Leaf 7))))