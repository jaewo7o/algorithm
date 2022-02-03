package com.jaewoo.algorithm.boj.graph.spfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class A1738 {

    static int N;
    static int M;

    static List<Edge>[] edges;
    static int[] dist;
    static Queue<Integer> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N + 1];
        for (int n = 1; n <= N; n++) {
            edges[n] = new ArrayList<>();
        }

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MIN_VALUE);

        for (int m = 1, u, v, w; m <= M; m++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            edges[u].add(new Edge(v, w));
        }

        boolean cycle = spfa(1);

        if (cycle) {
            System.out.println("-1");
        } else {
            String pathString = path.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(pathString);
        }
    }

    private static boolean spfa(int start) {
        int[] visit = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();

        path = new LinkedList<>();

        q.offer(start);
        path.offer(start);
        dist[start] = 0;
        visit[start]++;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Edge edge : edges[now]) {
                if (dist[edge.v] < dist[now] + edge.w) {
                    dist[edge.v] = dist[now] + edge.w;

                    if (!path.contains(edge.v)) {
                        path.offer(edge.v);
                    }

                    q.offer(edge.v);

                    if (++visit[edge.v] >= N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Edge {
        public int v;
        public int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}

/* input
5 7
1 2 3
1 3 4
3 1 -7
2 3 2
3 4 1
4 2 -5
4 5 1
 */
