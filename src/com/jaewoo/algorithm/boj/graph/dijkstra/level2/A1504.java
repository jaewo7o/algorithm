package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.*;
import java.util.*;

public class A1504 {
    static int N;
    static int E;

    static final int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        List<Edge>[] linkEdges = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            linkEdges[i] = new ArrayList<>();
        }

        int s, e, w;
        for (int i=1; i<=E; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            linkEdges[s].add(new Edge(e, w));
            linkEdges[e].add(new Edge(s, w));
        }

        int pass1, pass2;
        st = new StringTokenizer(br.readLine());
        pass1 = Integer.parseInt(st.nextToken());
        pass2 = Integer.parseInt(st.nextToken());

        int dist1 = 0;
        dist1 += dijkstra(linkEdges, 1, pass1);
        dist1 += dijkstra(linkEdges, pass1, pass2);
        dist1 += dijkstra(linkEdges, pass2, N);

        int dist2 = 0;
        dist2 += dijkstra(linkEdges, 1, pass2);
        dist2 += dijkstra(linkEdges, pass2, pass1);
        dist2 += dijkstra(linkEdges, pass1, N);

        if (dist1 >= INF && dist2 >= INF) {
            bw.write("-1\n");
        } else {
            bw.write(Math.min(dist1, dist2) + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int dijkstra(List<Edge>[] linkEdges, int start, int end) {
        int[] dist = new int[N + 1];
        boolean[] isVisit = new boolean[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now, next, nextDist;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            now = edge.end;
            if (dist[now] < edge.dist) {
                continue;
            }

            if (now == end) {
                break;
            }

            if (!isVisit[now]) {
                isVisit[now] = true;
                for (Edge nextEdge : linkEdges[now]) {
                    next = nextEdge.end;
                    nextDist = dist[now] + nextEdge.dist;

                    if (nextDist < dist[next] && !isVisit[next]) {
                        dist[next] = nextDist;
                        pq.offer(new Edge(next, nextDist));
                    }
                }
            }
        }

        return dist[end];
    }

    private static class Edge implements Comparable<Edge> {
        public int end;
        public int dist;

        public Edge(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.dist - edge.dist;
        }
    }
}

/* INPUT
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
*/

/* OUTPUT
7
 */
