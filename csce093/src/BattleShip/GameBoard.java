package BattleShip;

import java.util.ArrayList;

public class GameBoard {
	int rowCount = 10;
	int colCount = 10;

	final String LINE_END = System.getProperty("line.separator");

	ArrayList<ArrayList<Cell>> cells;
	ArrayList<Ship> myShips = new ArrayList<Ship>();

	public GameBoard(int rowCount, int colCount) {
		this.rowCount = rowCount;
		this.colCount = colCount;

		// create the 2D array of cells
		this.cells = new ArrayList<ArrayList<Cell>>(colCount);
		for (int x = 0; x < rowCount; x++) {
			this.cells.add(x, new ArrayList<Cell>(colCount));
		}

	}

	public String draw() {

		StringBuilder board = new StringBuilder();
		board.append("+----------+\n");// top
		for (int x = 0; x < 10; x++) {

		}
		return board.toString();

		// draw the entire board... I'd use a StringBuilder object to improve
		// speed
		// remember - you must draw one entire row at a time, and don't forget
		// the
		// pretty border...
	}

	// add in a ship if it fully 1) fits on the board and 2) doesn't collide w/
	// an existing ship.
	// Returns true on successful addition; false, otherwise
	public boolean addShip(Ship s, Position sternLocation, HEADING bowDirection) {
		if (OutOfBounds(s, sternLocation, bowDirection)) { // return true if
															// ship fits
			if (myShips.isEmpty()) {
				return true; // fits board and no ships to collide with so add
								// the ship
			} else {// else there is another ship
				for (int y = 0; y < rowCount; y++) {
					for (ArrayList<Cell> cell : cells) {
						if (cell.contains(s)) {
							return false; //cell contains ship - dont add
						} else {
							return true; //cell does not contain ship - add
						}
					}
				}
			}
		} else {// does not fit in grid
			return false;
		}
		
	}

	private boolean OutOfBounds(Ship s, Position sternLocation, HEADING bowDirection) {
		int size = s.getLength();
		switch (size) {
		case 3: // ship size = 3
			if (bowDirection == HEADING.WEST && sternLocation.x == 1) {
				System.out.println("Off the left side");
				return false;
			} else if (bowDirection == HEADING.EAST && sternLocation.x == 8) {
				System.out.println("Off the right side");
				return false;
			} else if (bowDirection == HEADING.NORTH && sternLocation.y == 1) {
				System.out.println("Off the top");
				return false;
			} else if (bowDirection == HEADING.SOUTH && sternLocation.y == 8) {
				System.out.println("Off the bottom");
				return false;
			} else { // ship fits
				return true;
			}
		case 4: // ship size = 4
			if (bowDirection == HEADING.WEST && sternLocation.x == 2) {
				System.out.println("Off the left side");
				return false;
			} else if (bowDirection == HEADING.EAST && sternLocation.x == 7) {
				System.out.println("Off the right side");
				return false;
			} else if (bowDirection == HEADING.NORTH && sternLocation.y == 2) {
				System.out.println("Off the top");
				return false;
			} else if (bowDirection == HEADING.SOUTH && sternLocation.y == 7) {
				System.out.println("Off the bottom");
				return false;
			} else { // ship fits
				return true;
			}
		default:
			System.out.println("Invalid ship size");
			return false;
		}

	}

	// Returns A reference to a ship, if that ship was struck by a missle.
	// The returned ship can then be used to print the name of the ship which
	// was hit to the player who hit it.
	// Ensure you handle missiles that may fly off the grid
	public Ship fireMissle(Position coordinate) {

	}

	// Here's a simple driver that should work without touching any of the code
	// below this point
	public static void main(String[] args) {
		System.out.println("Hello World");
		GameBoard b = new GameBoard(10, 10);
		System.out.println(b.draw());

		Ship s = new Cruiser("Cruiser");
		if (b.addShip(s, new Position(3, 6), HEADING.WEST))
			System.out.println("Added " + s.getName() + "Location is ");
		else
			System.out.println("Failed to add " + s.getName());

		s = new Destroyer("Vader");
		if (b.addShip(s, new Position(3, 5), HEADING.NORTH))
			System.out.println("Added " + s.getName() + "Location is ");
		else
			System.out.println("Failed to add " + s.getName());

		System.out.println(b.draw());

		b.fireMissle(new Position(3, 5));
		System.out.println(b.draw());
		b.fireMissle(new Position(3, 4));
		System.out.println(b.draw());
		b.fireMissle(new Position(3, 3));
		System.out.println(b.draw());

		b.fireMissle(new Position(0, 6));
		b.fireMissle(new Position(1, 6));
		b.fireMissle(new Position(2, 6));
		b.fireMissle(new Position(3, 6));
		System.out.println(b.draw());

		b.fireMissle(new Position(6, 6));
		System.out.println(b.draw());
	}

}
