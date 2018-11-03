package aoer.backend;
import java.util.Arrays;

import aoer.information.*;

public class Core {
	private DLC[] allowedDLC;
	private Civs[] allowedCivs;
	private PlayerMap playerMap;
	private CivMap civMap;
	private boolean allowRepeats;
	
	public Core() {
		setAllowedDLC(DLC.getAllDLC());
		civMap = new CivMap(allowedCivs);
		playerMap = new PlayerMap();
		allowRepeats = true;
	}
	
	public void setPlayerList(String input) {
		playerMap.addPlayers(InputParser.createPlayerList(input));
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
		CivRandomizer.randomizeCivs(playerMap, civMap, allowRepeats);
	}
}
