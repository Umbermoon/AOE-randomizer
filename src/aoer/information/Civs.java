package aoer.information;

public enum Civs {
	BRITONS("Britons", DLC.BASE),
	BYZANTINES("Byzantines", DLC.BASE),
	CELTS("Celts", DLC.BASE),
	CHINESE("Chinese", DLC.BASE),
	FRANKS("Franks", DLC.BASE),
	GOTHS("Goths", DLC.BASE),
	JAPANESE("Japanese", DLC.BASE),
	MONGOLS("Mongols", DLC.BASE),
	PERSIANS("Persians", DLC.BASE),
	SARACENS("Saracens", DLC.BASE),
	TEUTONS("Teutons", DLC.BASE),
	TURKS("Turks", DLC.BASE),
	VIKINGS("Vikings", DLC.BASE),
	AZTECS("Aztecs", DLC.BASE),
	HUNS("Huns", DLC.BASE),
	KOREANS("Koreans", DLC.BASE),
	MAYANS("Mayans", DLC.BASE),
	SPANISH("Spanish", DLC.BASE),
	INCAS("Incas", DLC.FORGOTTEN),
	INDIANS("Indians", DLC.FORGOTTEN),
	ITALIANS("Italians", DLC.FORGOTTEN),
	MAGYARS("Magyars", DLC.FORGOTTEN),
	SLAVS("Slavs", DLC.FORGOTTEN),
	BERBERS("Berbers", DLC.AFRICANS),
	ETHIOPIANS("Ethiopians", DLC.AFRICANS),
	MALIANS("Malians", DLC.AFRICANS),
	PORTUGAL("Portuguese", DLC.AFRICANS),
	BURMA("Burmese", DLC.RAJAS),
	KHMER("Khmer", DLC.RAJAS),
	MALAY("Malay", DLC.RAJAS),
	VIETNAM("Vietnam", DLC.RAJAS);
	
	private final String name;
	private final DLC dlc;
	
	Civs(String s, DLC d) {
		name = s;
		dlc = d;
	}
	
	public String getName() {
		return name;
	}
	
	public DLC getDLC() {
		return dlc;
	}
	
	public String getDLCString() {
		return dlc.getName();
	}
	
	public boolean isPartOfDLC(DLC d) {
		return dlc.equals(d);
	}
	
	public static Civs[] getAllCivs() {
		return Civs.class.getEnumConstants();
	}
	
	public static Civs[] getCivsFromDLC(DLC[] dlc) {
		Civs[] civList = getAllCivs();
		byte civCount = 0;
		for(int i = 0; i < dlc.length; i++) {
			civCount += dlc[i].getAmountOfCivs();
		}
		Civs[] newList = new Civs[civCount];
		int numAdded = 0;
		for(int i = 0; i < civList.length; i++) {
			if(civList[i].isPartOfDLC(dlc)) {
				newList[numAdded] = civList[i];
				numAdded++;
			}
		}
		return newList;
	}
	
	public boolean isPartOfDLC(DLC[] d) {
		for(DLC c : d) {
			if(isPartOfDLC(c)){
				return true;
			}
		}
		return false;
	}
	
	public boolean equals(Civs civ) {
		return (name.equals(civ.getName()) && dlc.equals(civ.getDLC()));
	}
	
}
