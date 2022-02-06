package com.jaewoo.algorithm.boj.graph.dijkstra.level1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1238 {

    /**
     * Floyd_warshall or dijkstra로 문제 풀이 가능
     * dijkstra 문제풀이 URL : https://steady-coding.tistory.com/106
     */

    static int N;
    static int M;
    static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int [] dist = new int[N + 1];
        int [] revDist = new int[N + 1];
        List<Edge>[] linkEdges = new ArrayList[N + 1];
        List<Edge>[] revLinkEdges = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            // 노드까지 거리 초기화
            dist[i] = Integer.MAX_VALUE;
            revDist[i] = Integer.MAX_VALUE;

            linkEdges[i] = new ArrayList<>();
            revLinkEdges[i] = new ArrayList<>();
        }

        int s, e, w;
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // 길은 단방향이기 때문에 목적지까지와 목적지에서 출발하는 경우 2가지를 역방향으로 저장한다.
            linkEdges[s].add(new Edge(e, w));
            revLinkEdges[e].add(new Edge(s, w));
        }

        dijkstra(revLinkEdges, revDist, X);
        dijkstra(linkEdges, dist, X);

        // 최장거리 계산
        int maxDist = 0;
        for (int i=1; i<=N; i++) {
            maxDist = Math.max(maxDist, dist[i] + revDist[i]);
        }

        bw.write(maxDist + "\n");

        bw.flush();
        bw.close();
    }

    private static void dijkstra(List<Edge>[] linkEdges, int[] dist, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 출발점 노드 설정
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now, next, nextDistance;
        while (!pq.isEmpty()) {
            now = pq.poll().end;

            for (Edge nextEdge : linkEdges[now]) {
                next = nextEdge.end;
                nextDistance = dist[now] + nextEdge.weight;
                if (nextDistance < dist[next]) {
                    dist[next] = nextDistance;
                    pq.offer(nextEdge);
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int end;
        public int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}


/* INPUT
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
*/
