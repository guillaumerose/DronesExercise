package fr.guillaumerose;

import static fr.guillaumerose.Direction.E;
import static fr.guillaumerose.Direction.N;
import static fr.guillaumerose.Direction.O;
import static fr.guillaumerose.Direction.S;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;

import org.junit.Test;

import com.google.common.base.Splitter;

public class RobotTest {
	@Test
	public void should_answer_to_first_acceptance_test() {
		assertThat(endPosition(new Robot("DRONE_A", 7, 10, N, 55, 25, instructions("AAGAAADAAGAAAGADAA"))).summary()) //
				.isEqualTo("DRONE_A : 0 13 O");
	}

	@Test
	public void should_answer_to_second_acceptance_test() {
		assertThat(endPosition(new Robot("DRONE_B", 13, 23, E, 55, 25, instructions("AAADAAGAAADAAGAAAAAADA"))).summary()) //
				.isEqualTo("DRONE_B : 25 18 S");
	}

	@Test
	public void should_be_able_to_do_nothing() throws Exception {
		assertThat(endPosition(new Robot("Drone", 0, 0, N, 100, 100, instructions(""))).summary()) //
				.isEqualTo("Drone : 0 0 N");
	}

	@Test
	public void should_be_able_to_go_forward() throws Exception {
		assertThat(endPosition(new Robot("Drone", 0, 0, N, 100, 100, instructions("AAAA"))).summary()) //
				.isEqualTo("Drone : 0 4 N");
	}

	@Test
	public void should_be_able_to_go_forward_with_a_different_original_direction() throws Exception {
		assertThat(endPosition(new Robot("Drone", 0, 2, S, 100, 100, instructions("AA"))).summary()) //
				.isEqualTo("Drone : 0 0 S");
		assertThat(endPosition(new Robot("Drone", 0, 0, E, 100, 100, instructions("A"))).summary()) //
				.isEqualTo("Drone : 1 0 E");
		assertThat(endPosition(new Robot("Drone", 1, 0, O, 100, 100, instructions("A"))).summary()) //
				.isEqualTo("Drone : 0 0 O");
	}

	@Test
	public void should_be_able_to_start_at_a_different_point() throws Exception {
		assertThat(endPosition(new Robot("Drone", 10, 5, O, 100, 100, instructions("A"))).summary()) //
				.isEqualTo("Drone : 9 5 O");
	}

	@Test
	public void should_be_able_to_turn_left() throws Exception {
		assertThat(endPosition(new Robot("Drone", 0, 0, N, 100, 100, instructions("G"))).summary()) //
				.isEqualTo("Drone : 0 0 O");
		assertThat(endPosition(new Robot("Drone", 10, 5, S, 100, 100, instructions("G"))).summary()) //
				.isEqualTo("Drone : 10 5 E");
	}

	@Test
	public void should_be_able_to_turn_right() throws Exception {
		assertThat(endPosition(new Robot("Drone", 0, 0, N, 100, 100, instructions("D"))).summary()) //
				.isEqualTo("Drone : 0 0 E");
		assertThat(endPosition(new Robot("Drone", 10, 5, S, 100, 100, instructions("D"))).summary()) //
				.isEqualTo("Drone : 10 5 O");
	}

	@Test
	public void should_be_stable() throws Exception {
		assertThat(endPosition(new Robot("Drone", 2, 0, N, 100, 100, instructions("AAGAAGAAGAAG"))).summary()) //
				.isEqualTo("Drone : 2 0 N");
		assertThat(endPosition(new Robot("Drone", 3, 0, N, 100, 100, instructions("AAADAAADAAADAAAD"))).summary()) //
				.isEqualTo("Drone : 3 0 N");
	}

	@Test
	public void should_not_go_below_zero() throws Exception {
		assertThat(endPosition(new Robot("Drone", 0, 0, S, 100, 100, instructions("AAA"))).summary()) //
				.isEqualTo("Drone : 0 0 S");
		assertThat(endPosition(new Robot("Drone", 0, 0, O, 100, 100, instructions("AAA"))).summary()) //
				.isEqualTo("Drone : 0 0 O");
	}

	@Test
	public void should_not_go_after_maxX_and_maxY() throws Exception {
		assertThat(endPosition(new Robot("Drone", 9, 9, N, 10, 10, instructions("AAAAAA"))).summary()) //
				.isEqualTo("Drone : 9 10 N");
	}

	private ArrayDeque<String> instructions(String instructions) {
		return new ArrayDeque<String>(Splitter.fixedLength(1).splitToList(instructions));
	}

	private Robot endPosition(Robot robot) {
		while (robot.hasNext()) {
			robot.next();
		}
		return robot;
	}
}
