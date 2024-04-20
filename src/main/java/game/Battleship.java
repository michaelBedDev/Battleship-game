package game;

public class Battleship {

	private Player player1;
	private Player player2;

	

	public static void main(String[] args) {
		Battleship battleship = new Battleship();
		Player p = new Player();
		p.createBoardStatus();
		p.placeShips();
		p.showBoats();

	}
}
