package hackercup2016.qualification;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class BoomerangConstellations {

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

        public static final int M = 2010;
        public static final int[][] B = new int[M][M];

        public void solve() {
            for (int i = 0; i < M; i++) {
                B[i][0] = B[i][i] = 1;
            }

            for (int i = 1; i < M; i++) {
                for (int j = 1; j < i; j++) {
                    B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
                }
            }

            int t = in.nextInt();

            for (int i = 1; i <= t; i++) {
                int n = in.nextInt();

                int[] x = new int[n],
                        y = new int[n];

                for (int j = 0; j < n; j++) {
                    int xj = in.nextInt(),
                            yj = in.nextInt();

                    x[j] = xj;
                    y[j] = yj;
                }

                int s = 0;

                for (int j = 0; j < n; j++) {
                    int x1 = x[j],
                            y1 = y[j];
                    HashMap<Double, Integer> map = new HashMap<>();

                    for (int k = 0; k < n; k++) {
                        int x2 = x[k],
                                y2 = y[k];
                        double d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                        Integer c = map.get(d);

                        if (c == null) {
                            c = 0;
                        }

                        map.put(d, c + 1);
                    }

                    for (Integer c : map.values()) {
                        if (c >= 2) {
                            s += B[c][2];
                        }
                    }

                    out.println(map);
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
