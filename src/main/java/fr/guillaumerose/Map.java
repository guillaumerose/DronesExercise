package fr.guillaumerose;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.*;
import static java.lang.Integer.*;

public class Map {
    private static final String RIGHT = "D";
    private static final String LEFT = "G";
    private static final String FORWARD = "A";
    private final int maxX;
    private final int maxY;

    public Map(Integer maxX, Integer maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public String move(String input) {
        List<String> parts = newArrayList(Splitter.on(";").split(input));
        Robot robot = robotFrom(parts);
        for (String instruction : instructionsFrom(parts)) {
            if (FORWARD.equals(instruction)) {
                robot = robot.forward();
            }
            else if (LEFT.equals(instruction)) {
                robot = robot.turnLeft();
            }
            else if (RIGHT.equals(instruction)) {
                robot = robot.turnRight();
            }
        }
        return robot.summary();
    }

    private Robot robotFrom(List<String> parts) {
        List<String> fields = newArrayList(Splitter.on(" ").split(parts.get(1)));
        return new Robot(parts.get(0), valueOf(fields.get(0)), valueOf(fields.get(1)), Direction.valueOf(fields.get(2)), maxX, maxY);
    }

    private static List<String> instructionsFrom(List<String> parts) {
        return newArrayList(Splitter.fixedLength(1).omitEmptyStrings().split(parts.get(2)));
    }

    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<String> lines = Files.readLines(new File(args[0]), Charsets.UTF_8);
        List<String> sizes = newArrayList(Splitter.on(" ").split(lines.get(0)));
        Map map = new Map(valueOf(sizes.get(0)), valueOf(sizes.get(1)));
        for (String line : lines.subList(1, lines.size())) {
            System.out.println(map.move(line));
        }
        System.out.println("Total time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }
}