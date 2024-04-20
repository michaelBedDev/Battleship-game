package game;

import java.util.Random;

public class Player {

	private int[][] board = new int[10][10]; // 1 for ships, -1 for occupied space, 0 default
	private int[][] boardStatus = new int[10][10]; // 1 for water, 2 for ships, 0 default(not seen yet)
	private int shipsSinked; // +number of cells discovered

	// createBoard
	public void createBoardStatus() {
		for (int i = 0; i < this.getBoardStatus().length; i++) {
			for (int j = 0; j < this.getBoardStatus().length; j++) {
				this.getBoardStatus()[i][j] = 0;
			}
		}
	}

	public void placeShips() {
		placeShip(5);
		showBoats();
		placeShip(4);
		showBoats();
		placeShip(3);
		showBoats();
		placeShip(3);
		showBoats();
		placeShip(2);
	}

	// mostrar tablero
	public void showStatus() {
		for (int[] row : this.boardStatus) {
			for (int i : row) {
				switch (i) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}
			}
			System.out.println();
		}
	}

	public void showBoats() {
		for (int[] row : this.board) {
			for (int i : row) {
				switch (i) {
				case 0 -> System.out.print("🔷");
				case -1 -> System.out.print("⬛"); // agua
				case 1 -> System.out.print("🔲"); // barco
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// posicionarBarcos
	public void placeShip(int size) {
		Random rand = new Random();
		boolean validPosition = false;

		int positionX;
		int positionY;
		boolean axis;

		do {
			positionX = rand.nextInt(10);
			positionY = rand.nextInt(10);
			axis = rand.nextBoolean(); // true if horizontal, false if vertical

			validPosition = validatePosition(this.getBoard(), positionX, positionY, axis, size);

		} while (!validPosition);

		placeShipInto(this.getBoard(), positionX, positionY, axis, size);

	}

	public boolean validatePosition(int[][] board, int positionX, int positionY, boolean axis, int size) {
	    if (!enoughSpace(board, positionX, positionY, axis, size)) {
	        return false; // Si no hay suficiente espacio, retornamos false sin verificar el espacio alrededor
	    }
	    return enoughSpaceAround(board, positionX, positionY, axis, size);
	}


	private boolean enoughSpace(int[][] board, int positionX, int positionY, boolean axis, int size) {
		if (axis) {
			if (positionX + size > 10) {
				return false;
			}
			for (int i = 0; i < size; i++) {
				if (board[positionY][positionX + i] != 0) {
					return false;
				}
			}
		} else {
			if (positionY + size > 10) {
				return false;
			}
			for (int i = 0; i < size; i++) {
				if (board[positionY + i][positionX] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean enoughSpaceAround(int[][] board, int positionX, int positionY, boolean axis, int size) { //Revisar!!
		if (axis) { // horizontal
			for (int i = -1; i <= 1; i++) { // starts at positionY -1 ends at posY +1
				for (int j = -1; j <= size; j++) { // starts at posX -1 ends at posX == size

					if ((positionX + j) < 0 || (positionX + j > 9)) { // is in limit X
						continue;
					} else if ((positionY + i) < 0 || (positionY + i > 9)) { // is in limit Y
						continue;
					} else if (board[positionY + i][positionX + j] == 1) { // is marked an occupied space
						return false;
					}
				}
			}

		} else { // vertical 
			for (int i = -1; i <= size; i++) { // same as before
				for (int j = -1; j <= 1; j++) {

					if ((positionX + j) < 0 || (positionX + j > 9)) { // is in limit X
						continue;
					} else if ((positionY + i) < 0 || (positionY + i > 9)) { // is in limit Y
						continue;
					} else if (board[positionY + i][positionX + j] == 1) { // is marked an occupied space
						return false;
					}
				}
			}
		}
		return true;
	}

	private void placeShipInto(int[][] board, int positionX, int positionY, boolean axis, int size) {

		updateSpaceAround(board, positionX, positionY, axis, size);
		updateBoatPlace(board, positionX, positionY, axis, size);

	}

	private void updateSpaceAround(int[][] board, int positionX, int positionY, boolean axis, int size) {
		if (axis) { // horizontal
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= size; j++) {

					if ((positionX + j) < 0 || (positionX + j > 9)) {
						continue;
					} else if ((positionY + i) < 0 || (positionY + i > 9)) {
						continue;
					} else { // set to -1 to see occupiedSpace
						board[positionY + i][positionX + j] = -1;
					}
				}
			}
		} else { // vertical
			for (int i = -1; i <= size; i++) { // same as before
				for (int j = -1; j <= 1; j++) {

					if ((positionX + j) < 0 || (positionX + j > 9)) {
						continue;
					} else if ((positionY + i) < 0 || (positionY + i > 9)) {
						continue;
					} else {
						board[positionY + i][positionX + j] = -1;
					}
				}
			}
		}
	}

	private void updateBoatPlace(int[][] board, int positionX, int positionY, boolean axis, int size) {
		if (axis) {
			for (int j = 0; j < size; j++) {
				board[positionY][positionX + j] = 1;
			}

		} else {
			for (int i = 0; i < size; i++) {
				board[positionY + i][positionX] = 1;
			}
		}
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int[][] getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(int[][] boardStatus) {
		this.boardStatus = boardStatus;
	}
}
