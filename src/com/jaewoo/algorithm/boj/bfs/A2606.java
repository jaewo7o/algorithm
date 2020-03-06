package com.jaewoo.algorithm.boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A2606 {
    static int N;
    static int M;
    static List<Integer>[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        links = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i=1, s, e; i<=M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            links[s].add(e);
            links[e].add(s);
        }

        int result = bfs(1);
        System.out.println(result);
    }

    private static int bfs(int start) {
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Integer> q = new PriorityQueue();
        q.offer(start);
        visit[start] = true;

        int c, result = 0;
        while (!q.isEmpty()) {
            c = q.poll();

            for (int n : links[c]) {
                if (!visit[n]) {
                    visit[n] = true;
                    q.offer(n);
                    result++;
                }
            }
        }

        return result;
    }
}

/* input
7
6
1 2
2 3
1 5
5 2
5 6
4 7
 */