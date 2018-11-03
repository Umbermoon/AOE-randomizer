package aoer.backend;
import java.util.Random;

import aoer.information.Civs;

public class CivRandomizer {
	
	public static void randomizeCivs(Player[] pList, CivMap cMap, boolean allowedRepeats) {
		Random rand = new Random();
		Civs[] enabledCivs = cMap.getEnabledCivs();
		int randomIndex = 0;
		for(Player p : pList) {
			randomIndex = rand.nextInt(enabledCivs.length);
			p.setAssignedCiv(enabledCivs[randomIndex]);
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
