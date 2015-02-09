package fr.guillaumerose;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Robot {
	private final String name;
	private int x;
	private int y;
	private Direction direction;
	private final int maxX;
	private final int maxY;

	public String summary() {
		return name + " : " + x + " " + y + " " + direction;
	}

	public void forward() {
		switch (direction) {
		case S:
			y = y - 1 >= 0 ? y - 1 : y;
			break;
		case N:
			y = y + 1 <= maxY ? y + 1 : y;
			break;
		case O:
			x = x - 1 >= 0 ? x - 1 : x;
			break;
		case E:
			x = x + 1 <= maxX ? x + 1 : x;
			break;
		}
	}

	public void turnLeft() {
		direction = direction.getLeft();
	}

	public void turnRight() {
		direction = direction.getRight();
	}
}