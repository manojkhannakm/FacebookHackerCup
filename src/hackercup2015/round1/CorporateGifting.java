package hackercup2015.round1;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Manoj Khanna
 */

public class CorporateGifting {

    private static int n;
    private static Node ceoNode;
    private static HashMap<Integer, Node> nodeMap;
    private static int gifts;

    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new FileInputStream("in.txt"));

        int t = scanner.nextInt();
        for (int i = 1; i <= t; i++) {
            n = scanner.nextInt();

            ceoNode = new Node(1);

            nodeMap = new HashMap<>(n);
            nodeMap.put(1, ceoNode);

            scanner.nextInt();

            for (int j = 2; j <= n; j++) {
                int managerId = scanner.nextInt();
                Node managerNode = nodeMap.get(managerId);
                if (managerNode == null) {
                    managerNode = new Node(managerId);
                    nodeMap.put(managerId, managerNode);
                }

                Node employeeNode = nodeMap.get(j);
                if (employeeNode == null) {
                    employeeNode = new Node(j);
                    nodeMap.put(j, employeeNode);
                }

                managerNode.employeeNodeList.add(employeeNode);
            }

            gifts = 0;
            ceoNode.gift();

            System.out.println("Case #" + i + ": " + gifts);
        }
    }

    private static class Node {

        private int id;
        private ArrayList<Node> employeeNodeList = new ArrayList<>();

        private Node(int id) {
            this.id = id;
        }

        private int gift() {
            int[] a = new int[n + 1];
            for (Node node : employeeNodeList) {
                a[node.gift()]++;
            }

            for (int i = 1; i < a.length; i++) {
                if (a[i] == 0) {
                    gifts += i;

                    return i;
                }
            }

            return 0;
        }

    }

}
