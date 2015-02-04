package fr.guillaumerose;

import com.google.common.base.Splitter;

import java.util.List;

import lombok.Data;

import static com.google.common.collect.Lists.*;
import static java.lang.Integer.*;

public class App {
    @Data
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
            if ("N".equals(direction)) {
                return new Robot(name, x, y + 1, direction);
            }
            return this;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static String process(String input) {
        List<String> parts = newArrayList(Splitter.on(";").split(input));
        Robot robot = Robot.parse(parts.get(0), parts.get(1));
        List<String> instructions = newArrayList(Splitter.fixedLength(1).omitEmptyStrings().split(parts.get(2)));
        for (String instruction : instructions) {
            robot = robot.forward();
        }
        return robot.summary();
    }
}