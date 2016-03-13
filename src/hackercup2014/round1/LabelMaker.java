package hackercup2014.round1;

import java.util.Scanner;

/**
 * @author Manoj Khanna
 */

public class LabelMaker {

    private char[] chars;
    private long count, curCount = 0;
    private String result;

    public LabelMaker(char[] chars, long count) {
        this.chars = chars;
        this.count = count;

        for (int i = 1; curCount < count; i++) {
            generatePermutations("", i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1, t = scanner.nextInt(); i <= t; i++) {
            System.out.println("Case #" + i + ": " + new LabelMaker(scanner.next().toCharArray(), scanner.nextLong()).getResult());
        }
    }

    private void generatePermutations(String prefix, long offset) {
        if (offset == 0) {
            curCount++;

            if (count == curCount) {
                result = prefix;
            }
            return;
        }

        for (char c : chars) {
            generatePermutations(prefix + c, offset - 1);

            if (count == curCount) {
                return;
            }
        }
    }

    public String getResult() {
        return result;
    }

}