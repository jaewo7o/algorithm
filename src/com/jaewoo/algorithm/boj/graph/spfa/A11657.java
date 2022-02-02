package com.jaewoo.algorithm.boj.graph.spfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A11657 {
    /*
        백준 문제 URL : https://www.acmicpc.net/problem/11657
     */

    static int N;
    static int M;

    static List<Edge>[] linkEdges;

    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];

        linkEdges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            linkEdges[i] = new ArrayList<>();
        }

        for (int i = 1, u, v, w; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            Edge e = new Edge(u, v, w);
            linkEdges[u].add(e);
        }

        boolean cycle = spfa(1);
        printOutput(cycle);
    }

    private static void printOutput(boolean cycle) {
        if (cycle) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    public static boolean spfa(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        int[] visit = new int[N + 1];

        Queue<Integer> q = new LinkedList();
        q.offer(start);
        visit[start]++;

        int nextDist;
        while (!q.isEmpty()) {
            int current = q.poll();

            for (Edge edge : linkEdges[current]) {
                nextDist = dist[current] + edge.w;
                int next = edge.v;
                if (dist[next] > nextDist) {
                    dist[next] = nextDist;
                    q.offer(edge.v);

                    // 해당 정점을 노드수 이상 방문 했다는 것은 싸이클이 존재한다는 의미임.
                    if (++visit[next] >= N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class Edge {
        public int u;
        public int v;
        public int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}

/* INPUT
3 4
1 2 4
1 3 3
2 3 -1
3 1 -2
 */
