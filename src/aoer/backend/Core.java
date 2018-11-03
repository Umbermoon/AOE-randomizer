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
		setAllowedCivs(Civs.getCivsFromDLC(allowedDLC));
	}
	
	public void setAllowedCivs(Civs[] civs) {
		allowedCivs = Arrays.copyOf(civs, civs.length);
		civMap.setCivs(civs);
	}
	
	public void randomizeCivs() {
		CivRandomizer.randomizeCivs(playerList, civMap, allowRepeats);
	}
	
	public void allowRepeats(boolean b) {
		allowRepeats = b;
	}
	
	public DLC[] getAllowedDLC() {
		return allowedDLC;
	}
	
	public Civs[] getAllowedCivs() {
		return allowedCivs;
	}
	
	public CivMap getCivMap() {
		return civMap;
	}
}
