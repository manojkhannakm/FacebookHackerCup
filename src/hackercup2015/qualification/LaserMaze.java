package hackercup2015.qualification;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Manoj Khanna
 */

public class LaserMaze {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt(),
                    n = scanner.nextInt();
            scanner.nextLine();

            Node[][] nodes = new Node[m][n];
            Node startNode, goalNode;
            for (int j = 0; j < m; j++) {
                char[] chars = scanner.nextLine().toCharArray();
                for (int k = 0; k < n; k++) {
                    Node node = new Node(chars[k]);
                    nodes[j][k] = node;

                    switch (node.c) {

                        case 'S':
                            startNode = node;
                            break;

                        case 'G':
                            goalNode = node;
                            break;

                    }
                }
            }

            System.out.println(Arrays.deepToString(nodes));
        }
    }

    private static class Node {

        private char c;

        public Node(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return String.valueOf(c);
        }

    }

}
