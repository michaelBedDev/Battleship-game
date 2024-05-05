package game;

import data.CPU;
import data.Player;
import view.UI;

public class Battleship {

	Player player1;
	CPU cpu;
	UI view;

	public static void main(String[] args) {
		Battleship battleship = new Battleship();
		battleship.gameLoop();
	}

	// Añadir al descubrirse el barco que enseñe el agua a su alrededor
	// Añadir mensaje de agua y barco después del turno
	// Añadir cpu
	// Añadir timesleep para cpu
	// Revisar bug de terminado o no y añadir mensaje
	// arreglar input letras arreglar con A10 arreglar con string de un caracter

	/**
	 * Gameloop
	 */
	public void gameLoop() {
		Player p1 = new Player();
		Player cpu = new Player();
		UI view = new UI();
		view.showBothBoards(p1.getBoard(), cpu.getBoard());
		view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());

		do {
			playerTurn(p1, cpu, view);
			cpuTurn(p1, cpu, view);
		} while (p1.getShipsSinked() < 5 || cpu.getShipsSinked() < 5);
	}

	public void playerTurn(Player p1, Player cpu, UI view) {

		int[] coordinates = view.askCoordinates();
		int y = coordinates[0];
		int x = coordinates[1];

		if (cpu.getBoard().getTablero()[y][x] == 2) { // if ship
			cpu.getBoardStatus().setCell(y, x, 2); // set boardSt to ship

			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			playerTurn(p1, cpu, view);

		} else {
			cpu.getBoardStatus().setCell(y, x, 1);
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
		}
	}

	private void cpuTurn(Player p1, Player cpu2, UI view) {

	}
}