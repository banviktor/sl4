package phoebe;

import phoebe.basic.Vector;
import phoebe.game.*;

public class Application {

	public static void main(String[] args) {		
		System.out.println("A szkeletonban alkalmazható parancsok listája:");
		System.out.println("");
		System.out.println("startgame		Elindít egy új játékot.");
		System.out.println("putoil			Egy olajfolt lerakását kezdeményezi.");
		System.out.println("putglue			Egy ragacsfolt lerakását kezdeményezi.");
		System.out.println("inputvector x y	Az (x, y) vektort adja meg bemeneti sebességvektornak.");
		System.out.println("endturn			A kör végét futtatja le.");
		System.out.println("exit			Kilép az alkalmazásból.");
		System.out.println("");
		System.out.println("A \">\" jel jelzi, hogy parancsra vár az alkalmazás.");
		System.out.println("");
		
		boolean exit = false;
		while ( !exit ) {
			String[] command = UserInput.getCommand();
			if(command[0].equals("exit")){
				exit = true;
			}else if(command[0].equals("startgame")){
				//Inicializálás
				GameController gc = new GameController();
				
				//Logolás és kérdezés bekapcsolása
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elindítása
				gc.newGame();

				//Logolás és kérdezés kikapcsolása
				Log.setConsoleLogging(false);
				UserInput.disable();
			}else if(command[0].equals("putoil")){
				//Inicializálás				
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logolás és kérdezés bekapcsolása
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elindítása
				rc.toggleOil();

				//Logolás és kérdezés kikapcsolása
				Log.setConsoleLogging(false);
				UserInput.disable();
				
			}else if(command[0].equals("putglue")){
				//Inicializálás		
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logolás és kérdezés bekapcsolása
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elindítása
				rc.toggleGlue();

				//Logolás és kérdezés kikapcsolása
				Log.setConsoleLogging(false);
				UserInput.disable();
				
			}else if(command[0].equals("inputvector")){
				//Inicializálás				
				double x = Double.parseDouble( command[1] );
				double y = Double.parseDouble( command[2] );
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logolás és kérdezés bekapcsolása
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elindítása
				rc.setInputSpeedVector(new Vector(x,y));				
				
				//Logolás és kérdezés kikapcsolása
				Log.setConsoleLogging(false);
				UserInput.disable();
			}else if(command[0].equals("endturn")){
				//Inicializálás	
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logolás és kérdezés bekapcsolása
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elindítása
				rc.nextTurn();

				//Logolás és kérdezés kikapcsolása
				Log.setConsoleLogging(false);
				UserInput.disable();
			}
			
		}
	}

	
	
}
