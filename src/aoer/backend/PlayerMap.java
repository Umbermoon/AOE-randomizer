package aoer.backend;
import java.util.HashMap;
import java.util.Set;

import aoer.information.Civs;

public class PlayerMap {
	HashMap<Player, Civs> pMap;
	
	public PlayerMap() {
		pMap = new HashMap<Player, Civs>();
	}
	
	public PlayerMap(Player[] pList) {
		for(Player p : pList) {
			if(p != null)
				pMap.put(p, null);
		}
	}
	
	public void setPlayerCiv(Player p, Civs c) {
		pMap.put(p, c);
	}
	
	public void addPlayers(Player[] pList) {
		for(int i = 0; i < pList.length; i++) {
			pMap.put(pList[i], null);
		}
	}
	
	public Set<Player> getPlayerList() {
		return pMap.keySet();
	}
	
}
