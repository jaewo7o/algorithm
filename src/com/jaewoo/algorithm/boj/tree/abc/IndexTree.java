package com.jaewoo.algorithm.boj.tree.abc;

public class IndexTree {

    public static void main(String[] args) {
        int [] numbers = {0, 8, 3, 26, 1, 7, 24, 10};
        //int [] numbers = {0, 1, 2, 3, 4, 5};

        Tree tree = new Tree(numbers);

        System.out.println("tree height : " + tree.height);
        System.out.println("leaf count : " + tree.leafCount);

        tree.makeTree();
        System.out.println("partial sum(1-3) : " + tree.getPartialSum(1, 3));

        tree.update(3, 0);
        System.out.println("after index 3 26 -> 0, partial sum(1-3) : " + tree.getPartialSum(1, 3));
    }

    static class Tree {
        int[] nodes;
        int[] numbers;
        int height;
        int leafCount;

        Tree(int[] numbers) {
            this.numbers = numbers;
            int length = numbers.length - 1;

            this.height = 0;
            while ( length != 0 ) {
                length /= 2;
                this.height++;
            }

            this.leafCount = (int) Math.pow(2, this.height);
            this.nodes = new int[ (int) Math.pow(2, this.height + 1) ];
        }

        public void makeTree() {
            makeSubTree(1, 1, leafCount);
        }

        private int makeSubTree(int node, int left, int right) {
            if (left == right) {
                if (left < this.numbers.length) {
                    return this.nodes[node] = this.numbers[left];
                } else {
                    return this.nodes[node] = 0;
                }
            }

            int mid = (left + right) / 2;
            this.nodes[node] = makeSubTree(2*node, left, mid);
            this.nodes[node] += makeSubTree(2*node + 1, mid + 1, right);

            return this.nodes[node];
        }

        public int getPartialSum(int targetLeft, int targetRight) {
            return target(1, 1, leafCount, targetLeft, targetRight);
        }

        private int target(int node, int left, int right, int tLeft, int tRight) {
            if (tRight < left || tLeft > right) {
                return 0;
            }

            if (tLeft <= left && right <= tRight) {
                return this.nodes[node];
            }

            int mid = (left + right) / 2;
            return target(2*node, left, mid, tLeft, tRight) +
                    target(2*node + 1, mid + 1, right, tLeft, tRight);
        }

        public void update(int index, int value) {
            subUpdate(1, 1, leafCount, index, value - numbers[index]);
            this.numbers[index] = value;
        }

        private void subUpdate(int node, int left, int right, int index, int diff) {
            if (left <= index && index <= right) {
                this.nodes[node] += diff;

                if (left == right) {
                    return;
                }
            } else {
                return;
            }

            int mid = (left + right) / 2;
            subUpdate(2*node, left, mid, index, diff);
            subUpdate(2*node + 1, mid + 1, right, index, diff);
        }
    }
}
