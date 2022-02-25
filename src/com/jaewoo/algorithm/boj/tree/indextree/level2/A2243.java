package com.jaewoo.algorithm.boj.tree.indextree.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2243 {
    private static int treeSize;
    private static int leafCount;

    private static int[] tree;

    public static void main(String[] args) throws IOException {
        int depth = 0;

        int len = 1000000 - 1;
        while (len != 0) {
            len /= 2;
            depth++;
        }

        leafCount = (int)Math.pow(2, depth);
        treeSize = (int)Math.pow(2, depth + 1);
        tree = new int[treeSize];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer token;
        for (int i = 1, a, b, c; i <= N; i++) {
            token = new StringTokenizer(br.readLine());

            a = Integer.parseInt(token.nextToken());
            if (a == 1) {
                b = Integer.parseInt(token.nextToken());
                System.out.println(pickup(b));
            } else {
                b = Integer.parseInt(token.nextToken());
                c = Integer.parseInt(token.nextToken());

                update(b, c);
            }
        }
    }

    private static int pickup(int rank) {
        int index = subPickup(1, 1, leafCount, rank);
        update(index, -1);
        return index;
    }

    private static int subPickup(int node, int left, int right, int rank) {
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        int leftNodeSum = tree[node * 2];
        if (leftNodeSum >= rank ) {
            return subPickup(node * 2, left, mid, rank);
        } else {
            return subPickup(node * 2 + 1, mid + 1, right, rank - leftNodeSum);
        }
    }

    private static void update(int index, int value) {
        subUpdate(1, 1, leafCount, index, value);
    }

    private static void subUpdate(int node, int left, int right, int index, int diff) {
        if (left > index || right < index) {
            return;
        }

        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            subUpdate(2 * node, left, mid, index, diff);
            subUpdate(2 * node + 1, mid + 1, right, index, diff);
        }
    }
}

/* INPUT
6
2 1 2
2 3 3
1 2
1 2
2 1 -1
1 2
 */
