::A konzolra val� �r�s kikapcsol�sa
@echo off

::K�nyvt�rak
set test_input_dir=tests/input
set test_output_dir=tests/results
set test_compare_dir=tests/expected

::A kimeneti f�jl meghat�roz�sa
set output=%1
if %1.==. set output=test_results.txt

::A kimeneti f�jl l�trehoz�sa �resen
type NUL > %output%

::Tesztesetek lefuttat�sa �s a kimeneti f�jlhoz f�z�se

::Teszt 1
java -cp ./bin phoebe.Application < %test_input_dir%/teszt_2jatek_rossz.txt > %test_output_dir%/teszt_2jatek_rossz.txt
java -cp ./bin difftool.Application %test_output_dir%/teszt_2jatek_rossz.txt %test_compare_dir%/teszt_2jatek_rossz.txt >> %output%

::Tesztel�s v�ge
echo A teszteles veget ert.
pause
