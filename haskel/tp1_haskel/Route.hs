module Route ( Route, newR, inOrderR )
 where

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR = Rou                                   

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta

inOrderR (Rou (city:cities)) city1 city2 | city1 == city = elem city2 (city:cities) 
                                         | otherwise = inOrderR (Rou cities) city1 city2
inOrderR (Rou []) city1 city2 = False                    




