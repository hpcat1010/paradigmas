module Test (test)
 where


import Container
-- import Stack
import Route

-- test :: [Bool]
test = [ ("test de destination"     , destinationC (newC "aire" 2) == "aire")
        ,("test de net weight C"    , netC (newC "aire" 2) == 2) 
        ,("test de inOrderR ab - ab", inOrderR  (newR ["a", "b" ]) "a" "b" == True)
        ,("test de inOrderR ab - cb", inOrderR  (newR ["a", "b" ]) "c" "b" /= True)
        ,("test de inOrderR ab - ba", inOrderR  (newR ["a", "b" ]) "b" "a" /= True)

        ,("nothing", True)]


