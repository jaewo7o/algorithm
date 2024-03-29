package com.jaewoo.algorithm.boj.graph.dijkstra.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A5719 {

    static int N;
    static int M;

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
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

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

                edges[u].add(new Edge(v, w));
            }

            dist = new int[N];

            // 최단거리 계산
            dijkstra(s);

            // back trace 후 최단거리 삭제
            removeShortestPath(e);

            // 최단거리 재계산(거의 최단거리)
            dijkstra(s);

            if (dist[e] == Integer.MAX_VALUE) {
                System.out.println("-1");
            } else {
                System.out.println(dist[e]);
            }
        }
    }

    private static void removeShortestPath(int start) {
        Queue<Integer> q = new LinkedList();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Edge nextEdge : shortestPaths[now]) {
                int next = nextEdge.e;
                if (dist[now] == dist[next] + nextEdge.w) {
                    edges[next].removeIf(x -> now == x.e);
                    q.offer(next);
                }
            }
        }
    }

    private static void dijkstra(int start) {
        boolean[] isVisit = new boolean[N];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now, next, nextDist;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            now = edge.e;
            if (isVisit[now]) {
                continue;
            }

            isVisit[now] = true;

            for (Edge nextEdge : edges[now]) {
                next = nextEdge.e;
                nextDist = dist[now] + nextEdge.w;
                if (!isVisit[next] && dist[next] >= nextDist) {
                    System.out.println(String.format("now : %d, next : %d, oldDist : %d, newDist : %d", now, next, dist[next], nextDist));
                    dist[next] = nextDist;
                    pq.offer(new Edge(next, nextDist));
                    shortestPaths[next].add(new Edge(now, nextEdge.w));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int e;
        public int w;

        public Edge(int e, int w) {
            this.e = e;
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
