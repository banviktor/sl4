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
			String[] command = UserIO.getCommand();
			if(command[0].equals("exit")){
				exit = true;
			} else if(command[0].equals("randomization")) {
				if (command[1].equals("off")) {
					UserIO.randomOff();
				} else {
					UserIO.randomOn();
				}
			} else if(command[0].equals("questions")) {
				if (command[1].equals("off")) {
					UserIO.questionsOff();
				} else {
					UserIO.questionsOn();
				}
			} else if(command[0].equals("start_game")) {
				if (command.length == 2)
					gc.newGame( Integer.parseInt(command[1] ) );
			} else if(command[0].equals("smudge_stock")) {
				UserIO.println("Olajfoltok:   " + gc.getRobotController().getActualRobot().getOilNumber());
				UserIO.println("Ragacsfoltok: " + gc.getRobotController().getActualRobot().getGlueNumber());
			} else if(command[0].equals("toggle_oil")) {
				gc.getRobotController().toggleOil();
			} else if(command[0].equals("toggle_glue")) {
				gc.getRobotController().toggleGlue();
			} else if(command[0].equals("inputvector")) {
				if (command.length == 3) {
					Vector input = new Vector(Double.parseDouble( command[1] ), Double.parseDouble( command[2] ));
					gc.getRobotController().setInputSpeedVector( input );
				}
			} else if(command[0].equals("jump")) {
				gc.getRobotController().nextTurn();
			} else if(command[0].equals("list_player_robots")) {
				UserIO.println("Játékosrobotok listája:");
				for(PlayerRobot i : gc.getGame().getPlayerRobots()) {
					UserIO.println(i.toString());
				}
			} else if(command[0].equals("list_cleaner_robots")) {
				UserIO.println("Takarítórobotok listája:");
				for(CleanerRobot i : gc.getGame().getCleaningRobots()) {
					UserIO.println(i.toString());
				}
			} else if(command[0].equals("list_smudges")) {
				UserIO.println("Foltok listája:");
				for(Smudge i : gc.getGame().getMap().getSmudges()) {
					UserIO.println(i.toString());
				}
			} else {
				UserIO.println("Nincs ilyen parancs.");
			}
		}
	}
	
}
