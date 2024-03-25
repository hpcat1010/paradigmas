module Vessel ( Vessel, newV, freeCellsV, loadV , unloadV, netV)
 where

import Container
import Stack
import Route

data Vessel = Ves [ Stack ] Route deriving (Eq, Show)

newV :: Int -> Int -> Route -> Vessel  -- construye un barco según una cantidad de bahias(Stacks), la altura de las mismas y una ruta
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

unloadV :: Vessel -> String -> Vessel  -- responde un barco al que se le han descargado los contenedores que podían descargarse en la ciudad
unloadV (Ves stacks route) city = Ves (removeContainerFromStacks stacks city []) route

removeContainerFromStacks :: [Stack] -> String -> [Stack] -> [Stack]
removeContainerFromStacks [] _ modifiedStacks = modifiedStacks
removeContainerFromStacks (stack:remainingStacks) city modifiedStacks =
    removeContainerFromStacks remainingStacks city (modifiedStacks ++ [popS stack city])

netV :: Vessel -> Int                  -- responde el peso neto en toneladas de los contenedores en el barco
netV (Ves stacks _) = sum (map netS stacks)

