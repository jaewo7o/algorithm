package com.jaewoo.algorithm.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/// 여기부터 시작
public class A5719 {

    static int N;
    static int M;

    static int S;
    static int D;

    static List<Edge>[] edges;
    static List<Edge>[] shortestPaths;

    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            edges = new List[N];
            shortestPaths = new List[N];
            for (int i=0; i<N; i++) {
                edges[i] = new ArrayList<>();
                shortestPaths[i] = new ArrayList<>();
            }

            for (int i=0, u, v, w; i<M; i++) {
                st = new StringTokenizer(br.readLine());

                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                edges[u].add(new Edge(u, v, w));
            }

            dist = new int[N];
            Arrays.fill(dist, Integer.MAX_VALUE);

            // 최단거리 계산
            dijkstra(S);

            // back trace 후 최단거리 삭제
            removeShortestPath(D);

            // 최단거리 재계산(거의 최단거리)
            Arrays.fill(dist, Integer.MAX_VALUE);
            dijkstra(S);

            if (dist[D] == Integer.MAX_VALUE) {
                System.out.println("-1");
            } else {
                System.out.println(dist[D]);
            }
        }
    }

    private static void removeShortestPath(int start) {
        Queue<Integer> q = new LinkedList();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Edge edge : shortestPaths[now]) {
                if (dist[edge.v] == dist[edge.u] + edge.w) {
                    edges[edge.u].removeIf(x -> edge.v == x.v);
                    q.offer(edge.u);
                }
            }
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(start);

        dist[start] = 0;
        while (!pq.isEmpty()) {
            int now = pq.poll();

            for (Edge edge : edges[now]) {
                if (dist[edge.v] >= dist[now] + edge.w) {
                    dist[edge.v] = dist[now] + edge.w;
                    pq.offer(edge.v);
                    shortestPaths[edge.v].add(edge);
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int u;
        public int v;
        public int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.w - edge.w;
        }
    }
}

/* input
7 9
0 6
0 1 1
0 2 1
0 3 2
0 4 3
1 5 2
2 6 4
3 6 2
4 6 4
5 6 1
4 6
0 2
0 1 1
1 2 1
1 3 1
3 2 1
2 0 3
3 0 2
6 8
0 1
0 1 1
0 2 2
0 3 3
2 5 3
3 4 2
4 1 1
5 1 1
3 0 1
0 0
 */
