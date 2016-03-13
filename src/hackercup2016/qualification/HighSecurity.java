package hackercup2016.qualification;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class HighSecurity {

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

        private int traverse(char[][] g, int r, int c, char x) {
            int n = g[r].length;

            while (c + 1 < n && g[r][c + 1] == x) {
                c++;
            }

            return c;
        }

        public void solve() {
            int t = in.nextInt();

            for (int i = 1; i <= t; i++) {
                int n = in.nextInt();

                char[][] g = new char[2][n];

                for (int j = 0; j < 2; j++) {
                    char[] gj = in.nextLine().toCharArray();

                    g[j] = gj;
                }

                boolean[][] b = new boolean[2][n];

                for (int j = 0; j < 2; j++) {
                    int x1, x2 = -1;

                    while (x2 + 1 < n) {
                        x1 = traverse(g, j, x2, 'X');
                        x2 = traverse(g, j, x1, '.');

                        if (x2 - x1 == 1) {
                            b[j][x2] = true;
                        }
                    }
                }

                int s = 0;

                for (int j = 0; j < n; j++) {
                    if (b[0][j] && b[1][j]) {
                        s++;
                    }
                }

                for (int j = 0; j < 2; j++) {
                    int x1, x2 = -1;

                    while (x2 + 1 < n) {
                        x1 = traverse(g, j, x2, 'X');
                        x2 = traverse(g, j, x1, '.');

                        int d = x2 - x1;

                        if (d == 1) {
                            if (g[j == 0 ? 1 : 0][x2] == 'X') {
                                s++;
                            }
                        } else if (d > 1) {
                            int c = 0;

                            for (int k = x1 + 1; k <= x2; k++) {
                                if (b[j == 0 ? 1 : 0][k]) {
                                    c++;
                                }
                            }

                            s += Math.max(1, c);
                        }
                    }
                }

                out.println("Case #" + i + ": " + s);
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
