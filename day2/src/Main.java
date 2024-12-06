import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;

        int minSafeChange = 1;
        int maxSafeChange = 3;

        int safeReportCount = 0;

        try {
            reader = new BufferedReader(new FileReader("src/input.txt"));
            String line = reader.readLine();

            while (line != null) {
//                System.out.println(line);
//                String[] lineStrSplit = line.split(" ");
                int[] numbers = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                boolean isSafe = checkSafety(numbers, minSafeChange, maxSafeChange);

                if (!isSafe && canBeSafeWithOneRemoval(numbers, minSafeChange, maxSafeChange)) {
                    isSafe = true;
                }

                if (isSafe) {
                    safeReportCount++;
                    System.out.println(line);
                }

                line = reader.readLine();
            }

            reader.close();

            System.out.println("SafeCount : " + safeReportCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static boolean checkSafety(int[] numbers, int minSafeChange, int maxSafeChange) {
        boolean isIncreasing = numbers[0] < numbers[1];

        for (int i = 0; i < numbers.length - 1; i++) {
            int difference = abs(numbers[i] - numbers[i + 1]);

            if ((isIncreasing && numbers[i] > numbers[i + 1]) ||
                    (!isIncreasing && numbers[i] < numbers[i + 1]) ||
                    difference < minSafeChange || difference > maxSafeChange) {
                return false;
            }
        }
        return true;
    }

    private static boolean canBeSafeWithOneRemoval(int[] numbers, int minSafeChange, int maxSafeChange) {
        for (int indexToRemove = 0; indexToRemove < numbers.length; indexToRemove++) {
            int[] modifiedNumbers = new int[numbers.length - 1];
            int newArrayIndex = 0;

            for (int originalIndex = 0; originalIndex < numbers.length; originalIndex++) {
                if (originalIndex != indexToRemove) {
                    modifiedNumbers[newArrayIndex] = numbers[originalIndex];
                    newArrayIndex++;
                }
            }

            if (checkSafety(modifiedNumbers, minSafeChange, maxSafeChange)) {
                return true;
            }
        }

        return false;
    }
}
