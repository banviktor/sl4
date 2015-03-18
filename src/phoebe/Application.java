package phoebe;

public class Application {

	public static void main(String[] args) {	
		// [...]
		
		// Tegy�k fel, hogy a foo() megh�v�sakor lezajl� h�v�sokat szeretn�nk logolni.
		
		// Bekapcsoljuk a logol�st.
		Log.setConsoleLogging(true);
		
		// Megh�vjuk foo-t.
		foo("cica");
		
		// Kikapcsoljuk a logol�st.
		Log.setConsoleLogging(false);
		
		// [...]
	}
	
	private static void foo(String par1){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t.
		Log.enterFunction(Application.class, "foo", par1);
		
		// Csin�lunk dolgokat.
		bar();

		// Return el�tt ki�rjuk mit fogunk return�lni, void eset�n param�ter n�lk�l h�vjuk 
		// meg az exitFunction()-t.
		Log.exitFunction();
		return;
	}
	
	private static int bar(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t, ha van.
		Log.enterFunction(Application.class, "bar");
		
		// Csin�lunk dolgokat.
		
		// Return el�tt ki�rjuk mit fogunk return�lni.
		Log.exitFunction("3");
		return 3;
	}

}
