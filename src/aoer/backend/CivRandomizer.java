package aoer.backend;
import java.util.Random;

import aoer.information.Civs;

public class CivRandomizer {
	
	public static void randomizeCivs(Player[] pList, CivMap cMap, boolean allowedRepeats) {
		Random rand = new Random();
		Civs[] enabledCivs = cMap.getEnabledCivs();
		Civs[] removedCivs = new Civs[pList.length];
		int randomIndex = 0;
		int iter = 0;
		for(Player p : pList) {
			randomIndex = rand.nextInt(enabledCivs.length);
			p.setAssignedCiv(enabledCivs[randomIndex]);
			if(!allowedRepeats) {
				removedCivs[iter] = enabledCivs[randomIndex];
				enabledCivs = removeCiv(enabledCivs, enabledCivs[randomIndex]);
			}
			iter++;
		}
		enabledCivs = addCivs(enabledCivs, removedCivs);
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
	
	private static Civs[] addCivs(Civs[] list1, Civs[] list2) {
		Civs[] combined = new Civs[list1.length + list2.length]; 
		for(int i = 0; i < list1.length; i++) {
			combined[i] = list1[i];
		}
		for(int i = list1.length; i < list1.length + list2.length; i++) {
			combined[i] = list2[i - list1.length];
		}
		return combined;
	}
	
}
