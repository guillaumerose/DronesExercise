package fr.guillaumerose;

import lombok.Data;
import lombok.experimental.Wither;

import static java.lang.Integer.*;

@Data
@Wither
class Robot {
    private final String name;
    private final int x;
    private final int y;
    private final String direction;
    private final int maxX;
    private final int maxY;

    public static Robot parse(String name, String position, int maxX, int maxY) {
        String[] fields = position.split(" ");
        return new Robot(name, valueOf(fields[0]), valueOf(fields[1]), fields[2], maxX, maxY);
    }

    public String summary() {
        return name + " : " + x + " " + y + " " + direction;
    }

    public Robot forward() {
        if ("S".equals(direction)) {
            return y - 1 >= 0 ? withY(y - 1) : this;
        }
        else if ("N".equals(direction)) {
            return y + 1 <= maxY ? withY(y + 1) : this;
        }
        else if ("O".equals(direction)) {
            return x - 1 >= 0 ? withX(x - 1) : this;
        }
        else if ("E".equals(direction)) {
            return x + 1 <= maxX ? withX(x + 1) : this;
        }
        return this;
    }

    public Robot turnLeft() {
        if ("S".equals(direction)) {
            return withDirection("E");
        }
        else if ("N".equals(direction)) {
            return withDirection("O");
        }
        else if ("O".equals(direction)) {
            return withDirection("S");
        }
        else if ("E".equals(direction)) {
            return withDirection("N");
        }
        return this;
    }

    public Robot turnRight() {
        if ("S".equals(direction)) {
            return withDirection("O");
        }
        else if ("N".equals(direction)) {
            return withDirection("E");
        }
        else if ("O".equals(direction)) {
            return withDirection("N");
        }
        else if ("E".equals(direction)) {
            return withDirection("S");
        }
        return this;
    }
}