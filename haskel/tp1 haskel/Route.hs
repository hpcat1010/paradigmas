module Route ( Route, newR, inOrderR )
 where

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR [ciudad_x, ciudad_y] = Rou [ciudad_x, ciudad_y]

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta
inOrderR  (Rou [ciudad_x, ciudad_y]) ciudad1 ciudad2 = ciudad1 == ciudad_x && ciudad2 == ciudad_y