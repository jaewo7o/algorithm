package com.jaewoo.algorithm.boj.graph.bellman_ford.level2;

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

    static Edge[] links;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        links = new Edge[M + 1];

        for (int i = 1, u, v, w; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            links[i] = new Edge(u, v, w);
        }

        boolean cycle = bellmanFord(1);
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

    public static boolean bellmanFord(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        int nextDist;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Edge e = links[j];
                nextDist = dist[e.u] + e.w;
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.v] > nextDist) {
                    dist[e.v] = nextDist;

                    if (i == N) {
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
