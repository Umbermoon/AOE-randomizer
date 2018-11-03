package aoer.backend;
import java.util.Arrays;

import aoer.information.*;

public class Core {
	private DLC[] allowedDLC;
	private Civs[] allowedCivs;
	private Player[] playerList;
	private CivMap civMap;
	private boolean allowRepeats;
	
	public Core() {
		civMap = new CivMap();
		setAllowedDLC(DLC.getAllDLC());
		playerList = null;
		allowRepeats = true;
	}
	
	public void setPlayerList(String input) {
		playerList = InputParser.createPlayerList(input);
	}
	
	public void setAllowedDLC(DLC[] dlc) {
		allowedDLC = Arrays.copyOf(dlc, dlc.length);
		String test = "Allowed DLC: ";
		for(int i = 0; i < allowedDLC.length-1; i++) {
			test += allowedDLC[i].getName() + ", ";
		}
		test += allowedDLC[allowedDLC.length-1].getName();
		System.out.println(test);
		setAllowedCivs(Civs.getCivsFromDLC(allowedDLC));
	}
	
	public void setAllowedCivs(Civs[] civs) {
		allowedCivs = Arrays.copyOf(civs, civs.length);
		String test = "Allowed Civs: ";
		for(int i = 0; i < allowedCivs.length-1; i++) {
			test += allowedCivs[i].getName() + "\n";
		}
		test += allowedCivs[allowedCivs.length-1].getName();
		System.out.println(test);
		civMap.setCivs(civs);
	}
	
	public void randomizeCivs() {
		CivRandomizer.randomizeCivs(playerList, civMap, allowRepeats);
	}
}
