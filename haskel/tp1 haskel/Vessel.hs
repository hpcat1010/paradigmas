module Vessel ( Vessel, newV, freeCellsV, loadV , unloadV, netV)
 where

import Container
import Stack
import Route







data Vessel = Ves [ Stack ] Route deriving (Eq, Show)
--     cant    alto    ruta
newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cnatida de bahias(Stacks), la altura de las mismas y una ruta
newV cant_bahias altura ruta = Ves (replicate cant_bahias (newS altura)) ruta

freeCellsV :: Vessel -> Int            -- responde las celdas disponibles en el barco
freeCellsV (Ves stacks _) = sum (map freeCellsS stacks)

loadV :: Vessel -> Container -> Vessel -- carga un contenedor en el barco
loadV (Ves stacks route) container = Ves (addContainerToStacks stacks container route []) route

addContainerToStacks :: [Stack] -> Container -> Route -> [Stack] -> [Stack]
addContainerToStacks [] _ _ checkedStacks = checkedStacks
addContainerToStacks (stack:remainingStacks) container route checkedStacks      -- caso base 
    | holdsS stack container route = (checkedStacks ++ [stackS stack container] ++ remainingStacks)    -- chequeo si el stack puede contener el container
    | otherwise = addContainerToStacks remainingStacks container route (checkedStacks ++ [stack])      -- paso recursivo

{-En lugar de buscar el primer Stack que puede contener el Container, puedes dividir la lista de Stacks en dos partes: los Stacks que ya has comprobado y los que aún no has comprobado. Luego puedes agregar el Container al primer Stack que puede contenerlo y combinar las dos partes de nuevo. Aquí está cómo podrías hacerlo:

checkedStacks
En este código, addContainerToStacks es una función auxiliar que divide la lista de Stacks en checkedStacks (los Stacks que ya has comprobado) y (stack:remainingStacks) (el Stack actual y los Stacks que aún no has comprobado). Si el Stack actual puede contener el Container, se agrega el Container al Stack y se combinan las dos partes de la lista de Stacks. Si el Stack actual no puede contener el Container, se agrega a la lista de checkedStacks y se comprueba el siguiente Stack. Si no hay más Stacks para comprobar, se devuelve la lista de checkedStacks.-}
----------------------------------------
{- loadV (Ves stacks ruta) container = Ves (map (\stack -> stackS stack container) stacks) ruta
loadV (Ves stacks ruta) container = Ves( f stacks container ruta ) ruta         -- era cambiar route por ruta. 

f :: [Stack] -> Container -> Route -> [Stack]
--checkear usando holdsS: si holdsS devuelve true, stackS, sino, f recursivo -- SIN IF!!!!
f [] _ _ = []
-- f stacks container route = map (\stack -> maybe stack (stackS stack) (holdsS stack container route)) stacks 
f stacks container route = map (\stack -> stackS stack container) (filter (\stack -> holdsS stack container route) stacks) ++ f (filter (\stack -> not (holdsS stack container route)) stacks) container route
--f stacks container route = map stackS (filter (\stack -> holdsS stack container route) stacks) ++ f (filter (\stack -> not (holdsS stack container route)) stacks) container route
-}

-- decidir que hacer si pasan un container que no pertenece a la ruta. explota holdsS?
------------------------
-- unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
-- unloadV (Ves stacks ruta) ciudad = Ves (map (\stack -> popS stack ciudad) stacks) ruta

unloadV :: Vessel -> String -> Vessel
unloadV (Ves stacks route) city = Ves (removeContainerFromStacks stacks city []) route

removeContainerFromStacks :: [Stack] -> String -> [Stack] -> [Stack]
removeContainerFromStacks [] _ modifiedStacks = modifiedStacks
removeContainerFromStacks (stack:remainingStacks) city modifiedStacks =
    removeContainerFromStacks remainingStacks city (modifiedStacks ++ [popS stack city])




netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves stacks _) = sum (map netS stacks)




-- holdsV :: Vessel -> Container -> Bool  -- indica si el barco puede aceptar el contenedor considerando las ciudades en la ruta