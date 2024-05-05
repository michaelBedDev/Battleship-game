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
		battleship.play();
	}

	// Añadir al descubrirse el barco que enseñe el agua a su alrededor
	// Añadir mensaje de agua y barco después del turno
	// Añadir cpu
	// Añadir timesleep para cpu
	// Revisar bug de terminado o no y añadir mensaje
	// arreglar input letras arreglar con A10 arreglar con string de un caracter

	public void play() {
		Player p1 = new Player();
		Player cpu = new Player();
		UI view = new UI();
		view.showBothBoards(p1, cpu);
		view.showBothStatusBoards(p1, cpu);

		do {
			playerTurn(p1, cpu, view);
			cpuTurn(p1, cpu, view);
		} while (p1.getShipsSinked() < 5 || cpu.getShipsSinked() < 5);
	}

	private void cpuTurn(Player p1, Player cpu2, UI view) {

		
	}

	public void playerTurn(Player p1, Player cpu, UI view) {
		// BoardStatus: 0 default(not seen yet), 1 water 2 ships //Status: 0 default, -1
		// occupied space 1 ship

		int[] coordinates = view.askCoordinates();
		int y = coordinates[0];
		int x = coordinates[1];

		if (cpu.getBoard().getTablero()[y][x] == 1) { // if ship
			cpu.getBoardStatus().setCell(y, x, 2);
			; // set board to ship

			view.showBothStatusBoards(p1, cpu);
			playerTurn(p1, cpu, view);

		} else {
			cpu.getBoardStatus().setCell(y, x, 1);
			view.showBothStatusBoards(p1, cpu);
		}
	}
	
	

}