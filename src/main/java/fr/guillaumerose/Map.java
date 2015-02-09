package fr.guillaumerose;

import static java.lang.Integer.valueOf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

public class Map {
	private final int maxX;
	private final int maxY;

	public Map(Integer maxX, Integer maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public String move(String input) {
		List<String> parts = Splitter.on(";").splitToList(input);
		Robot robot = robotFrom(parts);
		while (robot.hasNext()) {
			robot.next();
		}
		return robot.summary();
	}

	private Robot robotFrom(List<String> parts) {
		List<String> fields = Splitter.on(" ").splitToList(parts.get(1));
		return new Robot(parts.get(0), valueOf(fields.get(0)), valueOf(fields.get(1)), Direction.valueOf(fields.get(2)), maxX, maxY, instructionsFrom(parts));
	}

	private static Queue<String> instructionsFrom(List<String> parts) {
		return new ArrayDeque<>(Splitter.fixedLength(1).omitEmptyStrings().splitToList(parts.get(2)));
	}

	public static void main(String[] args) throws IOException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		List<String> lines = Files.readLines(new File(args[0]), Charsets.UTF_8);
		List<String> sizes = Splitter.on(" ").splitToList(lines.get(0));
		Map map = new Map(valueOf(sizes.get(0)), valueOf(sizes.get(1)));
		for (String line : lines.subList(1, lines.size())) {
			System.out.println(map.move(line));
		}
		System.out.println("Total time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
	}
}