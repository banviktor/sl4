package phoebe.basic;

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
}
