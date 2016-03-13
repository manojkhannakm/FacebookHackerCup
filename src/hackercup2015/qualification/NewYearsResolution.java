package hackercup2015.qualification;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Manoj Khanna
 */

public class NewYearsResolution {

    private static boolean sum(int[] p, int[] c, int[] f, int gp, int gc, int gf, int[] pCombo, int[] cCombo, int[] fCombo) {
        int pSum = 0, cSum = 0, fSum = 0;
        for (int i = 0; i < pCombo.length; i++) {
            pSum += pCombo[i];
            cSum += cCombo[i];
            fSum += fCombo[i];
        }

        if (pSum == gp && cSum == gc && fSum == gf) {
            return true;
        }

        if (pSum > gp || cSum > gc || fSum > gf) {
            return false;
        }

        for (int i = 0; i < p.length; i++) {
            int[] newPCombo = Arrays.copyOfRange(pCombo, 0, pCombo.length + 1);
            newPCombo[newPCombo.length - 1] = p[i];

            int[] newCCombo = Arrays.copyOfRange(cCombo, 0, cCombo.length + 1);
            newCCombo[newCCombo.length - 1] = c[i];

            int[] newFCombo = Arrays.copyOfRange(fCombo, 0, fCombo.length + 1);
            newFCombo[newFCombo.length - 1] = f[i];

            if (sum(Arrays.copyOfRange(p, i + 1, p.length),
                    Arrays.copyOfRange(c, i + 1, c.length),
                    Arrays.copyOfRange(f, i + 1, f.length),
                    gp, gc, gf, newPCombo, newCCombo, newFCombo)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        for (int i = 1; i <= t; i++) {
            int gp = scanner.nextInt(),
                    gc = scanner.nextInt(),
                    gf = scanner.nextInt(),
                    n = scanner.nextInt();

            int[] p = new int[n],
                    c = new int[n],
                    f = new int[n];
            for (int j = 0; j < n; j++) {
                p[j] = scanner.nextInt();
                c[j] = scanner.nextInt();
                f[j] = scanner.nextInt();
            }

            System.out.println("Case #" + i + ": " + (
                    sum(p, c, f, gp, gc, gf, new int[0], new int[0], new int[0]) ? "yes" : "no"));
        }
    }

}
