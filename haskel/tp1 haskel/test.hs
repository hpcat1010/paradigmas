 ---   module_container   ---
ghci> newC "aire" 2
Con "aire" 2

ghci> destinationC (Con "aire" 2)
"aire"

ghci> netC (Con "aire" 2)
2

---    module_Route    ---
ghci> newR ["aire", "buenos"]  
Rou ["aire","buenos"]



ghci> inOrderR  (Rou ["a", "b" ]) "a" "b"                                                              
True
ghci> inOrderR  (Rou ["a", "b" ]) "b" "a" 
False
ghci> inOrderR  (Rou ["a", "b" ]) "a" "a" 
False
ghci> inOrderR  (Rou ["a", "b" ]) "c" "a" 
False