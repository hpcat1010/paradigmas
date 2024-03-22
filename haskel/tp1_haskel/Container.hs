module Container ( Container, newC, destinationC, netC )
 where

data Container = Con String Int deriving (Eq, Show)

newC :: String -> Int -> Container   -- construye un Contenedor dada una ciudad de destino y un peso en toneladas
newC ciudad_x peso_x = Con ciudad_x peso_x


destinationC :: Container -> String  -- responde la ciuda destino del contenedor
destinationC (Con ciudad_x peso_x) = ciudad_x


netC :: Container -> Int             -- responde el peso en toneladas del contenedor
netC (Con ciudad_x peso_x) = peso_x