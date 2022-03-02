package com.jaewoo.algorithm.boj.tree.indextree.level2;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A9426 {
    private static int N;
    private static int K;

    private static int MAX_N = 65535;
    private static int[] idxTree;

    private static int startIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        startIdx = 1;
        while (startIdx < MAX_N) {
            startIdx *= 2;
        }

        idxTree = new int[startIdx * 2];

        int now;
        long result = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            now = Integer.parseInt(br.readLine());
            update(now, 1);
            q.offer(now);

            if (i >= K) {
                //result += search(1, 0, MAX_N, (K + 1) / 2);
                result += search2((K + 1) / 2);
                int poll = q.poll();
                update(poll, -1);
            }
        }

        System.out.println(result);
    }

    private static void update(int idx, int diff) {
        idx += startIdx;
        while (idx != 0) {
            idxTree[idx] += diff;
            idx /= 2;
        }
    }

    private static int search(int node, int left, int right, int k) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) / 2;
            if (k <= idxTree[node * 2]) {
                return search(node * 2, left, mid, k);
            } else {
                return search(node * 2 + 1, mid + 1, right, k);
            }
        }
    }

    private static int search2(int k) {
        int idx = 1;
        while (idx < startIdx) {
            idx *= 2;
            if (idxTree[idx] < k) {
                k -= idxTree[idx];
                idx++;
            }
        }

        return idx - startIdx;
    }
}
