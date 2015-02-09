package fr.guillaumerose;

import static java.lang.Integer.valueOf;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import com.google.common.base.Splitter;

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

	public Robot(String name, int x, int y, Direction direction, Queue<String> instructions, int maxX, int maxY) {
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

	public static Robot fromLine(int maxX, int maxY, String line) {
		List<String> parts = Splitter.on(";").splitToList(line);
		List<String> fields = Splitter.on(" ").splitToList(parts.get(1));
		return new Robot(parts.get(0), valueOf(fields.get(0)), valueOf(fields.get(1)), Direction.valueOf(fields.get(2)), instructionsFrom(parts), maxX, maxY);
	}

	private static Queue<String> instructionsFrom(List<String> parts) {
		return new ArrayDeque<>(Splitter.fixedLength(1).omitEmptyStrings().splitToList(parts.get(2)));
	}
}