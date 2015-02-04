package fr.guillaumerose;

import org.junit.Test;

import static fr.guillaumerose.App.*;
import static org.assertj.core.api.Assertions.*;

public class AppTest {
    @Test
    public void should_answer_to_first_acceptance_test() {
        // assertThat(readLine("DRONE_A;7 10 N;AAGAAADAAGAAAGADAA")).isEqualTo("DRONE_A : 13 0 O");
    }

    @Test
    public void should_answer_to_second_acceptance_test() {
        assertThat(readLine("DRONE_B;13 23 E;AAADAAGAAADAAGAAAAAADA")).isEqualTo("DRONE_B : 25 18 S");
    }

    @Test
    public void should_be_able_to_do_nothing() throws Exception {
        assertThat(readLine("DRONE_A;0 0 N;")).isEqualTo("DRONE_A : 0 0 N");
    }

    @Test
    public void should_be_able_to_go_forward() throws Exception {
        assertThat(readLine("DRONE_A;0 0 N;AAAA")).isEqualTo("DRONE_A : 0 4 N");
    }

    @Test
    public void should_be_able_to_go_forward_with_a_different_original_direction() throws Exception {
        assertThat(readLine("DRONE_A;0 0 S;AA")).isEqualTo("DRONE_A : 0 -2 S");
        assertThat(readLine("DRONE_A;0 0 E;A")).isEqualTo("DRONE_A : 1 0 E");
        assertThat(readLine("DRONE_A;0 0 O;A")).isEqualTo("DRONE_A : -1 0 O");
    }

    @Test
    public void should_be_able_to_start_at_a_different_point() throws Exception {
        assertThat(readLine("DRONE_B;10 5 O;A")).isEqualTo("DRONE_B : 9 5 O");
    }

    @Test
    public void should_be_able_to_turn_left() throws Exception {
        assertThat(readLine("DRONE_B;0 0 N;G")).isEqualTo("DRONE_B : 0 0 O");
        assertThat(readLine("DRONE_B;10 5 S;G")).isEqualTo("DRONE_B : 10 5 E");
    }

    @Test
    public void should_be_able_to_turn_right() throws Exception {
        assertThat(readLine("DRONE_C;0 0 N;D")).isEqualTo("DRONE_C : 0 0 E");
        assertThat(readLine("DRONE_C;10 5 S;D")).isEqualTo("DRONE_C : 10 5 O");
    }

    @Test
    public void should_be_stable() throws Exception {
        assertThat(readLine("DRONE_C;0 0 N;AAGAAGAAGAAG")).isEqualTo("DRONE_C : 0 0 N");
        assertThat(readLine("DRONE_C;0 0 N;AAADAAADAAADAAAD")).isEqualTo("DRONE_C : 0 0 N");
    }
}
