module Test (test)
 where


import Container
import Stack
import Route
import Vessel

testroute = newR ["a", "b" ]
container1 = newC "aire" 2
container2 = newC "bueno" 3

stack1Empty = newS 1
stack1Full = stackS stack1Empty container1

stack2Empty = newS 1
stack2Full = stackS stack2Empty container2




-- test :: [Bool]
test = [ ( "test de destination"     , destinationC (container1) == "aire")
        ,( "test de net weight C"    , netC (container1) == 2) 
        ,( "test de inOrderR ab - ab", inOrderR  (testroute) "a" "b" == True)
        ,( "test de inOrderR ab - cb", inOrderR  (testroute) "c" "b" /= True)
        ,( "test de inOrderR ab - ba", inOrderR  (testroute) "b" "a" /= True)
        ,( "test de freeCellsS stack1Empty", freeCellsS stack1Empty == 1)
        ,( "test de freeCellsS stack1Full", freeCellsS stack1Full == 0)
        ,( "test de freeCellsS falso", freeCellsS stack1Empty /= 2)
        ,( "test de netS stack1Empty", netS stack1Empty == 0)
        ,( "test de netS stack1Full", netS stack1Full == 2)
        ,( "test de holdsS stack1Empty container1 testroute", holdsS stack1Empty container1 testroute == True) -- hay algo raro en holdsS, no se si es el test o el codigo -__-
        ,( "test de holdsS stack1Full container1 testroute", holdsS stack1Full container1 testroute == False)
        ,( "test de popS stack1Full aire", popS stack1Full "aire" == stack1Empty) -- funciona perfecto, soy muy bueno 




        ,("nothing", True)]


testVes :: [(String, Bool)]
testVes = [ ( "test de freeCellsV Ves stack1Empty testroute", freeCellsV (Ves [stack1Empty] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full] testroute) == 0)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full] testroute) == 0)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Empty] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full, stack1Full] testroute) == 0)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full, stack1Empty] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full, stack1Full, stack1Full] testroute) == 0)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full, stack1Full, stack1Empty] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full, stack1Empty, stack1Full] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Empty, stack1Full, stack1Full] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Empty, stack1Full, stack1Full, stack1Full] testroute) == 1)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Full, stack1Empty, stack1Empty] testroute) == 2)
          ,( "test de freeCellsV Ves stack1Full testroute", freeCellsV (Ves [stack1Full, stack1Empty, stack1Full, stack1Empty] testroute) == 2)
          , True ]