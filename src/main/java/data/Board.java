package data;

import java.security.SecureRandom;

public class Board {

	private int[][] tablero;
	private static SecureRandom rand = new SecureRandom();

	public Board() {
		this.tablero = new int[10][10];
	}

	/**
	 * Place all the ships into board
	 * 
	 * @param ships the array of the Player´s ships
	 */
	public void placeShips(Boat[] ships) {
		for (int i = 0; i < ships.length; i++) {
			Boat boat = ships[i];
			placeShip(boat);
		}
	}

	/**
	 * Random generate the position and axis and set them. Place the ship
	 * 
	 * @param ship
	 */
	private void placeShip(Boat ship) {

		boolean validPosition = false;

		int positionX;
		int positionY;
		boolean axis;

		do {
			positionX = rand.nextInt(10);
			positionY = rand.nextInt(10);
			axis = rand.nextBoolean(); // true if horizontal, false if vertical

			ship.setAxis(axis);
			ship.setCoordX(positionX);
			ship.setCoordY(positionY);

			validPosition = validatePosition(ship);
		} while (!validPosition);

		placeShipInto(ship);
	}

	/**
	 * Check if the board position is valid and ship can be placed
	 * 
	 * @param ship
	 * @return true if it´s valid position or false if isn´t
	 */
	private boolean validatePosition(Boat ship) {
		if (!enoughSpace(ship)) {
			return false; /* If the ship is out the board, return false directly */
		}
		return enoughSpaceAround(ship);
	}

	/**
	 * Check with a loop if the ship can be placed properly into board
	 * 
	 * @param ship
	 * @return true if it´s valid position or false if isn´t
	 */
	private boolean enoughSpace(Boat ship) {
		if (ship.isAxis()) {
			if (ship.getCoordX() + ship.getSize() > 10) {
				return false;
			}
			for (int i = 0; i < ship.getSize(); i++) {
				if (this.tablero[ship.getCoordY()][ship.getCoordX() + i] != 0) {
					return false;
				}
			}
		} else {
			if (ship.getCoordY() + ship.getSize() > 10) {
				return false;
			}
			for (int i = 0; i < ship.getSize(); i++) {
				if (this.tablero[ship.getCoordY() + i][ship.getCoordX()] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if there´s enough space around the desired position. Boats can´t be
	 * touching each other even diagonally. Checking the space around the boat with
	 * two loops.
	 * 
	 * @param ship
	 * @return true if it´s valid position or false if isn´t
	 */
	private boolean enoughSpaceAround(Boat ship) {
		if (ship.isAxis()) {
			for (int i = -1; i <= 1; i++) { /* loop starts at positionY -1 ends at posY +1 */
				for (int j = -1; j <= ship.getSize(); j++) { /* loop starts at posX -1 ends at posX == size */

					if ((ship.getCoordX() + j) < 0 || (ship.getCoordX() + j > 9)) { /* is in limit X */
						continue;
					} else if ((ship.getCoordY() + i) < 0 || (ship.getCoordY() + i > 9)) { /* is in limit Y */
						continue;
					} else if (this.tablero[ship.getCoordY() + i][ship.getCoordX()
							+ j] == 1) { /* check if cell is marked as occupied */
						// space
						return false;
					}
				}
			}
		} else {
			for (int i = -1; i <= ship.getSize(); i++) { /* same as before */
				for (int j = -1; j <= 1; j++) {

					// Juntar ifs
					if ((ship.getCoordX() + j) < 0 || (ship.getCoordX() + j > 9)) {
						continue;
					} else if ((ship.getCoordY() + i) < 0 || (ship.getCoordY() + i > 9)) {
						continue;
					} else if (this.tablero[ship.getCoordY() + i][ship.getCoordX() + j] == 1) {
						return false;
					}
				}
			}
		}
		return true;
	}

	
	/**
	 * Finally place the ship
	 * 
	 * @param ship
	 */
	private void placeShipInto(Boat ship) {
		updateSpaceAround(ship);
		updateBoatPlace(ship);
	}

	/**
	 * Update the space around the ship after placed.
	 * 
	 * @param ship
	 */
	private void updateSpaceAround(Boat ship) {
		if (ship.isAxis()) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= ship.getSize(); j++) {
					if ((ship.getCoordX() + j) < 0 || (ship.getCoordX() + j > 9)) { /* is in limit X */
						continue;
					} else if ((ship.getCoordY() + i) < 0 || (ship.getCoordY() + i > 9)) { /* is in limit Y */
						continue;
					} else {
						this.tablero[ship.getCoordY() + i][ship.getCoordX() + j] = -1; /* update the cell to -1 */
					}
				}
			}
		} else {
			for (int i = -1; i <= ship.getSize(); i++) { /* same as before */
				for (int j = -1; j <= 1; j++) {

					if ((ship.getCoordX() + j) < 0 || (ship.getCoordX() + j > 9)) {
						continue;
					} else if ((ship.getCoordY() + i) < 0 || (ship.getCoordY() + i > 9)) {
						continue;
					} else {
						this.tablero[ship.getCoordY() + i][ship.getCoordX() + j] = -1;
					}
				}
			}
		}
	}

	/**
	 * Update the cells to 1 in board. Ship is now placed
	 * 
	 * @param ship
	 */
	private void updateBoatPlace(Boat ship) {
		if (ship.isAxis()) {
			for (int j = 0; j < ship.getSize(); j++) {
				this.tablero[ship.getCoordY()][ship.getCoordX() + j] = 1;
			}

		} else {
			for (int i = 0; i < ship.getSize(); i++) {
				this.tablero[ship.getCoordY() + i][ship.getCoordX()] = 1;
			}
		}
	}

	// Getters & Setters
	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public void setCell(int y, int x, int value) {
		this.tablero[y][x] = value;
	}

	public int getCell(int y, int x) {
		return tablero[y][x];
	}
}
