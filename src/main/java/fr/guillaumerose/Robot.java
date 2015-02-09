package fr.guillaumerose;

import java.util.Queue;

class Robot {
	private static final String RIGHT = "D";
	private static final String LEFT = "G";
	private static final String FORWARD = "A";

	private final String name;
	private int x;
	private int y;
	private Direction direction;
	private final int maxX;
	private final int maxY;
	private final Queue<String> instructions;

	public Robot(String name, int x, int y, Direction direction, int maxX, int maxY, Queue<String> instructions) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.maxX = maxX;
		this.maxY = maxY;
		this.instructions = instructions;
	}

	public String summary() {
		return name + " : " + x + " " + y + " " + direction;
	}

	public boolean hasNext() {
		return !instructions.isEmpty();
	}

	public void next() {
		String instruction = instructions.poll();
		if (FORWARD.equals(instruction)) {
			forward();
		} else if (LEFT.equals(instruction)) {
			turnLeft();
		} else if (RIGHT.equals(instruction)) {
			turnRight();
		}
	}

	private void forward() {
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

	private void turnLeft() {
		direction = direction.getLeft();
	}

	private void turnRight() {
		direction = direction.getRight();
	}
}