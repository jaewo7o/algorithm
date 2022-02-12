package com.jaewoo.algorithm.boj.tree.segmenttree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A10868 {

    private static int N;
    private static int M;

    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(token.nextToken());
        }

        SegmentTree tree = new SegmentTree();
        SegmentTree.Node node = tree.initNode(numbers, 1, N);

        // Query
        for(int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());

            // Get Value For start(l), end(r)
            System.out.println(tree.getValue(node, start, end));
        }
    }

    static class SegmentTree {
        public class Node {
            private int start;
            private int end;
            private int value;

            private Node leftChild;
            private Node rightChild;
        }

        public Node initNode(int[] numbers, int left, int right) {
            // Leaf Node
            if (left == right) {
                Node node = new Node();
                node.start = left;
                node.end = right;
                node.value = numbers[left];
                return node;
            }

            // Parent Node
            int mid = (left + right) / 2;
            Node leftNode = initNode(numbers, left, mid);
            Node rightNode = initNode(numbers, mid + 1, right);

            Node rootNode = new Node();
            rootNode.start = left;
            rootNode.end = right;
            rootNode.value = Math.min(leftNode.value, rightNode.value);
            rootNode.leftChild = leftNode;
            rootNode.rightChild = rightNode;

            return rootNode;
        }

        public static int getValue(Node root, int l, int r) {
            if (root.start >= l && root.end <= r) {
                return root.value;
            }

            if (root.start > r || root.end < l) {
                return Integer.MAX_VALUE;
            }

            return Math.min(getValue(root.leftChild, l, r), getValue(root.rightChild, l, r));
        }
    }
}


/* INPUT
10 4
75
30
100
38
50
51
52
20
81
5
1 10
3 5
6 9
8 10
 */
