package hackercup2017.qualification;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Manoj Khanna
 */

public class ProgressPie {

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

        private boolean clockwise(double x1, double y1, double x2, double y2) {
            return -x1 * y2 + y1 * x2 > 0.0;
        }

        private boolean counterClockwise(double x1, double y1, double x2, double y2) {
            return -x1 * y2 + y1 * x2 < 0.0;
        }

        public void solve() {
            int t = in.nextInt();

            for (int i = 1; i <= t; i++) {
                double p = in.nextInt(),
                        x = in.nextInt(),
                        y = in.nextInt();

                double r = 50.0;

                x -= r;
                y -= r;

                boolean b;

                if (p == 0 || (x * x + y * y) > (r * r)) {
                    b = false;
                } else {
                    double a = Math.ceil(360.0 * p / 100.0);

                    double x2 = r * Math.sin(Math.toRadians(a)),
                            y2 = r * Math.cos(Math.toRadians(a));

                    if (a <= 90.0) {
                        b = counterClockwise(x, y, 0.0, r) && clockwise(x, y, x2, y2);
                    } else if (a <= 180.0) {
                        b = counterClockwise(x, y, 0.0, r) && clockwise(x, y, r, 0.0)
                                || counterClockwise(x, y, r, 0.0) && clockwise(x, y, x2, y2);
                    } else if (a <= 270.0) {
                        b = counterClockwise(x, y, 0.0, r) && clockwise(x, y, r, 0.0)
                                || counterClockwise(x, y, r, 0.0) && clockwise(x, y, 0.0, -r)
                                || counterClockwise(x, y, 0.0, -r) && clockwise(x, y, x2, y2);
                    } else {
                        b = counterClockwise(x, y, 0.0, r) && clockwise(x, y, r, 0.0)
                                || counterClockwise(x, y, r, 0.0) && clockwise(x, y, 0.0, -r)
                                || counterClockwise(x, y, 0.0, -r) && clockwise(x, y, -r, 0.0)
                                || counterClockwise(x, y, -r, 0.0) && clockwise(x, y, x2, y2);
                    }
                }

                out.println("Case #" + i + ": " + (b ? "black" : "white"));
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
