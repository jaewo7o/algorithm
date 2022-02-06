package com.jaewoo.algorithm.boj.basic.dfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A11724 {
    static int N;
    static int M;
    static List<Integer>[] links;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        links = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1, s, e; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            links[s].add(e);
            links[e].add(s);
        }

        int count = 0;
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    private static void dfs(int i) {
        visit[i] = true;

        for (int n : links[i]) {
            if (!visit[n]) {
                dfs(n);
            }
        }
    }
}


/* input
6 5
1 2
2 5
5 1
3 4
4 6

 */
