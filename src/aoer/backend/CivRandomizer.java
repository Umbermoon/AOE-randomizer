package aoer.backend;
import java.util.Random;
import java.util.Set;

import aoer.information.Civs;

public class CivRandomizer {
	
	public static void randomizeCivs(PlayerMap pMap, CivMap cMap, boolean allowedRepeats) {
		Random rand = new Random();
		Civs[] enabledCivs = cMap.getEnabledCivs();
		Set<Player> playerList = pMap.getPlayerList();
		int randomIndex = 0;
		for(Player p : playerList) {
			randomIndex = rand.nextInt(enabledCivs.length);
			pMap.setPlayerCiv(p, enabledCivs[randomIndex]);
			if(!allowedRepeats)
				enabledCivs = removeCiv(enabledCivs, enabledCivs[randomIndex]);
		}
	}
	
	private static Civs[] removeCiv(Civs[] list, Civs removeMe) {
		Civs[] newList = new Civs[list.length-1];
		int i = 0;
		for(Civs c : list) {
			if(c != removeMe) {
				newList[i] = c;
				i++;
			}	
		}
		return list;
	}
	
}
