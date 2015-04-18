package phoebe;

import java.util.Arrays;

import phoebe.basic.Vector;
import phoebe.game.CleanerRobot;
import phoebe.game.GameController;
import phoebe.game.PlayerRobot;
import phoebe.game.Smudge;

public class Application {
	
	private static GameController gc = null;

	public static void main(String[] args) {
		if(!Arrays.asList(args).contains("test")){
			System.out.println("Phoebe - PROTOTÍPUS");
			System.out.println("barbershop_quartet");
			System.out.println("");
			printCommands();
		}
		
		boolean exit = false;
		gc = new GameController();
		while (!exit) {
			String[] command = UserIO.getCommand();
			if(command[0].equals("exit")){
				exit = true;
			} else if(command[0].equals("commands")) {
				printCommands();
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
					UserIO.println("\t" + i.toString());
				}
			} else if(command[0].equals("list_cleaner_robots")) {
				UserIO.println("Takarítórobotok listája:");
				for(CleanerRobot i : gc.getGame().getCleaningRobots()) {
					UserIO.println("\t" + i.toString());
				}
			} else if(command[0].equals("list_smudges")) {
				UserIO.println("Foltok listája:");
				for(Smudge i : gc.getGame().getMap().getSmudges()) {
					UserIO.println("\t" + i.toString());
				}
			} else {
				UserIO.println("Nincs ilyen parancs.");
			}
		}
	}
	
	public static void printCommands(){
		System.out.println("A parancsok listája:");
		System.out.println("randomization [on/off] - Véletlenszerűség kapcsolása");
		System.out.println("questions [on/off]     - Kérdezés kapcsolása");
		System.out.println("start_game <2-5>       - Játék indítása 2-5 játékossal");
		System.out.println("smudge_stock           - Foltkészlet listázása");
		System.out.println("toggle_oil             - Olajlerakási szándék");
		System.out.println("toggle_glue            - Ragacslerakási szándék");
		System.out.println("inputvector <x> <y>    - Módosítóvektor megadása");
		System.out.println("jump                   - Ugrás");
		System.out.println("list_player_robots     - Listázza a játékos robotokat");
		System.out.println("list_cleaner_robots    - Listázza a takarítórobotokat");
		System.out.println("list_smudges           - Listázza a foltokat");
		System.out.println("commands               - Listázza a parancsokat");
		System.out.println("exit                   - Kilép");
		System.out.println("");
	}
	
}
