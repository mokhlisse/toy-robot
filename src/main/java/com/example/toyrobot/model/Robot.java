package com.example.toyrobot.model;

/**
 * Robot class. Each robot has 2 coordinates x and y; it has also a
 * facing(direction)
 * 
 * @author <a href="mailto:mokhlisse_badre@yahoo.fr">Badre-Edine Mokhlisse</a>
 *
 */
public class Robot {

	private static final int MAX_X = 5;
	private static final int MIN_X = 0;
	private static final int MAX_Y = 5;
	private static final int MIN_Y = 0;

	private int x;
	private int y;
	private FacingEnum facing;

	public int getX() {
		return x;
	}

	/**
	 * control values set to x
	 * 
	 * @param x
	 */
	public void setX(int x) {
		if (x > MAX_X || x < MIN_X) {
			throw new IllegalArgumentException("invalid x value(" + x + ")");
		}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	/**
	 * control values set to y
	 * 
	 * @param y
	 */
	public void setY(int y) {
		if (y > MAX_Y || y < MIN_Y) {
			throw new IllegalArgumentException("invalid y value(" + y + ")");
		}
		this.y = y;
	}

	public FacingEnum getFacing() {
		return facing;
	}

	public void setFacing(FacingEnum facing) {
		this.facing = facing;
	}

	public void incrementX() {
		setX(getX() + 1);
	}

	public void decrementX() {
		setX(getX() - 1);
	}

	public void incrementY() {
		setY(getY() + 1);
	}

	public void decrementY() {
		setY(getY() - 1);
	}

	public void place(int x, int y, FacingEnum facingEnum) {
		if (x > MAX_X || x < MIN_X || y > MAX_Y || y < MIN_Y) {
			throw new IllegalArgumentException("invalid x or y value(" + x + ", " + y + ")");
		}
		this.x = x;
		this.y = y;
		setFacing(facingEnum);
	}

	/**
	 * move robot depending on x, y and facing
	 */
	public void move() {
		if (facing == null) {
			throw new IllegalArgumentException("null facing, cannot move!");
		}
		switch (facing) {
		case NORTH:
			incrementY();
			break;
		case SOUTH:
			decrementY();
			break;
		case WEST:
			decrementX();
			break;
		case EAST:
			incrementX();
			break;
		}
	}

	/**
	 * turn robot to left
	 */
	public void left() {
		setFacing(facing.left());
	}

	/**
	 * turn robot to right
	 */
	public void right() {
		setFacing(facing.right());
	}

	/**
	 * report robot position
	 * 
	 * @return
	 */
	public String report() {
		if (facing == null) {
			return "Output: ROBOT MISSING";
		}
		return String.format("Output: %d,%d,%s", x, y, facing);
	}

	/**
	 * display robot fields
	 */
	@Override
	public String toString() {
		return "Robot [x=" + x + ", y=" + y + ", facing=" + facing + "]";
	}
}
