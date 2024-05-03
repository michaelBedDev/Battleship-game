package game;

public class Player {

	private Board board;
	private BoardStatus boardStatus;
	private final Boat[] ships;
	private int shipsSinked;

	
	public Player() {
		this.ships = new Boat[]{new Boat(5),new Boat(4),new Boat(3),new Boat(3),new Boat(2)};
		
		this.board = new Board();
		this.boardStatus = new BoardStatus();
		board.placeShips(ships);
	}

	

	//Getters & Setters
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
