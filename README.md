#Feladat

**Phoebe**

A MarsOne telepesei szabad idej�kben robotok versenyeit szervezik. A robotok egy el�re elk�sz�tett versenyp�ly�n ugr�lnak. A p�ly�r�l leugr� robotok elakadnak, �s kiesnek a j�t�kb�l.

A robotok �ll� helyzetb�l indulnak, minden robot a saj�t kezd�poz�ci�j�b�l. A sebess�g�k egys�gnyi m�ret�, tetsz�leges ir�ny� sebess�gvektorral opcion�lisan m�dos�that�. Egy ugr�ssal a sebess�ggel egyenesen ar�nyos t�vols�gra tudnak eljutni.

A p�ly�n vannak olajfoltok, amikre �rkezve sebess�gm�dos�t�sra nincs m�d, illetve ragacsfoltok, amik a sebess�g nagys�g�t megfelezik.

A robotok fel vannak szerelve olaj �s ragacsk�szlettel, amiket a j�t�kos parancs�ra elugr�skor maguk m�g�tt tudnak hagyni.

Az nyer, aki megadott id� alatt a legnagyobb t�vols�got tudja megtenni.

**M�dos�t�s (2015.03.23.)**

* a ragacs elt�nik a p�ly�r�l, miut�n n�gy robot r�ugrott (elkopik)
* egy meghat�rozott id� letelte ut�n az olajfolt elt�nik a p�ly�r�l (felsz�rad)
* a p�ly�ra id�nk�nt kem�nyen dolgoz� kisrobotok jutnak be, akik sz�pen sorban feltakar�tj�k a foltokat. A kisrobot egys�gnyi sebess�ggel halad, adott ideig (pl. k�t k�r) takar�tja a foltot. A folt feltakar�t�sa ut�n a legk�zelebbi folthoz indul. Ha egy robot r�ugrik, akkor a kisrobot megsemmis�l �s olajfolt ker�l a hely�re; a r�ugr� robot nem szenved s�r�l�st. Ha a kisrobot m�sik kisrobotnak vagy robotnak �tk�zik, ir�nyt v�lt.
* a robotok k�pesek �tk�zni, ha azonos helyre �rkeznek ugr�suk v�g�n. Ilyenkor a gyorsabb robot �sszet�ri (megsemmis�ti) a lassabbat, �s kettej�k �tlagsebess�g�vel halad tov�bb (vektor�tlag!).
