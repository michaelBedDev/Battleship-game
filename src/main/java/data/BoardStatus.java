package data;

public class BoardStatus {

	private int[][] tablero;

	public BoardStatus() {
		this.tablero = new int[10][10];
	}
	
	// createBoard
	public void createBoardStatus() {
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero.length; j++) {
				this.tablero[i][j] = 0;
			}
		}
	}

	public void setCell(int y, int x, int value) {
		this.tablero[y][x] = value;
	}
	
	public int getCell(int y, int x) {
		return tablero[y][x];
	}
	
	//Getters & Setters
	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}
}
