package com.jaewoo.algorithm.boj.graph.dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1916 {
    static int N;
    static int M;

    static int[] cost;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        cost = new int[N + 1];
        visit = new boolean[N + 1];
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
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(s, 0));
        cost[s] = 0;

        int now, next, nextCost;
        while(!pq.isEmpty()) {
            now = pq.poll().getEnd();

            for (Edge nextNode : linkEdge[now]) {
                next = nextNode.getEnd();
                nextCost = cost[now] + nextNode.getCost();
                if (nextCost < cost[next]) {
                    cost[next] = nextCost;
                    pq.offer(nextNode);
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int end;
        private int cost;

        public Edge(int end, int weight) {
            this.end = end;
            this.cost = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }

        public int getEnd() {
            return end;
        }

        public int getCost() {
            return cost;
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
