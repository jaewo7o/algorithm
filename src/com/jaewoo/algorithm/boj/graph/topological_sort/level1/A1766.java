package com.jaewoo.algorithm.boj.graph.topological_sort.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1766 {

    static int N;
    static int M;
    static List<Integer>[] links;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        links = new List[N + 1];
        indegree = new int[N + 1];

        for (int i=1; i<=N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i=1, s, e; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            links[s].add(e);
            indegree[e]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i=1; i<=N; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            sb.append(now).append(" ");

            for (int next : links[now]) {
                if (--indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(sb.toString());
    }
}

/* INPUT
4 2
4 2
3 1
 */
