package fr.guillaumerose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AppTest {
    @Test
    public void should_answer_acceptance_test() {
        // String input =
        // "55 25\nDRONE_A;7 10 N;AAGAAADAAGAAAGADAA\nDRONE_B;13 23 E;AAADAAGAAADAAGAAAAAADA";
        // assertThat(App.process(input)).isEqualTo("DRONE_A : 13 0 O");
    }

    @Test
    public void should_be_able_to_do_nothing() throws Exception {
        assertThat(App.process("DRONE_A;0 0 N;")).isEqualTo("DRONE_A : 0 0 N");
    }

}
