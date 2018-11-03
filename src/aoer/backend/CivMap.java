package aoer.backend;
import aoer.information.Civs;
import java.util.*;

public class CivMap {
	HashMap<Civs, Boolean> cMap;
	
	public CivMap() {
		cMap = new HashMap<Civs, Boolean>();
	}
	
	public CivMap(Civs[] civList) {
		cMap = new HashMap<Civs, Boolean>();
		for(Civs civ : civList) {
			cMap.put(civ, Boolean.TRUE);   //yay wrapper classes
		}
	}

	public void disableCiv(Civs civ) {
		cMap.replace(civ, Boolean.FALSE);
	}
	
	public void enableCiv(Civs civ) {
		cMap.replace(civ, Boolean.TRUE);
	}
	
	public void addCivs(Civs[] civList) {
		for(Civs civ : civList) {
			cMap.put(civ, Boolean.TRUE);
		}
	}
	
	public void removeCivs(Civs[] civList) {
		for(Civs civ : civList) {
			cMap.remove(civ);
		}
	}
	
	public void setCivs(Civs[] civList) {
		cMap.clear();
		for(Civs civ : civList) {
			cMap.put(civ, Boolean.TRUE);
		}
	}
	
	public boolean isCivEnabled(Civs c) {
		Boolean b = cMap.get(c);
		if(b == null || !b)
			return false;
		else
			return true;
	}
	
	public Civs[] getEnabledCivs() {
		Set<Civs> cSet = cMap.keySet();
		Civs[] cList = new Civs[cSet.size()];
		cSet.toArray(cList);
		Civs[] cArray = new Civs[cSet.size()];
		int enabledCount = 0;
		for(int i = 0; i < cList.length; i++) {         //move all enabled civs to a different array
			if(cMap.get(cList[i])) {
				cArray[enabledCount] = cList[i];
				enabledCount++;
			}
		}
		Civs[] lastArray = new Civs[enabledCount];    //move enabled civs to a correctly sized array
		for(int i = 0; i < enabledCount; i++) {
			lastArray[i] = cArray[i];
		}
		return lastArray;
	}
}
