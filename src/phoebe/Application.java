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
			if(command[0].equals("startgame")){
				gameController = new GameController();
				gameController.newGame();
			}else if(command[0].equals("putoil")){
				//TODO
			}else if(command[0].equals("putglue")){
				//TODO
			}else if(command[0].equals("inputvector")){
				double x = Double.parseDouble( command[1] );
				double y = Double.parseDouble( command[2] );
				gameController = new GameController();
				gameController.newGame();
				break;
			}else if(command[0].equals("endturn")){
				//TODO
			}
			
		}
	}

	
	
}
