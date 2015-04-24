package phoebe.basic;

/**
 * Felsorolt típus a robotok lehetséges színeinek a tárolására
 */
public enum Color {
	RED, YELLOW, GREEN, BLUE, PURPLE;
	
	@Override
	public String toString(){
		switch(this){
			case RED:
				return "Piros";
			case YELLOW:
				return "Sárga";
			case GREEN:
				return "Zöld";
			case BLUE:
				return "Kék";
			case PURPLE:
				return "Lila";
			default: return "";
		}
	}
	
	public int toInt() {
		switch(this){
		case RED:
			return 0;
		case YELLOW:
			return 1;
		case GREEN:
			return 2;
		case BLUE:
			return 3;
		case PURPLE:
			return 4;
		default: return 0;
		}
	}
}
