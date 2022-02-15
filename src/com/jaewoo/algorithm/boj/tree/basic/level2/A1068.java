package com.jaewoo.algorithm.boj.tree.basic.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1068 {

    private static int N;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        parents = new int[N];

        StringTokenizer token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(token.nextToken());
        }

        int deleteNode = Integer.parseInt(br.readLine());
        removeNodes(deleteNode);
    }

    private static void removeNodes(int deleteNode) {
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
