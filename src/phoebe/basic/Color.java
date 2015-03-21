package phoebe.basic;

/**
 * Felsorolt t�pus a robotok lehets�ges sz�neinek a t�rol�s�ra
 */
public enum Color {
	RED, YELLOW, GREEN, BLUE, PURPLE;
	
	@Override
	public String toString(){
		switch(this){
			case RED:
				return "Piros";
			case YELLOW:
				return "S�rga";
			case GREEN:
				return "Z�ld";
			case BLUE:
				return "K�k";
			case PURPLE:
				return "Lila";
			default: return "";
		}
	}
}
