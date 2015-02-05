package fr.guillaumerose;

import com.google.common.base.Splitter;

import java.util.List;

import static com.google.common.collect.Lists.*;

public class Map {
    private final int maxX;
    private final int maxY;

    public Map(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public String move(String input) {
        List<String> parts = newArrayList(Splitter.on(";").split(input));
        Robot robot = Robot.parse(parts.get(0), parts.get(1), maxX, maxY);
        List<String> instructions = newArrayList(Splitter.fixedLength(1).omitEmptyStrings().split(parts.get(2)));
        for (String instruction : instructions) {
            if ("A".equals(instruction)) {
                robot = robot.forward();
            }
            else if ("G".equals(instruction)) {
                robot = robot.turnLeft();
            }
            else if ("D".equals(instruction)) {
                robot = robot.turnRight();
            }
        }
        return robot.summary();
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}