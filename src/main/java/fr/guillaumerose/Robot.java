package fr.guillaumerose;

import lombok.Data;
import lombok.experimental.Wither;

import static fr.guillaumerose.Direction.*;

@Data
@Wither
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
        if (S.equals(direction)) {
            return y - 1 >= 0 ? withY(y - 1) : this;
        }
        else if (N.equals(direction)) {
            return y + 1 <= maxY ? withY(y + 1) : this;
        }
        else if (O.equals(direction)) {
            return x - 1 >= 0 ? withX(x - 1) : this;
        }
        else if (E.equals(direction)) {
            return x + 1 <= maxX ? withX(x + 1) : this;
        }
        return this;
    }

    public Robot turnLeft() {
        return withDirection(direction.getLeft());
    }

    public Robot turnRight() {
        return withDirection(direction.getRight());
    }
}