#Feladat

**Phoebe**

A MarsOne telepesei szabad idejükben robotok versenyeit szervezik. A robotok egy elõre elkészített versenypályán ugrálnak. A pályáról leugró robotok elakadnak, és kiesnek a játékból.

A robotok álló helyzetbõl indulnak, minden robot a saját kezdõpozíciójából. A sebességük egységnyi méretû, tetszõleges irányú sebességvektorral opcionálisan módosítható. Egy ugrással a sebességgel egyenesen arányos távolságra tudnak eljutni.

A pályán vannak olajfoltok, amikre érkezve sebességmódosításra nincs mód, illetve ragacsfoltok, amik a sebesség nagyságát megfelezik.

A robotok fel vannak szerelve olaj és ragacskészlettel, amiket a játékos parancsára elugráskor maguk mögött tudnak hagyni.

Az nyer, aki megadott idõ alatt a legnagyobb távolságot tudja megtenni.

**Módosítás (2015.03.23.)**

* a ragacs eltûnik a pályáról, miután négy robot ráugrott (elkopik)
* egy meghatározott idõ letelte után az olajfolt eltûnik a pályáról (felszárad)
* a pályára idõnként keményen dolgozó kisrobotok jutnak be, akik szépen sorban feltakarítják a foltokat. A kisrobot egységnyi sebességgel halad, adott ideig (pl. két kör) takarítja a foltot. A folt feltakarítása után a legközelebbi folthoz indul. Ha egy robot ráugrik, akkor a kisrobot megsemmisül és olajfolt kerül a helyére; a ráugró robot nem szenved sérülést. Ha a kisrobot másik kisrobotnak vagy robotnak ütközik, irányt vált.
* a robotok képesek ütközni, ha azonos helyre érkeznek ugrásuk végén. Ilyenkor a gyorsabb robot összetöri (megsemmisíti) a lassabbat, és kettejük átlagsebességével halad tovább (vektorátlag!).
