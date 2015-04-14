::A konzolra való írás kikapcsolása
@echo off

::Könyvtárak
set test_input_dir=tests/input
set test_output_dir=tests/results
set test_compare_dir=tests/expected

::A kimeneti fájl meghatározása
set output=%1
if %1.==. set output=test_results.txt

::A kimeneti fájl létrehozása üresen
type NUL > %output%

::Tesztesetek lefuttatása és a kimeneti fájlhoz fûzése

::Teszt 1
java -cp ./bin phoebe.Application < %test_input_dir%/teszt_2jatek_rossz.txt > %test_output_dir%/teszt_2jatek_rossz.txt
java -cp ./bin difftool.Application %test_output_dir%/teszt_2jatek_rossz.txt %test_compare_dir%/teszt_2jatek_rossz.txt >> %output%

::Tesztelés vége
echo A teszteles veget ert.
pause
