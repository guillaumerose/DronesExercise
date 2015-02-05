package fr.guillaumerose;

public enum Direction {
    N("O", "E"), S("E", "O"), E("N", "S"), O("S", "N");

    private final String left;
    private final String right;

    private Direction(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public Direction getLeft() {
        return valueOf(left);
    }

    public Direction getRight() {
        return valueOf(right);
    }
}