package hackercup2015.round1;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Manoj Khanna
 */

public class Autocomplete {

    private static Node rootNode;

    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new FileInputStream("in.txt"));

        int t = scanner.nextInt();
        for (int i = 1; i <= t; i++) {
            rootNode = new Node();

            int n = scanner.nextInt();
            scanner.nextLine();

            int res = 0;
            for (int j = 0; j < n; j++) {
                res += rootNode.search(scanner.nextLine().toCharArray(), 0);
            }

            System.out.println("Case #" + i + ": " + res);
        }
    }

    private static class Node {

        private HashMap<Character, Node> childNodeMap = new HashMap<>(26);

        private int search(char[] word, int i) {
            char c = word[i];
            Node node = childNodeMap.get(c);
            if (node == null) {
                Node nextNode = new Node();
                for (int j = word.length - 2; j >= i; j--) {
                    Node currNode = new Node();
                    currNode.childNodeMap.put(word[j + 1], nextNode);
                    nextNode = currNode;
                }
                childNodeMap.put(c, nextNode);

                return i + 1;
            }

            return i + 1 < word.length ? node.search(word, i + 1) : word.length;
        }

    }

}
