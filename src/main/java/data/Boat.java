package data;

public class Boat {

	private int coordX;
	private int coordY;
	private int size;
	private boolean axis; /* true if horizontal ship, false if vertical */

	/**
	 * Constructor without params
	 */
	public Boat() {
		super();
	}

	/**
	 * Constructor for Boat with param size (length)
	 * 
	 * @param size
	 */
	public Boat(int size) {
		this.size = size;
	}

	/* Getters & Setters */
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAxis() {
		return axis;
	}

	public void setAxis(boolean axis) {
		this.axis = axis;
	}
}