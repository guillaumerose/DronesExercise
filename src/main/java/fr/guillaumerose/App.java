package fr.guillaumerose;

import lombok.Data;

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
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static String process(String input) {
        String[] parts = input.split(";");
        return Robot.parse(parts[0], parts[1]).summary();
    }
}