package phoebe;

import phoebe.game.GameController;

public class Application {
	private static GameController gc = null;

	public static void main(String[] args) {
		boolean exit = false;
		gc = new GameController();
		while (!exit) {
			String[] command = UserInput.getCommand();
			if(command[0].equals("exit")){
				exit = true;
			} else if(command[0].equals("randomization")) {
				if (command[1].equals("off")) {
					UserInput.randomOff();
				} else {
					UserInput.randomOn();
				}
			} else if(command[0].equals("questions")) {
				if (command[1].equals("off")) {
					UserInput.questionsOff();
				} else {
					UserInput.questionsOn();
				}
			} else if(command[0].equals("start_game")) {
				gc.newGame();
			} else if(command[0].equals("smudge_stock")) {
				;
			} else if(command[0].equals("toggle_oil")) {
				;
			} else if(command[0].equals("toggle_glue")) {
				;
			} else if(command[0].equals("inputvector")) {
				;
			} else if(command[0].equals("jump")) {
				;
			} else if(command[0].equals("list_player_robots")) {
				;
			} else if(command[0].equals("list_cleaner_robots")) {
				;
			} else if(command[0].equals("list_smudges")) {
				;
			}
		}
	}
	
}
