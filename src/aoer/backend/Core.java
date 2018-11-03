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
		setAllowedDLC(new DLC[] {DLC.BASE});
		playerList = null;
		allowRepeats = true;
	}
	
	public void setPlayerList(String input) {
		playerList = InputParser.createPlayerList(input);
	}
	
	public void setPlayerList(String[] names) {
		playerList = new Player[names.length];
		for(int i = 0; i < playerList.length; i++) {
			playerList[i] = new Player(names[i]);
		}
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
	
	public void banCiv(Civs c) {
		civMap.disableCiv(c);
	}
	
	public void unbanCiv(Civs c) {
		civMap.enableCiv(c);
	}
	
	public boolean hasAllowedDLC(DLC dlc) {
		for(int i = 0; i < allowedDLC.length; i++) {
			if(allowedDLC[i].equals(dlc)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasAllowedCiv(Civs civ) {
		return civMap.isCivEnabled(civ);
	}
}
