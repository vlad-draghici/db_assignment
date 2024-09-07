package exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private int getFirstDigit(int number) {
        while(number >= 10) {
            number /= 10;
        }
        return number;
    }

    private int getLastDigit(int number) {
        return number % 10;
    }

    private void checkNoNegatives(int[] inputArray) {
        Arrays.stream(inputArray)
                .filter(x -> x < 0)
                .findFirst()
                .ifPresent(el -> {
                    throw new IllegalArgumentException("Negative numbers are not allowed in the array!");
                });
    }

    public int solution(int[] inputArray) {
        Objects.requireNonNull(inputArray, "Input array can't be null!");
        checkNoNegatives(inputArray);

        return Arrays.stream(inputArray)
                .boxed()
                .collect(Collectors.groupingBy(
                        el -> List.of(getFirstDigit(el), getLastDigit(el)))
                )
                .values()
                .stream()
                .filter(numbersWithSameDigitPairs -> numbersWithSameDigitPairs.size() >= 2)
                .map(numbersWithSameDigitPairs -> {
                    numbersWithSameDigitPairs.sort(Collections.reverseOrder());
                    return numbersWithSameDigitPairs.get(0) + numbersWithSameDigitPairs.get(1);
                })
                .max(Integer::compareTo)
                .orElse(-1);
    }

    public int solutionBrute(int[] inputArray) {
        int maxSum = -1;
        for (int i = 0; i < inputArray.length - 1; i++) {
            for (int j = i + 1; j < inputArray.length; j++) {
                int el1 = inputArray[i];
                int el2 = inputArray[j];
                if ((getFirstDigit(el1) == getFirstDigit(el2))
                        && (getLastDigit(el1) == getLastDigit(el2))) {
                    maxSum = Math.max(maxSum, el1 + el2);
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Solution program = new Solution();

        // Execution time measurements
        int[] largeArray = generateRandomArray(20_000);
        int[] hugeArray = generateRandomArray(1_000_000);

        long start1 = System.nanoTime();
        program.solution(largeArray);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        program.solution(hugeArray);
        long end2 = System.nanoTime();

        long start3 = System.nanoTime();
        program.solutionBrute(largeArray);
        long end3 = System.nanoTime();

        long executionTime1 = (end1 - start1) / 1_000_000;
        long executionTime2 = (end2 - start2) / 1_000_000;
        long executionTime3 = (end3 - start3) / 1_000_000;

        System.out.println("Execution time for 20K array, optimal solution: " + executionTime1 + " ms");
        System.out.println("Execution time for 1M array, optimal solution: " + executionTime2 + " ms");
        System.out.println("Execution time for 20K array, quadratic solution: " + executionTime3 + " ms");
    }

    private static int[] generateRandomArray(int size) {
            return new Random()
                    .ints(size, 0, Integer.MAX_VALUE)
                    .toArray();
    }
}
