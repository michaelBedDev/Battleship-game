package game;

import java.util.Scanner;

public class Battleship {

	Player player1;
	Player cpu;

	public static void main(String[] args) {
		Battleship battleship = new Battleship();
		battleship.play();
	}

	//Añadir al descubrirse el barco que enseñe el agua a su alrededor
	//Añadir mensaje de agua y barco después del turno
	//Añadir cpu
	//Añadir timesleep para cpu
	//Revisar bug de terminado o no y añadir mensaje
	//arreglar input letras arreglar con A10 arreglar con string de un caracter
	
	
	public void play() {
		Player p1 = new Player();
		Player cpu = new Player();
		showBothBoards(p1.getBoard(), cpu.getBoard());
		showBothStatusBoards(p1.getBoardStatus(), cpu.getBoardStatus());
		
		do {
			playerTurn(p1, cpu);
			cpuTurn(p1, cpu);
		} while (p1.getShipsSinked() < 5 || cpu.getShipsSinked() < 5);
	}

	public void playerTurn(Player p1, Player cpu) {
		//BoardStatus: 0 default(not seen yet), 1 water 2 ships  //Status: 0 default, -1 occupied space 1 ship
		
		
		int[] coordinates = askCoordinates();
		int y = coordinates[0]; 
		int x = coordinates[1];
		
		if (cpu.getBoard().getTablero()[y][x] == 1) {     //if ship 
			cpu.getBoardStatus().setCell(y, x, 2); ; //set board to ship
	
			showBothStatusBoards(p1.getBoardStatus(), cpu.getBoardStatus());
			playerTurn(p1, cpu);
			
		} else {
			cpu.getBoardStatus().setCell(y, x, 1);
			showBothStatusBoards(p1.getBoardStatus(), cpu.getBoardStatus());
		}
	}
	
	public void cpuTurn(Player p1, Player cpu) {
		
	}
	
	
	
	
	// mostrar tablero
	public void showStatus(Player p) {
		for (int[] row : p.getBoardStatus().getTablero()) {
			for (int i : row) {
				switch (i) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// mostrar Barcos
	public void showBoats(Player p) {
		for (int[] row : p.getBoard().getTablero()) {
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
	
	public void showPlayerBoats(Player p1) {
		System.out.println("*MAPA DE TUS BARCOS*");
		showBoats(p1);
	}

	// mostrar ambos tableros SAME SIZE!!
	public void showBothStatusBoards(BoardStatus boardStatus, BoardStatus boardStatus2) {

		char letter = 'A'; //print letter
		int number = 1;
		
		System.out.printf("%16s\t\t%15s\n", "*TUS BARCOS*", "*EL RIVAL*");
		
		for (int i = 0; i < 2; i++) {
			System.out.print(" ".repeat(2));
			for (int j = 0; j < boardStatus2.getTablero().length; j++) {
				System.out.print(number + " ");
				number++;
			}
			number = 1;
			System.out.print(" ".repeat(9));
		}
		System.out.println();
		
		
		for (int posY = 0; posY < boardStatus.getTablero().length; posY++) {

			System.out.print(letter + "-");
			// Board 1
			for (int x1 = 0; x1 < boardStatus.getTablero()[posY].length; x1++) {
				switch (boardStatus.getTablero()[posY][x1]) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}

			}

			System.out.print("\t\t" + letter + "-"); // Spacing

			// Board 2
			for (int x2 = 0; x2 < boardStatus2.getTablero()[posY].length; x2++) {
				switch (boardStatus2.getTablero()[posY][x2]) {
				case 0 -> System.out.print("🔷");
				case 1 -> System.out.print("⬛"); // agua
				case 2 -> System.out.print("🔲"); // barco
				}
			}
			letter++;
			System.out.println();
		}
		System.out.println();
	
	}
	
	
	// mostrar ambos tableros de barcos SAME SIZE!!
		public void showBothBoards(Board board, Board board2) {

			char letter = 'A'; //print letter
			int number = 1;
			
			System.out.printf("%16s\t\t%15s\n", "*TUS BARCOS*", "*EL RIVAL*");
			
			for (int i = 0; i < 2; i++) {
				System.out.print(" ".repeat(2));
				for (int j = 0; j < board2.getTablero().length; j++) {
					System.out.print(number + " ");
					number++;
				}
				number = 1;
				System.out.print(" ".repeat(9));
			}
			System.out.println();
			
			
			
			for (int posY = 0; posY < board.getTablero().length; posY++) {

				System.out.print(letter + "-");
				// Board 1
				for (int x1 = 0; x1 < board.getTablero()[posY].length; x1++) {

					switch (board.getCell(posY, x1)) {
					case 0 -> System.out.print("🔷");
					case -1 -> System.out.print("⬛"); 
					case 1 -> System.out.print("🔲"); 
					}
					
				}

				System.out.print("\t\t" + letter + "-"); // Spacing

				// Board 2
				for (int x2 = 0; x2 < board2.getTablero()[posY].length; x2++) {
					switch (board2.getCell(posY, x2)) {
					case 0 -> System.out.print("🔷");
					case -1 -> System.out.print("⬛"); 
					case 1 -> System.out.print("🔲"); 
					}
				}
				letter++;
				System.out.println();
			}
			System.out.println();
		}
	
	private int[] askCoordinates() { //need test ARREGLAR
		String input;
		boolean isValid = false;
		int letter; int number;
		Scanner sc = new Scanner(System.in);
		
		do {
			boolean validateFormat;
			boolean validateLength;
			do {
				do {
					System.out.println("Introduce las coordenadas [A1:J10]");
					input = sc.nextLine();
				} while (input.equals(""));
				
				validateFormat = Character.isAlphabetic(input.charAt(0)) && Character.isDigit(input.charAt(1));
				validateLength = (input.length() == 2);
				
			} while (!validateLength && !validateFormat); 
			
			
			letter = input.toUpperCase().charAt(0) - 'A'; //A in ASCII = 65 so (65-65 A would be 0)
			number = Integer.parseInt(input.substring(1)) - 1;
			
			boolean validLetter = letter >-1 && letter <10;
			
			isValid = validateFormat && validateLength && validLetter;
			
			if(!isValid) {
				System.out.println("Por favor, introduce nuevamente las coordenadas");
			}
		} while (!isValid || input.equals(""));
		
		return new int[] {letter,number};
	}
}