package phoebe;

public class Application {

	public static void main(String[] args) {	
		// [...]
		
		// Tegyük fel, hogy a foo() meghívásakor lezajló hívásokat szeretnénk logolni.
		
		// Bekapcsoljuk a logolást.
		Log.setConsoleLogging(true);
		
		// Meghívjuk foo-t.
		foo("cica");
		
		// Kikapcsoljuk a logolást.
		Log.setConsoleLogging(false);
		
		// [...]
	}
	
	private static void foo(String par1){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát.
		Log.enterFunction(Application.class, "foo", par1);
		
		// Csinálunk dolgokat.
		bar();

		// Return elõtt kiírjuk mit fogunk returnölni, void esetén paraméter nélkül hívjuk 
		// meg az exitFunction()-t.
		Log.exitFunction();
		return;
	}
	
	private static int bar(){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát, ha van.
		Log.enterFunction(Application.class, "bar");
		
		// Csinálunk dolgokat.
		
		// Return elõtt kiírjuk mit fogunk returnölni.
		Log.exitFunction("3");
		return 3;
	}

}
