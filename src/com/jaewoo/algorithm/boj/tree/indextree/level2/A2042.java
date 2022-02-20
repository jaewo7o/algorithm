package com.jaewoo.algorithm.boj.tree.indextree.level2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2042 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] numbers = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        IndexTree tree = new IndexTree(numbers);
        tree.makeTree();

        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                tree.update(b, c);
            } else
                System.out.println(tree.getSum(b, c));
        }
    }

    static class IndexTree {
        private long[] numbers;
        private long[] nodes;
        private int height, leafCount;

        IndexTree(long[] numbers) {
            this.numbers = numbers;
            int len = this.numbers.length - 1;

            this.height = 0;
            while (len != 0) {
                len /= 2;
                this.height++;
            }

            this.leafCount = (int) Math.pow(2, height);
            this.nodes = new long[(int) Math.pow(2, height + 1)];
        }

        public void makeTree() {
            makeSubTree(1, 1, leafCount);
        }

        private long makeSubTree(int node, int left, int right) {
            if (left == right) {
                if (left < numbers.length) {
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

        public long getSum(int left, int right) {
            return getSubSum(1, 1, leafCount, left, right);
        }

        private long getSubSum(int node, int left, int right, int tLeft, int tRight) {
            if (tRight < left || tLeft > right) {
                return 0L;
            }

            if (tLeft <= left  && right <= tRight) {
                return this.nodes[node];
            }

            int mid = (left + right) / 2;
            return getSubSum(node * 2, left, mid, tLeft, tRight)
                    + getSubSum(node*2 + 1, mid + 1, right, tLeft, tRight );
        }

        public void update(int index, int value) {
            subUpdate(1, 1, leafCount, index, value - numbers[index]);
            this.numbers[index] = value;
        }

        private void subUpdate(int node, int left, int right, int index, long diff) {
            if (index >= left && index <= right) {
                this.nodes[node] += diff;

                if (left == right) {
                    return;
                }
            } else {
                return;
            }

            int mid = (left + right) / 2;
            subUpdate(node * 2, left, mid, index, diff);
            subUpdate(node * 2 + 1, mid + 1, right, index, diff);
        }
    }
}

/* INPUT
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
 */
