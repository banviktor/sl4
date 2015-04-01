#What is this?
This repo contains the sources of our solution of the task for the Budapest University of Technology and Economics' Computer Engineering BSc's subject Software Laboratory 4. We are a team of 4 students and named ourselves *Barbershop Quartet*. The documentation is actively developed in Google Docs, so it cannot be found here (and it definitely won't get translated).

##English (translation)
###Original task

**Phoebe**

The settlers of MarsOne organize competitions for robots in their freetime. The robots have to jump around on a pre-made racing track. If a robot jumps off the track it gets disqualified.

The robots start without initial speed, every robot from its own starting position. Their velocity can be optionally modified with a 1 unit long velocity with any angle. The length of the jump is directly proportional to the robot's velocity.

There are oil smudges on the track, which make the robots standing on them unable to modify their velocity, also there are glue smudges, which halve the speed of the robots standing on them.

The robots are equipped with oil and glue stock, which can be deployed on the command of the player before jumping.

The robot with the most accumulated distance jumped under the specified time limit wins.

###Modification (03/23/2015)

* the glue vanishes off the track after 4 robots have jumped on it (wears off)
* after a specified time the oil smudge vanishes off the map (dries up)
* sometimes hard working small robots enter the map whose task is to clean the smudges. The small robot has a constant speedvector of unit length and cleans the smudge for a given time (e.g. 2 rounds). After cleaning a smudge it starts going to the nearest smudge. If a robot jumps on it then the small robot gets destroyed and leaves an oil smudge in its place; the robot jumping on it doesn't suffer damage. If a small robot bumps into another small robot or a big robot then it changes direction.
* the robots can collide, if they end up on the same position after their jump. In that case the faster robot smashes (destroys) the slower, and proceeds with the average of their velocities (vectorial average)

##Hungarian (original)
###Feladat

**Phoebe**

A MarsOne telepesei szabad idejükben robotok versenyeit szervezik. A robotok egy elõre elkészített versenypályán ugrálnak. A pályáról leugró robotok elakadnak, és kiesnek a játékból.

A robotok álló helyzetbõl indulnak, minden robot a saját kezdõpozíciójából. A sebességük egységnyi méretû, tetszõleges irányú sebességvektorral opcionálisan módosítható. Egy ugrással a sebességgel egyenesen arányos távolságra tudnak eljutni.

A pályán vannak olajfoltok, amikre érkezve sebességmódosításra nincs mód, illetve ragacsfoltok, amik a sebesség nagyságát megfelezik.

A robotok fel vannak szerelve olaj és ragacskészlettel, amiket a játékos parancsára elugráskor maguk mögött tudnak hagyni.

Az nyer, aki megadott idõ alatt a legnagyobb távolságot tudja megtenni.

###Módosítás (2015.03.23.)

* a ragacs eltûnik a pályáról, miután négy robot ráugrott (elkopik)
* egy meghatározott idõ letelte után az olajfolt eltûnik a pályáról (felszárad)
* a pályára idõnként keményen dolgozó kisrobotok jutnak be, akik szépen sorban feltakarítják a foltokat. A kisrobot egységnyi sebességgel halad, adott ideig (pl. két kör) takarítja a foltot. A folt feltakarítása után a legközelebbi folthoz indul. Ha egy robot ráugrik, akkor a kisrobot megsemmisül és olajfolt kerül a helyére; a ráugró robot nem szenved sérülést. Ha a kisrobot másik kisrobotnak vagy robotnak ütközik, irányt vált.
* a robotok képesek ütközni, ha azonos helyre érkeznek ugrásuk végén. Ilyenkor a gyorsabb robot összetöri (megsemmisíti) a lassabbat, és kettejük átlagsebességével halad tovább (vektorátlag!).
