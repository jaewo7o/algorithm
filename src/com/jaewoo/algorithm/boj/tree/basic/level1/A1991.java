package com.jaewoo.algorithm.boj.tree.basic.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Tree tree = new Tree();
        for (int i = 1; i <= N; i++) {
            String[] data = br.readLine().split(" ");

            tree.createNode(data[0], data[1], data[2]);
        }

        tree.preorder(tree.root);
        System.out.print("\n");
        tree.inorder(tree.root);
        System.out.print("\n");
        tree.postorder(tree.root);
    }

    static class Tree {
        class Node {
            String data;
            Node leftNode;
            Node rightNode;

            public Node(String data) {
                this.data = data;
            }
        }

        Node root;

        void createNode(String parent, String left, String right) {
            if (root == null) {
                root = new Node(parent);
                addChildNode(root, left, right);
            } else {
                createChildNode(root, parent, left, right);
            }
        }

        private void createChildNode(Node root, String parent, String left, String right) {
            if (root == null) {
                return;
            } else if (parent.equals(root.data)) {
                addChildNode(root, left, right);
            } else {
                createChildNode(root.leftNode, parent, left, right);
                createChildNode(root.rightNode, parent, left, right);
            }
        }

        private void addChildNode(Node root, String left, String right) {
            if (!".".equals(left)) {
                root.leftNode = new Node(left);
            }

            if (!".".equals(right)) {
                root.rightNode = new Node(right);
            }
        }

        // 전위순회 : 루트 -> 좌 -> 우
        public void preorder(Node root) {
            System.out.print(root.data);

            if (root.leftNode != null) {
                preorder(root.leftNode);
            }

            if (root.rightNode != null) {
                preorder(root.rightNode);
            }
        }

        // 중위순회 : 좌 -> 루트 -> 우
        public void inorder(Node root) {
            if (root.leftNode != null) {
                inorder(root.leftNode);
            }

            System.out.print(root.data);

            if (root.rightNode != null) {
                inorder(root.rightNode);
            }
        }

        // 후위순회 : 좌 -> 우 -> 루트
        public void postorder(Node root) {
            if (root.leftNode != null) {
                postorder(root.leftNode);
            }

            if (root.rightNode != null) {
                postorder(root.rightNode);
            }

            System.out.print(root.data);
        }
    }
}

/* INPUT
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .
 */
