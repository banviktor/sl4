package phoebe.basic;

public enum Color {
	RED, YELLOW, GREEN, BLUE, PURPLE;
	
	@Override
	public String toString(){
		switch(this){
			case RED:
				return "Red";
			case YELLOW:
				return "Yellow";
			case GREEN:
				return "Green";
			case BLUE:
				return "Blue";
			case PURPLE:
				return "Purple";
			default: return "";
		}
	}
}
