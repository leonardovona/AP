module AP_3_1 (myReplicateR, myReplicateC) where
myReplicateR n v
    | n > 0     = v:myReplicateR (n - 1) v
    | otherwise = []

myReplicateC = replicate

main = do
    print (myReplicateR 2 5)
    print (myReplicateR 5 "hello")
    print (myReplicateC 2 5)
    print (myReplicateC 5 "hello")