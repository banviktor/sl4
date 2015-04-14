::A konzolra való írás kikapcsolása
@echo off

::A kimeneti fájl meghatározása
set output=%1
if %1.==. set output=test_results.txt

::A kimeneti fájl létrehozása üresen
type NUL > %output%

::Tesztesetek lefuttatása és a kimeneti fájlhoz fűzése

::Teszt 1
::java phoebe.Application < teszt1_be.txt > teszt1_ki.txt
::java difftool teszt1_ki.txt teszt1_elvart.txt >> %output%

::Tesztelés vége
echo A teszteles veget ert.
pause
