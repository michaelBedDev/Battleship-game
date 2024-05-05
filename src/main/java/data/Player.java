package data;

public class Player {

	private Board board;
	private BoardStatus boardStatus;
	private final Boat[] ships;
	private int shipsSinked; /* min of 0, max of 5. Variable for in game purposes */

	/**
	 * Constructor for Player. Instance of his board (and place ships into it) and
	 * status board
	 */
	public Player() {
		this.ships = new Boat[] { new Boat(5), new Boat(4), new Boat(3), new Boat(3), new Boat(2) };

		this.board = new Board();
		this.boardStatus = new BoardStatus();
		board.placeShips(ships);
	}

	/* Getters & Setters */
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public BoardStatus getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(BoardStatus boardStatus) {
		this.boardStatus = boardStatus;
	}

	public Boat[] getShips() {
		return ships;
	}

	public int getShipsSinked() {
		return shipsSinked;
	}

	public void setShipsSinked(int shipsSinked) {
		this.shipsSinked = shipsSinked;
	}
}
