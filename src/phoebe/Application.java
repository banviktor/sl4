package phoebe;

import phoebe.basic.Vector;
import phoebe.game.*;

public class Application {

	public static void main(String[] args) {
		
		boolean exit = false;
		while ( !exit ) {
			String[] command = UserInput.getCommand();
			if(command[0].equals("startgame")){
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
