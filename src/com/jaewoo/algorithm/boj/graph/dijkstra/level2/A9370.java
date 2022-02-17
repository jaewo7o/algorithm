package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A9370 {

    static int T;
    static int N;
    static int[] dist;

    static int[][] map;

    static int[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int m, t;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            int s, g, h;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[N + 1][N + 1];
            for (int i = 0; i <= N; i++) {
                Arrays.fill(map[i], Integer.MAX_VALUE);
            }

            for (int i=1, u, v, w; i<=m; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                map[u][v] = map[v][u] = w * 2;
            }

            map[g][h] = map[h][g] = map[g][h] - 1;

            target = new int[t + 1];
            for (int i = 1; i <= t; i++) {
                target[i] = Integer.parseInt(br.readLine());
            }

            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dijkstra(s);

            Arrays.sort(target);
            for (int i = 1; i <= t; i++) {
                if (dist[target[i]] % 2 != 0) {
                    System.out.print(target[i] + " ");
                }
            }
        }
    }

    private static void dijkstra(int start) {
        boolean[] isVisit = new boolean[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            now = edge.e;
            if (isVisit[now]) {
                continue;
            }

            isVisit[now] = true;

            for (int next = 1; next <= N; next++) {
                int nextDist = dist[now] + map[now][next];
                if (!isVisit[next] && dist[next] > nextDist) {
                    dist[next] = nextDist;
                    pq.offer(new Edge(next, nextDist));
                }
            }
        }

    }

    public static class Edge implements Comparable<Edge> {
        public int e;
        public int d;

        public Edge(int e, int d) {
            this.e = e;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return this.d - o.d;
        }
    }
}

/* input
2
5 4 2
1 2 3
1 2 6
2 3 2
3 4 4
3 5 3
5
4
6 9 2
2 3 1
1 2 1
1 3 3
2 4 4
2 5 5
3 4 3
3 6 2
4 5 4
4 6 3
5 6 7
5
6
 */


/*
1
5
4
6 9 2
2 3 1
1 2 1
1 3 3
2 4 4
2 5 5
3 4 3
3 6 2
4 5 4
4 6 3
5 6 7
5
6
 */
