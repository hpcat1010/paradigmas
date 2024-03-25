module Test (test)
 where

import Container
import Stack
import Route
import Vessel
                          -- ejecutar "test_de_tests" para correr todo junto. 
testroute1 = newR ["a", "b" ]
testroute2 = newR ["bueno", "aire"]
container1 = newC "aire" 2
container2 = newC "bueno" 3
containerPesado = newC "aire" 19
stack1Empty = newS 1
stack1Full = stackS stack1Empty container1
stack2Empty = newS 1
stack2Full = stackS stack2Empty container2
stack3Empty = newS 3
stack3SemiFull = stackS stack3Empty container1
stackpesado = stackS stack3Empty containerPesado
testVessel = newV 2 1 testroute1
testVessel2 = newV 2 1 testroute2
testVessel2x2 = newV 2 2 testroute2
vesselcargado2x2 = loadV testVessel2x2 container1
vesselcargado2x2Stack1Lleno = loadV vesselcargado2x2 container1
unloadvesselcargado2x2 = unloadV vesselcargado2x2 "aire"
vesselCargado = loadV testVessel2 container1
fullvessel = loadV vesselCargado container1
testVesselUnloaded = unloadV vesselCargado "aire"

------------------
testroute3 = newR ["a", "b", "c"]
containerA = newC "a" 1
containerB = newC "b" 2
containerC = newC "c" 3
containerPesadoA = newC "a" 19
containerPesadoB = newC "b" 19
megaVesselEmpty = newV 3 3 testroute3
megaVessel1 = loadV megaVesselEmpty containerA
megaVessel2 = loadV megaVessel1 containerB
megaVessel3 = loadV megaVessel2 containerC
megaVessel4 = unloadV megaVessel3 "a"
megaVessel5 = loadV megaVessel4 containerA
megaVessel6 = loadV megaVessel5 containerPesadoA
megaVessel7 = loadV megaVessel6 containerPesadoB

megatest =  [("megaVesselEmpty", show megaVesselEmpty == "Ves [Sta [] 3,Sta [] 3,Sta [] 3] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel1", show megaVessel1 == "Ves [Sta [Con \"a\" 1] 2,Sta [] 3,Sta [] 3] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel2", show megaVessel2 == "Ves [Sta [Con \"a\" 1] 2,Sta [Con \"b\" 2] 2,Sta [] 3] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel3", show megaVessel3 == "Ves [Sta [Con \"a\" 1] 2,Sta [Con \"b\" 2] 2,Sta [Con \"c\" 3] 2] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel4", show megaVessel4 == "Ves [Sta [] 3,Sta [Con \"b\" 2] 2,Sta [Con \"c\" 3] 2] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel5", show megaVessel5 == "Ves [Sta [Con \"a\" 1] 2,Sta [Con \"b\" 2] 2,Sta [Con \"c\" 3] 2] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel6", show megaVessel6 == "Ves [Sta [Con \"a\" 19,Con \"a\" 1] 1,Sta [Con \"b\" 2] 2,Sta [Con \"c\" 3] 2] (Rou [\"a\",\"b\",\"c\"])")
            ,("megaVessel7", show megaVessel7 == "Ves [Sta [Con \"a\" 19,Con \"a\" 1] 1,Sta [Con \"b\" 2] 2,Sta [Con \"c\" 3] 2] (Rou [\"a\",\"b\",\"c\"])") ]

pesadotest = [("test de holdsS stackpesado containerPesado testroute2", not (holdsS stackpesado containerPesado testroute2) )]

testLoadVes = [("vessel con cosas not vessel vacio?: ",       loadV testVessel2 container1 /= testVessel2 )
              ,("cargado tiene 1 free cell",                  freeCellsV vesselCargado == 1 )
              ,("loadV fullvessel is the same as fullvessel", loadV fullvessel container1 == fullvessel ) ]

testUnloadVes = [("vessel con cosas not= vessel vacio",  testVesselUnloaded == testVessel2 )
                ,("cambia algo en el vessel cuando lo cargo", vesselCargado /= testVessel2 )
                ,("not tiene 3 cells libres",    freeCellsV testVesselUnloaded /= 3 )
                ,("si tiene 2 cells libres",     freeCellsV testVesselUnloaded == 2 )
                ,("not tiene 1 cell libre",      freeCellsV testVesselUnloaded /= 1 )
                ,("cargado tiene 1 cell libre",  freeCellsV vesselCargado == 1 )
                ,("unload 2 al mismo tiempo",          unloadV fullvessel "aire" == testVessel2 )
                ,("unload 2 al mismo del mismo stack", unloadV vesselcargado2x2Stack1Lleno "aire" == testVessel2x2 ) ]

