package phoebe;

import phoebe.basic.Vector;
import phoebe.game.*;

public class Application {

	public static void main(String[] args) {		
		System.out.println("A szkeletonban alkalmazhat� parancsok list�ja:");
		System.out.println("");
		System.out.println("startgame		Elind�t egy �j j�t�kot.");
		System.out.println("putoil			Egy olajfolt lerak�s�t kezdem�nyezi.");
		System.out.println("putglue			Egy ragacsfolt lerak�s�t kezdem�nyezi.");
		System.out.println("inputvector x y	Az (x, y) vektort adja meg bemeneti sebess�gvektornak.");
		System.out.println("endturn			A k�r v�g�t futtatja le.");
		System.out.println("exit			Kil�p az alkalmaz�sb�l.");
		System.out.println("");
		System.out.println("A \">\" jel jelzi, hogy parancsra v�r az alkalmaz�s.");
		System.out.println("");
		
		boolean exit = false;
		while ( !exit ) {
			String[] command = UserInput.getCommand();
			if(command[0].equals("exit")){
				exit = true;
			}else if(command[0].equals("startgame")){
				//Inicializ�l�s
				GameController gc = new GameController();
				
				//Logol�s �s k�rdez�s bekapcsol�sa
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elind�t�sa
				gc.newGame();

				//Logol�s �s k�rdez�s kikapcsol�sa
				Log.setConsoleLogging(false);
				UserInput.disable();
			}else if(command[0].equals("putoil")){
				//Inicializ�l�s				
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logol�s �s k�rdez�s bekapcsol�sa
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elind�t�sa
				rc.toggleOil();

				//Logol�s �s k�rdez�s kikapcsol�sa
				Log.setConsoleLogging(false);
				UserInput.disable();
				
			}else if(command[0].equals("putglue")){
				//Inicializ�l�s		
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logol�s �s k�rdez�s bekapcsol�sa
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elind�t�sa
				rc.toggleGlue();

				//Logol�s �s k�rdez�s kikapcsol�sa
				Log.setConsoleLogging(false);
				UserInput.disable();
				
			}else if(command[0].equals("inputvector")){
				//Inicializ�l�s				
				double x = Double.parseDouble( command[1] );
				double y = Double.parseDouble( command[2] );
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logol�s �s k�rdez�s bekapcsol�sa
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elind�t�sa
				rc.setInputSpeedVector(new Vector(x,y));				
				
				//Logol�s �s k�rdez�s kikapcsol�sa
				Log.setConsoleLogging(false);
				UserInput.disable();
			}else if(command[0].equals("endturn")){
				//Inicializ�l�s	
				GameController gc = new GameController();
				gc.newGame();
				RobotController rc = gc.getRobotController();
				
				//Logol�s �s k�rdez�s bekapcsol�sa
				Log.setConsoleLogging(true);
				UserInput.enable();
				
				//Folyamat elind�t�sa
				rc.nextTurn();

				//Logol�s �s k�rdez�s kikapcsol�sa
				Log.setConsoleLogging(false);
				UserInput.disable();
			}
			
		}
	}

	
	
}
