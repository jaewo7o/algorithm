package com.jaewoo.algorithm.boj.tree.abc;

public class BinaryTree {

    static class Tree {
        class Node {
            public int data;
            public Node left;
            public Node right;

            Node(int data) {
                this.data = data;
            }
        }

        Node root;

        public void makeTree(int[] numbers) {
            root = makeLeaf(numbers, 0, numbers.length - 1);
        }

        private Node makeLeaf(int[] numbers, int start, int end) {
            if (start > end) {
                return null;
            }

            int mid = (start + end) / 2;
            Node node = new Node(numbers[mid]);

            node.left = makeLeaf(numbers, start, mid - 1);
            node.right = makeLeaf(numbers, mid + 1, end);
            return node;
        }

        public void searchBTree(Node node, int find) {
            if (find < node.data) {
                System.out.println("Data is smaller than " + node.data);
                searchBTree(node.left, find);
            } else if (find > node.data) {
                System.out.println("Data is greater than " + node.data);
                searchBTree(node.right, find);
            } else {
                System.out.println("Find data");
            }
        }

        public Node searchNode(Node root, int find) {
            if (root == null || root.data == find) {
                return root;
            }

            if (find < root.data) {
                return searchNode(root.left, find);
            } else {
                return searchNode(root.right, find);
            }
        }

        public void insertNode(int data) {
            root = insertNode(root, data);
        }

        private Node insertNode(Node root, int data) {
            if (root == null) {
                root = new Node(data);
                return root;
            }

            if (data < root.data) {
                root.left = insertNode(root.left, data);
            } else {
                root.right = insertNode(root.right, data);
            }

            return root;
        }

        public void deleteNode(int data) {
            root = deleteNode(root, data);
        }

        private Node deleteNode(Node root, int data) {
            if (root == null) {
                return root;
            }

            if (data < root.data) {
                root.left = deleteNode(root.left, data);
            } else if (data > root.data) {
                root.right = deleteNode(root.right, data);
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    root.data = findMin(root.right);
                    root.right = deleteNode(root.right, data);
                }
            }

            return root;
        }

        private int findMin(Node root) {
            int min = root.data;
            while (root.left == null) {
                min = root.left.data;
                root = root.left;
            }

            return min;
        }

        public void inorder() {
            inorder(root);
            System.out.println("");
        }

        private void inorder(Node root) {
            if (root != null) {
                inorder(root.left);
                System.out.print(root.data + " ");
                inorder(root.right);
            }
        }
    }


    public static void main(String[] args) {
        int [] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        Tree tree = new Tree();
        tree.makeTree(numbers);

        tree.searchBTree(tree.root, 2);

        Tree.Node node = tree.searchNode(tree.root, 2);
        System.out.println(node.data);


        tree.insertNode(17);

        node = tree.searchNode(tree.root, 17);
        System.out.println(node.data);

        System.out.println("######### DELETE TEST #########");
        tree.inorder();
        tree.deleteNode(3);
        tree.inorder();
        tree.deleteNode(2);
        tree.inorder();
    }
}
