module Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV )
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)
--     cant    alto    ruta
newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cnatida de bahias(Stacks), la altura de las mismas y una ruta
newV cant_bahias altura ruta = Ves (replicate cant_bahias (newS altura)) ruta

freeCellsV :: Vessel -> Int            -- responde la celdas disponibles en el barco
freeCellsV (Ves stacks _) = sum (map freeCellsS stacks)

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
loadV (Ves stacks ruta) container = Ves (map (\stack -> stackS stack container) stacks) ruta
loadV (Ves stacks ruta) container = Ves( f stacks container route ) ruta

f :: [Stack] -> Container -> Route -> [Stack]
--checkear usando holdsS: si holdsS devuelve true, stackS, sino, f recursivo -- SIN IF!!!!
f [] _ _ = []
f stacks container route = map (\stack -> maybe stack (stackS stack) (holdsS stack container route)) stacks 


-- decidir que hacer si pasan un container que no pertenece a la ruta. explota holdsS?

-- unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
-- netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco




-- holdsV :: Vessel -> Container -> Bool  -- indica si el barco puede aceptar el contenedor considerando las ciudades en la ruta