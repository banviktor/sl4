package phoebe.basic;

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
