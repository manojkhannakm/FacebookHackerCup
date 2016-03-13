import java.io.*;
import java.util.*;

/**
 * @author Manoj Khanna
 */

public class TextEditor {

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
                int n = in.nextInt(),
                        k = in.nextInt();

                String[] s = new String[n];

                for (int j = 0; j < n; j++) {
                    String sj = in.nextLine();

                    s[j] = sj;
                }

                Trie trie = new Trie();

                for (String sj : s) {
                    trie.add(sj);
                }

                trie.print();

                out.println(trie.find(k));
            }
        }

        private static class Trie {

            private static int l;

            private Node rNode = new Node('\0');

            public void add(String s) {
                Node pNode = rNode;

                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    Node node = pNode.nodeMap.get(c);

                    if (node == null) {
                        node = new Node(c);
                        pNode.nodeMap.put(c, node);
                    }

                    node.n++;

                    if (node.m > s.length()) {
                        node.m = s.length();
                    }

                    pNode = node;
                }

                pNode.b = true;
            }

            public long find(int k) {
                l = 0;

                return rNode.find(k);
            }

            public void print() {
                rNode.print(new char[100000], 0);
            }

            private class Node {

                private char c;
                private boolean b;
                private int m = Integer.MAX_VALUE, n;
                private HashMap<Character, Node> nodeMap = new HashMap<>();

                private Node(char c) {
                    this.c = c;
                }

                private long find(int k) {
                    if (l == k || nodeMap.isEmpty()) {
                        return 0;
                    }

                    ArrayList<Node> nodeList = new ArrayList<>(nodeMap.values());

                    nodeList.sort(new Comparator<Node>() {

                        @Override
                        public int compare(Node o1, Node o2) {
                            int i = Boolean.compare(o2.b, o1.b);

                            if (i == 0) {
                                i = Integer.compare(o1.m, o2.m);

                                if (i == 0) {
                                    i = Integer.compare(o2.n, o1.n);
                                }
                            }

                            return i;
                        }

                    });

                    long s = 0;

                    for (Node node : nodeList) {
                        if (node.b) {
                            l++;
                            s++;
                        }

                        s += node.find(k);

                        if (s > 0) {
                            s += 2;
                        }

                        if (l == k) {
                            break;
                        }
                    }

                    return s;
                }

                private void print(char[] s, int i) {
                    s[i] = c;

                    out.format("%" + (i + 1) + "s (m = %d, n = %d)", c, m, n);
                    out.println(b ? " -> " + new String(s, 0, i + 1) : "");

                    for (Node node : nodeMap.values()) {
                        if (node != null) {
                            node.print(s, i + 1);
                        }
                    }

                    s[i] = '\0';
                }

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