test = [ ( "test de destination"     , destinationC container1 == "aire")
        ,( "test de net weight C"    , netC container1 == 2)
        ,( "test de inOrderR ab - ab",         inOrderR  testroute1 "a" "b")
        ,( "test de inOrderR ab - cb",    not (inOrderR  testroute1 "c" "b"))
        ,( "test de inOrderR ab - ba",    not (inOrderR  testroute1 "b" "a"))
        ,( "test de inOrderR ab - ba",    not (inOrderR  testroute2 "bueno" "a"))
        ,( "test de freeCellsS stack1Empty", freeCellsS stack1Empty == 1)
        ,( "test de freeCellsS stack1Full",  freeCellsS stack1Full == 0)
        ,( "test de freeCellsS falso",       freeCellsS stack1Empty /= 2)
        ,( "test de netS stack1Empty", netS stack1Empty == 0)
        ,( "test de netS stack1Full",  netS stack1Full == 2)
        ,( "test de holdsS stack1Empty container1 testroute",     holdsS stack1Empty container1 testroute1)
        ,( "test de holdsS stack1Full container1 testroute", not (holdsS stack1Full container1 testroute1))
        ,( "test de popS stack1Full aire", popS stack1Full "aire" == stack1Empty) ]

testHoldss :: [(String,Bool)]
testHoldss = [("test de holdsS stack1full container1 testroute2", not (holdsS stack1Full container1 testroute2))
             ,("test de holdsS stack1full stack3SemiFull testroute2", holdsS stack3SemiFull container1 testroute2)]

vestest :: [(String, Bool)]
vestest = [ ( "test de freeCellsV Ves stack1Empty testroute", freeCellsV testVessel == 2)
           ,( "test de freeCellsV Ves stack1Empty testroute", freeCellsV testVessel /= 3)
           ,("test de netV Ves stack1Empty testroute", netV testVessel == 0)
           ,("test de netV Ves stack1Empty testroute", netV testVessel /= 1) ]

testPopS :: [(String, Bool)]
testPopS = [ ("test de popS stack1Full aire",       popS stack1Full "aire" == stack1Empty)
           , ("test de popS stack1Full buenos",     popS stack1Full "buenos" == stack1Full)
           , ("test de popS stack1Empty aire",      popS stack1Empty "aire" == stack1Empty)
           , ("test de popS stack1Empty buenos",    popS stack1Empty "buenos" == stack1Empty)
           , ("test de popS stack3SemiFull aire",   popS stack3SemiFull "aire" == stack3Empty)
           , ("test de popS stack3SemiFull buenos", popS stack3SemiFull "buenos" == stack3SemiFull)
           , ("test de popS stackpesado aire",      popS stackpesado "aire" == stack3Empty)
           , ("test de popS stackpesado buenos",    popS stackpesado "buenos" == stackpesado)
           , ("test de popS stack2Full aire",       popS stack2Full "aire" /= stack2Empty)
           , ("test de popS stack2Full buenos",     popS stack2Full "buenos" == stack2Full)
           , ("test de popS stack2Empty aire",      popS stack2Empty "aire" == stack2Empty)
           , ("test de popS stack2Empty buenos",    popS stack2Empty "buenos" == stack2Empty)]

testUnloadV :: [(String, Bool)]
testUnloadV = [ ("test unloadV Ves stack1Empty aire", unloadV testVessel "aire" == testVessel)
              , ("test unloadV Ves stack1Full aire",  unloadV testVessel2 "aire" /= testVessel)
              , ("test unloadV Ves stack1Full bueno",  testVessel2  == testVesselUnloaded)
              , ("test unloadV Ves stack1Full ciudad no existente",  unloadV testVessel2 "ciudad" == testVessel2)
              , ("test unloadV Ves stack1Empty ciudad no existente", unloadV testVessel "ciudad" == testVessel) ]

test_de_tests = [ testUnloadV, testPopS, vestest, testHoldss, test, testUnloadVes, testLoadVes, pesadotest, megatest ]
