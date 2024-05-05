package data;

public class Boat {

	private int coordX;
	private int coordY;
	private int size;
	private boolean axis;
	

	
	

	public Boat(int size) {
		this.size = size;
	}
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


