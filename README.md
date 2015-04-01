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

A MarsOne telepesei szabad idej�kben robotok versenyeit szervezik. A robotok egy el�re elk�sz�tett versenyp�ly�n ugr�lnak. A p�ly�r�l leugr� robotok elakadnak, �s kiesnek a j�t�kb�l.

A robotok �ll� helyzetb�l indulnak, minden robot a saj�t kezd�poz�ci�j�b�l. A sebess�g�k egys�gnyi m�ret�, tetsz�leges ir�ny� sebess�gvektorral opcion�lisan m�dos�that�. Egy ugr�ssal a sebess�ggel egyenesen ar�nyos t�vols�gra tudnak eljutni.

A p�ly�n vannak olajfoltok, amikre �rkezve sebess�gm�dos�t�sra nincs m�d, illetve ragacsfoltok, amik a sebess�g nagys�g�t megfelezik.

A robotok fel vannak szerelve olaj �s ragacsk�szlettel, amiket a j�t�kos parancs�ra elugr�skor maguk m�g�tt tudnak hagyni.

Az nyer, aki megadott id� alatt a legnagyobb t�vols�got tudja megtenni.

###M�dos�t�s (2015.03.23.)

* a ragacs elt�nik a p�ly�r�l, miut�n n�gy robot r�ugrott (elkopik)
* egy meghat�rozott id� letelte ut�n az olajfolt elt�nik a p�ly�r�l (felsz�rad)
* a p�ly�ra id�nk�nt kem�nyen dolgoz� kisrobotok jutnak be, akik sz�pen sorban feltakar�tj�k a foltokat. A kisrobot egys�gnyi sebess�ggel halad, adott ideig (pl. k�t k�r) takar�tja a foltot. A folt feltakar�t�sa ut�n a legk�zelebbi folthoz indul. Ha egy robot r�ugrik, akkor a kisrobot megsemmis�l �s olajfolt ker�l a hely�re; a r�ugr� robot nem szenved s�r�l�st. Ha a kisrobot m�sik kisrobotnak vagy robotnak �tk�zik, ir�nyt v�lt.
* a robotok k�pesek �tk�zni, ha azonos helyre �rkeznek ugr�suk v�g�n. Ilyenkor a gyorsabb robot �sszet�ri (megsemmis�ti) a lassabbat, �s kettej�k �tlagsebess�g�vel halad tov�bb (vektor�tlag!).
