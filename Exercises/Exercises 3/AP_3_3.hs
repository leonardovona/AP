import AP_3_1 as A

replR [] _ = []
replR (x:xs) n = myReplicateR n x ++ replR xs n

replC xs n = concatMap (myReplicateC n) xs

main = do
    print (replR [1,2,3,4,5,6,7] 3)
    print (replR ([]::[Int]) 3)
    print (replR [1,2,3,4,5,6,7] 0)
    print (replC [1,2,3,4,5,6,7] 3)
    print (replC ([]::[Int]) 3)
    print (replC [1,2,3,4,5,6,7] 0)