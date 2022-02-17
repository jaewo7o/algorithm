package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1916 {
    static int N;
    static int M;

    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cost = new int[N + 1];
        List<Edge>[] linkEdge = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            cost[i] = Integer.MAX_VALUE;
            linkEdge[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int s, e, w;
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            linkEdge[s].add(new Edge(e, w));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dijkstra(linkEdge, s);

        bw.write(cost[e] + "\n");
        bw.flush();
        bw.close();
    }

    private static void dijkstra(List<Edge>[] linkEdge, int s) {
        boolean[] isVisit = new boolean[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(s, 0));
        cost[s] = 0;

        int now, next, nextCost;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            now = edge.end;
            if (isVisit[now]) {
                continue;
            }

            isVisit[now] = true;

            for (Edge nextNode : linkEdge[now]) {
                next = nextNode.end;
                nextCost = cost[now] + nextNode.cost;
                if (!isVisit[next] && nextCost < cost[next]) {
                    cost[next] = nextCost;
                    pq.offer(new Edge(next, nextCost));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int end;
        public int cost;

        public Edge(int end, int weight) {
            this.end = end;
            this.cost = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }
}

/* INPUT
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
*/


/* OUTPUT
4
 */
