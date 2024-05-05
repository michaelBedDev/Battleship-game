package data;

public class BoardStatus extends Board{



	/**
	 * Constructor for BoardStatus and inicialize them with 0s
	 */
	public BoardStatus() {
		
		for (int i = 0; i < super.tablero.length; i++) {
			for (int j = 0; j < super.tablero.length; j++) {
				this.tablero[i][j] = 0;
			}
		}
	}
}
