package fr.guillaumerose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MapTest {
    @Test
    public void should_answer_to_first_acceptance_test() {
        assertThat(new Map(55, 25).move("DRONE_A;7 10 N;AAGAAADAAGAAAGADAA")).isEqualTo("DRONE_A : 0 13 O");
    }

    @Test
    public void should_answer_to_second_acceptance_test() {
        assertThat(new Map(55, 25).move("DRONE_B;13 23 E;AAADAAGAAADAAGAAAAAADA")).isEqualTo("DRONE_B : 25 18 S");
    }

    @Test
    public void should_be_able_to_do_nothing() throws Exception {
        assertThat(largeMap().move("DRONE_A;0 0 N;")).isEqualTo("DRONE_A : 0 0 N");
    }

    @Test
    public void should_be_able_to_go_forward() throws Exception {
        assertThat(largeMap().move("DRONE_A;0 0 N;AAAA")).isEqualTo("DRONE_A : 0 4 N");
    }

    @Test
    public void should_be_able_to_go_forward_with_a_different_original_direction() throws Exception {
        assertThat(largeMap().move("DRONE_A;0 2 S;AA")).isEqualTo("DRONE_A : 0 0 S");
        assertThat(largeMap().move("DRONE_A;0 0 E;A")).isEqualTo("DRONE_A : 1 0 E");
        assertThat(largeMap().move("DRONE_A;1 0 O;A")).isEqualTo("DRONE_A : 0 0 O");
    }

    @Test
    public void should_be_able_to_start_at_a_different_point() throws Exception {
        assertThat(largeMap().move("DRONE_B;10 5 O;A")).isEqualTo("DRONE_B : 9 5 O");
    }

    @Test
    public void should_be_able_to_turn_left() throws Exception {
        assertThat(largeMap().move("DRONE_B;0 0 N;G")).isEqualTo("DRONE_B : 0 0 O");
        assertThat(largeMap().move("DRONE_B;10 5 S;G")).isEqualTo("DRONE_B : 10 5 E");
    }

    @Test
    public void should_be_able_to_turn_right() throws Exception {
        assertThat(largeMap().move("DRONE_C;0 0 N;D")).isEqualTo("DRONE_C : 0 0 E");
        assertThat(largeMap().move("DRONE_C;10 5 S;D")).isEqualTo("DRONE_C : 10 5 O");
    }

    @Test
    public void should_be_stable() throws Exception {
        assertThat(largeMap().move("DRONE_C;2 0 N;AAGAAGAAGAAG")).isEqualTo("DRONE_C : 2 0 N");
        assertThat(largeMap().move("DRONE_C;3 0 N;AAADAAADAAADAAAD")).isEqualTo("DRONE_C : 3 0 N");
    }

    @Test
    public void should_not_go_below_zero() throws Exception {
        assertThat(largeMap().move("DRONE_B;0 0 S;A")).isEqualTo("DRONE_B : 0 0 S");
        assertThat(largeMap().move("DRONE_B;0 0 O;A")).isEqualTo("DRONE_B : 0 0 O");
    }

    @Test
    public void should_not_go_after_maxX_and_maxY() throws Exception {
        assertThat(largeMap().move("DRONE_B;0 0 O;A")).isEqualTo("DRONE_B : 0 0 O");
    }

    private static Map largeMap() {
        return new Map(100, 100);
    }
}
