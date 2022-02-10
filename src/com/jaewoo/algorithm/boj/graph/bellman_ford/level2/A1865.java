package com.jaewoo.algorithm.boj.graph.bellman_ford.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1865 {

    /*
        백준 문제 url : https://www.acmicpc.net/problem/1865
    */
    static int T;
    static int N;
    static int M;
    static int W;

    static int[] dist;
    static List<Link> links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N + 1];
            links = new ArrayList<>();

            for (int i = 1, u, v, w; i <= M + W; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                if (i <= M) {
                    links.add(new Link(u, v, w));
                } else {
                    links.add(new Link(u, v, -w));
                }
            }

            boolean cycle = bellmanFord(1);
            printResult(cycle);
        }
    }


    private static void printResult(boolean cycle) {
        if (cycle) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean bellmanFord(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 시작노드 초기화
        dist[start] = 0;

        // 전체 N 반복수행(모든 노드에 대해 최단 거리 계산)
        int newDist;
        for (int i = 1; i <= N; i++) {
            for (Link e : links) {
                newDist = dist[e.u] + e.w;
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.v] > newDist) {
                    dist[e.v] = newDist;

                    // 마지막 노드에서도 최적의 해가 존재하면 싸이클 존재함
                    if (i == N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Link {
        public int u;
        public int v;
        public int w;

        public Link(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}

/* INPUT
2
3 3 1
1 2 2
1 3 4
2 3 1
3 1 3
3 2 1
1 2 3
2 3 4
3 1 8
 */
