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

::Tesztesetek lefuttatása és a kimeneti fájlhoz fűzése

::2jatek_rossz
java -cp ./bin phoebe.Application < %test_input_dir%/2jatek_rossz.txt > %test_output_dir%/2jatek_rossz.txt
java -cp ./bin difftool.Application %test_output_dir%/2jatek_rossz.txt %test_compare_dir%/2jatek_rossz.txt >> %output%

::2jatek_jo
java -cp ./bin phoebe.Application < %test_input_dir%/2jatek_jo.txt > %test_output_dir%/2jatek_jo.txt
java -cp ./bin difftool.Application %test_output_dir%/2jatek_jo.txt %test_compare_dir%/2jatek_jo.txt >> %output%

::jatekosszam
java -cp ./bin phoebe.Application < %test_input_dir%/jatekosszam.txt > %test_output_dir%/jatekosszam.txt
java -cp ./bin difftool.Application %test_output_dir%/jatekosszam.txt %test_compare_dir%/jatekosszam.txt >> %output%

::rovidjatek
java -cp ./bin phoebe.Application < %test_input_dir%/rovidjatek.txt > %test_output_dir%/rovidjatek.txt
java -cp ./bin difftool.Application %test_output_dir%/rovidjatek.txt %test_compare_dir%/rovidjatek.txt >> %output%

::hosszujatek
java -cp ./bin phoebe.Application < %test_input_dir%/hosszujatek.txt > %test_output_dir%/hosszujatek.txt
java -cp ./bin difftool.Application %test_output_dir%/hosszujatek.txt %test_compare_dir%/hosszujatek.txt >> %output%

::jatekosrobot_jatekosrobot_utkozes
java -cp ./bin phoebe.Application < %test_input_dir%/jatekosrobot_jatekosrobot_utkozes.txt > %test_output_dir%/jatekosrobot_jatekosrobot_utkozes.txt
java -cp ./bin difftool.Application %test_output_dir%/jatekosrobot_jatekosrobot_utkozes.txt %test_compare_dir%/jatekosrobot_jatekosrobot_utkozes.txt >> %output%

::jatekosrobot_takaritorobot_utkozes
java -cp ./bin phoebe.Application < %test_input_dir%/jatekosrobot_takaritorobot_utkozes.txt > %test_output_dir%/jatekosrobot_takaritorobot_utkozes.txt
java -cp ./bin difftool.Application %test_output_dir%/jatekosrobot_takaritorobot_utkozes.txt %test_compare_dir%/jatekosrobot_takaritorobot_utkozes.txt >> %output%

::takaritorobot_takaritorobot_utkozes
java -cp ./bin phoebe.Application < %test_input_dir%/takaritorobot_takaritorobot_utkozes.txt > %test_output_dir%/takaritorobot_takaritorobot_utkozes.txt
java -cp ./bin difftool.Application %test_output_dir%/takaritorobot_takaritorobot_utkozes.txt %test_compare_dir%/takaritorobot_takaritorobot_utkozes.txt >> %output%

::olajfolt
java -cp ./bin phoebe.Application < %test_input_dir%/olajfolt.txt > %test_output_dir%/olajfolt.txt
java -cp ./bin difftool.Application %test_output_dir%/olajfolt.txt %test_compare_dir%/olajfolt.txt >> %output%

::ragacsfolt
java -cp ./bin phoebe.Application < %test_input_dir%/ragacsfolt.txt > %test_output_dir%/ragacsfolt.txt
java -cp ./bin difftool.Application %test_output_dir%/ragacsfolt.txt %test_compare_dir%/ragacsfolt.txt >> %output%

::olaj_szaradas
java -cp ./bin phoebe.Application < %test_input_dir%/olaj_szaradas.txt > %test_output_dir%/olaj_szaradas.txt
java -cp ./bin difftool.Application %test_output_dir%/olaj_szaradas.txt %test_compare_dir%/olaj_szaradas.txt >> %output%

::ragacs_kopas
java -cp ./bin phoebe.Application < %test_input_dir%/ragacs_kopas.txt > %test_output_dir%/ragacs_kopas.txt
java -cp ./bin difftool.Application %test_output_dir%/ragacs_kopas.txt %test_compare_dir%/ragacs_kopas.txt >> %output%

::foltlerakas
java -cp ./bin phoebe.Application < %test_input_dir%/foltlerakas.txt > %test_output_dir%/foltlerakas.txt
java -cp ./bin difftool.Application %test_output_dir%/foltlerakas.txt %test_compare_dir%/foltlerakas.txt >> %output%

::takaritorobot_takaritas
java -cp ./bin phoebe.Application < %test_input_dir%/takaritorobot_takaritas.txt > %test_output_dir%/takaritorobot_takaritas.txt
java -cp ./bin difftool.Application %test_output_dir%/takaritorobot_takaritas.txt %test_compare_dir%/takaritorobot_takaritas.txt >> %output%


::Tesztelés vége
echo A teszteles veget ert.
pause
