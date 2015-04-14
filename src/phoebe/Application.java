package phoebe;

import phoebe.basic.Vector;
import phoebe.game.CleanerRobot;
import phoebe.game.GameController;
import phoebe.game.PlayerRobot;
import phoebe.game.Smudge;

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
				System.out.println("Olajfoltok:   " + gc.getRobotController().getActualRobot().getOilNumber());
				System.out.println("Ragacsfoltok: " + gc.getRobotController().getActualRobot().getGlueNumber());
			} else if(command[0].equals("toggle_oil")) {
				gc.getRobotController().toggleOil();
			} else if(command[0].equals("toggle_glue")) {
				gc.getRobotController().toggleGlue();
			} else if(command[0].equals("inputvector")) {
				Vector input = new Vector(Double.parseDouble( command[1] ), Double.parseDouble( command[2] ));
				gc.getRobotController().setInputSpeedVector( input );
			} else if(command[0].equals("jump")) {
				gc.getRobotController().nextTurn();
			} else if(command[0].equals("list_player_robots")) {
				System.out.println("Játékosrobotok listája:");
				for(PlayerRobot i : gc.getGame().getPlayerRobots()) {
					System.out.println(i.toString());
				}
			} else if(command[0].equals("list_cleaner_robots")) {
				System.out.println("Takarítórobotok listája:");
				for(CleanerRobot i : gc.getGame().getCleaningRobots()) {
					System.out.println(i.toString());
				}
			} else if(command[0].equals("list_smudges")) {
				System.out.println("Foltok listája:");
				for(Smudge i : gc.getGame().getMap().getSmudges()) {
					System.out.println(i.toString());
				}
			} else {
				System.out.println("Nincs ilyen parancs.");
			}
		}
	}
	
}
