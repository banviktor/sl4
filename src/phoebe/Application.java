package phoebe;

import phoebe.game.GameController;

public class Application {

	public static void main(String[] args) {
		Log.setConsoleLogging(true);
		UserInput.enable();
		boolean exit = false;
		GameController gameController;
		while ( !exit ) {
			String[] command = UserInput.getCommand();
			switch ( command[0] ) {
			case "startgame":
				gameController = new GameController();
				gameController.newGame();
				break;
			case "putoil":
				break;
			case "putglue":
				break;
			case "inputvector":
				double x = Double.parseDouble( command[1] );
				double y = Double.parseDouble( command[2] );
				gameController = new GameController();
				gameController.newGame();
				break;
			case "endturn":
				break;
			}

			
		}
	}

	
	
}
