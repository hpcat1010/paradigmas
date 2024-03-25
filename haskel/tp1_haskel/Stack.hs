module Stack -- ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where
import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)          

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS n = Sta [] n                             

freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS (Sta _ n) = n

stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila
stackS (Sta containers n) container = Sta (container:containers) (n-1)

netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta containers _) = sum (map netC containers) 

holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta
holdsS (Sta containers cap) container ruta   
    | freeCellsS (Sta containers cap) > 0 
    && netS (Sta containers cap) + netC container <= 20 
    && ( isEmptyStack (Sta containers cap) ||  inOrderR ruta (destinationC container) (destinationC (last containers)) ) 
    = True
    | otherwise = False      -- es una decision
  
isEmptyStack :: Stack -> Bool
isEmptyStack (Sta [] n) = True
isEmptyStack (Sta _ n) = False

popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada
popS (Sta containers n) ciudad
    | any (\container -> destinationC container == ciudad) containers = Sta (filter (\container -> destinationC container /= ciudad) containers) (n + length (filter (\container -> destinationC container == ciudad) containers))
    | otherwise = Sta containers n