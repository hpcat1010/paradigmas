module Route ( Route, newR, inOrderR )
 where

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR = Rou                                   -- forma hiper reducida. 
-- antes: -- newR [ciudad_x, ciudad_y] = Rou [ciudad_x, ciudad_y]
-- antes la implementación recibía solo dos ciudades, ahora deja que haskell procese cuantas ciudades llegan y las meta en el constructor (checkeado √)

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta

inOrderR (Rou []) city1 city2 = False                   -- si la lista de ciudades está vacía. 
inOrderR (Rou (city:cities)) city1 city2 | city1 == city = elem city2 cities
                                         | otherwise = inOrderR (Rou cities) city1 city2

-- itera sobre la lista de ciudades, si la ciudad1 es igual a la ciudad actual (el pop del 1er elemento), entonces la ciudad2 debe estar en la lista de ciudades restantes, no importa en qué posición.
-- (checkeado √)


-- funcion vieja: -- 
-- inOrderR  (Rou [ciudad_x, ciudad_y]) ciudad1 ciudad2 = ciudad1 == ciudad_x && ciudad2 == ciudad_y -- no. -- mal, asume que la lista de ciudades tiene 2 elementos.