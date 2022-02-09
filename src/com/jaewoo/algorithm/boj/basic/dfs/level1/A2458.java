package com.jaewoo.algorithm.boj.basic.dfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class A2458 {

    private static int N;
    private static int M;

    private static List<Integer>[] links;
    private static List<Integer>[] revLinks;

    private static boolean[] isVisit;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        links = new List[N + 1];
        revLinks = new List[N + 1];
        isVisit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
            revLinks[i] = new ArrayList<>();
        }

        for (int i = 1, s, e; i <= M; i++) {
            token = new StringTokenizer(br.readLine());

            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());

            links[s].add(e);
            revLinks[e].add(s);
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            count = 0;
            Arrays.fill(isVisit, false);
            dfs(links, i);

            Arrays.fill(isVisit, false);
            dfs(revLinks, i);

            if (count == N - 1) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static void dfs(List<Integer>[] links, int start) {
        for (int next : links[start]) {
            if (!isVisit[next]) {
                isVisit[next] = true;
                dfs(links, next);
                count++;
            }
        }
    }
}
