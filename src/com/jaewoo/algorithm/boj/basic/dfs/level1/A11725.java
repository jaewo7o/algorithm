package com.jaewoo.algorithm.boj.basic.dfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A11725 {

    private static int N;
    private static List<Integer>[] links;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        links = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        StringTokenizer token;
        for (int i = 2, u, v; i <= N; i++) {
            token = new StringTokenizer(br.readLine());

            u = Integer.parseInt(token.nextToken());
            v = Integer.parseInt(token.nextToken());
            links[u].add(v);
            links[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (parents[i] == 0) {
                dfs(i);
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }
    }

    private static void dfs(int start) {
        for (int next : links[start]) {
            if (parents[next] == 0) {
                parents[next] = start;
                dfs(next);
            }
        }
    }
}
