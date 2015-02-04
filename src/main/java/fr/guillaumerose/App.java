package fr.guillaumerose;

import com.google.common.base.Splitter;

import java.util.List;

import lombok.Data;
import lombok.experimental.Wither;

import static com.google.common.collect.Lists.*;
import static java.lang.Integer.*;

public class App {
    @Data
    @Wither
    private static class Robot {
        private final String name;
        private final int x;
        private final int y;
        private final String direction;

        public static Robot parse(String name, String position) {
            String[] fields = position.split(" ");
            return new Robot(name, valueOf(fields[0]), valueOf(fields[1]), fields[2]);
        }

        public String summary() {
            return name + " : " + x + " " + y + " " + direction;
        }

        public Robot forward() {
            if ("S".equals(direction)) {
                return withY(y - 1);
            }
            else if ("N".equals(direction)) {
                return withY(y + 1);
            }
            else if ("O".equals(direction)) {
                return withX(x - 1);
            }
            else if ("E".equals(direction)) {
                return withX(x + 1);
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

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static String readLine(String input) {
        List<String> parts = newArrayList(Splitter.on(";").split(input));
        Robot robot = Robot.parse(parts.get(0), parts.get(1));
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
}