package hackercup2014.qualification;

import java.util.Scanner;

/**
 * @author Manoj Khanna
 */

public class SquareDetector {

    private static int getCharCount(String string, char c) {
        int count = 0;

        for (char c1 : string.toCharArray()) {
            if (c1 == c) {
                count++;
            }
        }

        return count;
    }

    private static boolean isSquare(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }

        for (int i = 0, n = scanner.nextInt(); i < n; i++) {
            String line = scanner.next();
            int hashCount = getCharCount(line, '#');

            if (hashCount == 0) {
                continue;
            }

            for (int j = 1; j < hashCount; j++) {
                if (!line.equals(scanner.next())) {
                    return false;
                }
            }

            for (int j = i + hashCount; j < n; j++) {
                if (getCharCount(scanner.next(), '#') > 0) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1, t = scanner.nextInt(); i <= t; i++) {
            System.out.println("Case #" + i + ": " + (isSquare(scanner) ? "YES" : "NO"));
        }
    }

}