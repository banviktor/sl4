package phoebe.basic;

public enum Color {
	RED(1), YELLOW(2), GREEN(3), BLUE(4), PURPLE(5);
	private int value;
	
	private Color(int value) {
		this.value = value;
	}
	
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
