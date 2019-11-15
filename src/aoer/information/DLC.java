package aoer.information;

public enum DLC {
	BASE("Base Game", 18),
	FORGOTTEN("The Forgotten", 5),
	AFRICANS("The African Kingdoms", 4),
	RAJAS("Rise of the Rajas", 4),
	DEFINITIVE("Definitive Edition", 4);
	
	private final String name;
	private final int civCount;
	
	DLC(String s, int cc){
		name = s;
		civCount = cc;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAmountOfCivs() {
		return civCount;
	}
	
	public boolean equals(DLC d) {
		return name.equals(d.getName());
	}
	
	public static DLC[] getAllDLC() {
		return DLC.class.getEnumConstants();
	}
	
	
}
