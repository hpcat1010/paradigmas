module Stack -- ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route
--                            alto disponible
data Stack = Sta [ Container ] Int deriving (Eq, Show)          

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS n = Sta [] n                             --newS n = [1..n]



freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS (Sta _ n) = n

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta containers n) container = Sta (container:containers) (n-1)



netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta containers _) = sum (map netC containers) -- map takes a function and a list and applies that function to every element in the list, producing a new list.

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta containers cap) container ruta   
    -- |cap > 0 = True 
    | freeCellsS (Sta containers cap) > 0 
    && netS (Sta containers cap) + netC container < 20 
    && ( isEmptyStack (Sta containers cap) ||  inOrderR ruta (destinationC container) (destinationC (last containers)) ) 
    = True
    | otherwise = False      -- es una decision!
  
isEmptyStack :: Stack -> Bool
isEmptyStack (Sta [] n) = True
isEmptyStack (Sta _ n) = False

--     |n < 0 = False 
{-
holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta containers n) container ruta   
    |n >= 0 = True 
    |netS (Sta containers n) + netC container > 20 = False
    |otherwise = inOrderR ruta (destinationC container) (destinationC (last containers)) -}

-- en duda q significa "si la pila puede aceptar el contenedor considerando las ciudades en la ruta"
--https://stackoverflow.com/questions/7376937/fastest-way-to-get-the-last-element-of-a-list-in-haskell

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada
popS (Sta containers n) ciudad
    | any (\container -> destinationC container == ciudad) containers = Sta (filter (\container -> destinationC container /= ciudad) containers) (n+1)
    | otherwise = Sta containers n
