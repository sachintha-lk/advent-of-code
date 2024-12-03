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
                boolean isSafe = true;
                boolean isIncreasing = false;
                for (int i = 0; i < numbers.length; i++) {
                    if (i < numbers.length - 1) {
                        if (i == 0) {
                            isIncreasing = numbers[i] < numbers[i + 1];
                        } else {

                            if (isIncreasing && (numbers[i] > numbers[i+1])) {
                                isSafe = false;
                                break;
                            } else if (!isIncreasing && (numbers[i] < numbers[i+1])) {
                                isSafe = false;
                                break;
                            }
                        }

                        int difference = abs(numbers[i] - numbers[i+1] );

                        if (difference < minSafeChange || maxSafeChange < difference) {
                            isSafe = false;
                        }
                    }
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
}