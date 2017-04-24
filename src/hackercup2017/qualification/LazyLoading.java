package hackercup2017.qualification;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class LazyLoading {

    private static InputReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("-local")) {
            try {
                in = new InputReader(new FileInputStream("in.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            in = new InputReader(System.in);
        }

        try {
            out = new PrintWriter("out.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        new Solution().solve();

        out.close();
    }

    private static class Solution {

        public void solve() {
            int t = in.nextInt();

            for (int i = 1; i <= t; i++) {
                int n = in.nextInt();

                int[] w = new int[n];

                for (int j = 0; j < n; j++) {
                    w[j] = in.nextInt();
                }

                Arrays.sort(w);

                int j = 0,
                        k = n - 1,
                        c = 0;

                while (j <= k) {
                    int wk = w[k];

                    if (wk >= 50) {
                        c++;

                        k--;
                        continue;
                    }

                    int d = 50 / wk;

                    if (k - j >= d) {
                        c++;
                    }

                    j += d;
                    k--;
                }

                out.println("Case #" + i + ": " + c);
            }
        }

    }

    @SuppressWarnings("UnusedDeclaration")
    private static class InputReader {

        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public InputReader(InputStream inputStream) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }

        public String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return stringTokenizer.nextToken();
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            if (stringTokenizer != null && stringTokenizer.hasMoreTokens()) {
                return stringTokenizer.nextToken("");
            }

            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
