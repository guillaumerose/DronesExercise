package fr.guillaumerose;

import lombok.AllArgsConstructor;
import lombok.experimental.Wither;

@Wither
@AllArgsConstructor
class Robot {
    private final String name;
    private final int x;
    private final int y;
    private final Direction direction;
    private final int maxX;
    private final int maxY;

    public String summary() {
        return name + " : " + x + " " + y + " " + direction;
    }

    public Robot forward() {
        switch (direction) {
            case S:
                return y - 1 >= 0 ? withY(y - 1) : this;
            case N:
                return y + 1 <= maxY ? withY(y + 1) : this;
            case O:
                return x - 1 >= 0 ? withX(x - 1) : this;
            case E:
                return x + 1 <= maxX ? withX(x + 1) : this;
            default:
                return this;
        }
    }

    public Robot turnLeft() {
        return withDirection(direction.getLeft());
    }

    public Robot turnRight() {
        return withDirection(direction.getRight());
    }
}