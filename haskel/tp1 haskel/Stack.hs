module Stack -- ( Stack, newS, freeCellsS, stackS, netS, holdsS, popS )
 where

import Container
import Route

data Stack = Sta [ Container ] Int deriving (Eq, Show)

newS :: Int -> Stack                          -- construye una Pila con la capacidad indicada 
newS n = Sta [] n
--newS n = [1..n]

freeCellsS :: Stack -> Int                    -- responde la celdas disponibles en la pila
freeCellsS (Sta _ n) = n

-- stackS :: Stack -> Container -> Stack         -- apila el contenedor indicado en la pila

netS :: Stack -> Int                          -- responde el peso neto de los contenedores en la pila
netS (Sta _ pesos) =  sum [pesos]

-- netC :: Container -> Int             -- responde el peso en toneladas del contenedor
-- netC (Con ciudad_x peso_x) = peso_x
---- hay que buscar una función dentro de container que devuelva el peso peso_x 

-- holdsS :: Stack -> Container -> Route -> Bool -- indica si la pila puede aceptar el contenedor considerando las ciudades en la ruta

-- popS :: Stack -> String -> Stack              -- quita del tope los contenedores con destino en la ciudad indicada