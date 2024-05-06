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
	// Añadir cambiar length F55
	// Añadir que si la casilla ya está descubierta, volver a tirar
	//añadir que es tu turno o del rival
	// Revisar bug de terminado o no
	//añadir traducir coordenadas de la cpu

	/**
	 * Gameloop
	 */
	public void gameLoop() {
		player1 = new Player();
		cpu = new CPU();
		view = new UI();
		view.showBothBoards(player1.getBoard(), cpu.getBoard());
		view.showBothBoards(player1.getBoardStatus(), cpu.getBoardStatus());

		do {
			playerTurn(player1, cpu, view);
			cpuTurn(player1, cpu, view);
		} while (player1.getShipsSinked() < 5 || cpu.getShipsSinked() < 5);
	}

	public void playerTurn(Player p1, Player cpu, UI view) {

		int[] coordinates = view.askCoordinates();
		int y = coordinates[0];
		int x = coordinates[1];

		if (cpu.getBoard().getTablero()[y][x] == 2) { // if ship
			cpu.getBoardStatus().setCell(y, x, 2); // set boardSt to ship
			view.showMessage("!Barco!");
			player1.setShipsSinked(player1.getShipsSinked()+1);

			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			playerTurn(p1, cpu, view);
			
		} else {
			cpu.getBoardStatus().setCell(y, x, 1);
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			view.showMessage("¡Agua!");
		}
	}

	private void cpuTurn(Player p1, Player cpu, UI view) {

		int[] coordinates = cpu.getBoard().generateCoordinates();
		int y = coordinates[0];
		int x = coordinates[1];
		
		try {

			Thread.sleep(3000);
			view.showMessage("Coordenadas: " + y + ":" + x);
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (player1.getBoard().getTablero()[y][x] == 2) {
			player1.getBoardStatus().setCell(y, x, 2); // set boardSt to ship
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			
			view.showMessage("¡Barco!");
			cpu.setShipsSinked(cpu.getShipsSinked()+1);
			
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			cpuBoatTurn(p1, cpu, view, y, x);
			
		} else {
			player1.getBoardStatus().setCell(y, x, 1);
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			view.showMessage("¡Agua!");
		}
	}
	
	
	
	
	public void cpuBoatTurn(Player p1, Player cpu, UI view, int y, int x) {
		
		int[] coordinates = cpu.getBoard().generateCoordinatesAround(y, x);
		
		y = coordinates[0];
		x = coordinates[1];
		
		try {

			Thread.sleep(3000);
			view.showMessage("Coordenadas: " + y + ":" + x);
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (player1.getBoard().getTablero()[y][x] == 2) {
			player1.getBoardStatus().setCell(y, x, 2); // set boardSt to ship
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			
			view.showMessage("¡Barco!");
			cpu.setShipsSinked(cpu.getShipsSinked()+1);
			
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			cpuBoatTurn(p1, cpu, view, y, x);
			
		} else {
			player1.getBoardStatus().setCell(y, x, 1);
			view.showBothBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			view.showMessage("¡Agua!");
		}
	}
}