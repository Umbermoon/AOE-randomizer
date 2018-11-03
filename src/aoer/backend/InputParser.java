package aoer.backend;

public class InputParser {

	public static Player[] createPlayerList(String input) {
		Player[] playerList = new Player[8];
		String[] splitNames = input.split("[, ;.]");
		for(int i = 0; i < playerList.length && i < splitNames.length; i++) {
			playerList[i] = new Player(splitNames[i]);
		}
		return playerList;
	}
	
}
