package com.jaewoo.algorithm.boj.graph.bipartite_graph.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class A1671 {

    static int N;
    static int[] size;
    static int[] velocity;
    static int[] brain;
    static List<Integer>[] links;
    private static boolean[] isVisit;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        size = new int[N + 1];
        velocity = new int[N + 1];
        brain = new int[N + 1];
        B = new int[N + 1];
        links = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            size[i] = Integer.parseInt(st.nextToken());
            velocity[i] = Integer.parseInt(st.nextToken());
            brain[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                if (size[i] == size[j] && velocity[i] == velocity[j] && brain[i] == brain[j] && i > j) {
                    continue;
                }

                if (size[i] >= size[j] && velocity[i] >= velocity[j] && brain[i] >= brain[j]) {
                    links[i].add(j);
                }
            }
        }

        int count = 0;
        isVisit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(isVisit, false);
            if (dfs(i)) {
                count++;
            }

            Arrays.fill(isVisit, false);
            if (dfs(i)) {
                count++;
            }
        }

        System.out.println(N - count);
    }

    private static boolean dfs(int x) {
        for (int y : links[x]) {
            if (isVisit[y]) {
                continue;
            }

            isVisit[y] = true;
            if (B[y] == 0 || dfs(B[y])) {
                B[y] = x;
                return true;
            }
        }

        return false;
    }
}

/* INPUT
5
4 5 5
10 10 8
5 7 10
8 7 7
8 10 3

 */
