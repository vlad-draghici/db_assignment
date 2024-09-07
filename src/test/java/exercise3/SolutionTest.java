package exercise3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionTest {

    @Test
    @DisplayName("Should return correct results")
    void testNormalCases() {
        Solution program = new Solution();
        int result1 = program.solution(new int[] {130, 191, 200, 10});
        int result2 = program.solution(new int[] {405, 45, 300, 300});
        int result3 = program.solution(new int[] {30, 909, 3190, 99, 3990, 9009});

        assertEquals(140, result1);
        assertEquals(600, result2);
        assertEquals(9918, result3);
    }

    @Test
    @DisplayName("Should return -1 when no match")
    void testNoMatch() {
        Solution program = new Solution();
        int result = program.solution(new int[] {50, 222, 49, 52, 25});

        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Should handle single-digit numbers")
    void testSingleDigitNumbers() {
        Solution program = new Solution();
        int result = program.solution(new int[] {0, 0, 5, 5, 3, 2});

        assertEquals(10, result);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when given any negative numbers")
    void testNegativeNumbers() {
        Solution program = new Solution();

        assertThrows(IllegalArgumentException.class, () -> {
            program.solution(new int[] {50, -222, 49, 52, 25});
        });
    }

    @Test
    @DisplayName("Should handle empty arrays as expected")
    void testEmptyArray() {
        Solution program = new Solution();
        int result = program.solution(new int[] {});

        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Should throw NullPointerException when given a null argument")
    void testNullArgument() {
        Solution program = new Solution();

        assertThrows(NullPointerException.class, () -> {
            program.solution(null);
        });
    }
}
