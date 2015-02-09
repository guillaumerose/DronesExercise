package fr.guillaumerose;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.FluentIterable.from;
import static java.lang.Integer.valueOf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

public class Map {
	private final ExecutorService executor = Executors.newFixedThreadPool(10);
	private final List<Robot> robots;
	private final long waitTime;

	public Map(long waitTime, List<Robot> robots) {
		this.waitTime = waitTime;
		this.robots = robots;
	}

	public void run() {
		Stopwatch stopwatch = Stopwatch.createStarted();
		List<Future<String>> summaries = from(robots).transform(new Function<Robot, Future<String>>() {
			@Override
			public Future<String> apply(Robot robot) {
				return executor.submit(new Callable<String>() {
					@Override
					public String call() throws Exception {
						while (robot.hasNext()) {
							robot.next();
							Thread.sleep(waitTime);
						}
						return robot.summary();
					}
				});
			}
		}).toList();
		from(summaries).forEach(new Consumer<Future<String>>() {
			@Override
			public void accept(Future<String> summary) {
				try {
					System.out.println(summary.get());
				} catch (InterruptedException | ExecutionException e) {
					throw propagate(e);
				}
			}
		});
		System.out.println("Total time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
		executor.shutdown();
	}

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readLines(new File(args[0]), Charsets.UTF_8);
		List<String> sizes = Splitter.on(" ").splitToList(lines.get(0));
		Map map = new Map(10L, from(lines.subList(1, lines.size())).transform(toRobot(valueOf(sizes.get(0)), valueOf(sizes.get(1)))).toList());
		map.run();
	}

	private static Function<String, Robot> toRobot(final int maxX, final int maxY) {
		return new Function<String, Robot>() {
			@Override
			public Robot apply(String input) {
				return Robot.fromLine(maxX, maxY, input);
			}
		};
	}

}