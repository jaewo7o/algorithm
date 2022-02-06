package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A10282 {
    private static int T;
    private static int N;
    private static int D;
    private static int C;

    private static List<Edge>[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line);
            N = Integer.parseInt(token.nextToken());
            D = Integer.parseInt(token.nextToken());
            C = Integer.parseInt(token.nextToken());

            links = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                links[i] = new ArrayList<>();
            }

            for (int i = 1, s, e, t; i <= D; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);
                e = Integer.parseInt(token.nextToken());
                s = Integer.parseInt(token.nextToken());
                t = Integer.parseInt(token.nextToken());

                links[s].add(new Edge(s, e, t));
            }

            dijkstra(C);
        }
    }

    private static void dijkstra(int start) {
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }


        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;
        while (!q.isEmpty()) {
            int current = q.poll();
            for (Edge edge : links[current]) {
                int nextDist = dist[current] + edge.time;
                if (dist[edge.end] > nextDist) {
                    dist[edge.end] = nextDist;
                    q.offer(edge.end);
                }
            }
        }

        int maxDist = 0;
        int nodes = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                continue;
            }

            if (dist[i] > maxDist) {
                maxDist = dist[i];
            }

            nodes++;
        }

        System.out.println(nodes + " " + maxDist);
    }

    private static class Edge {
        public int start;
        public int end;
        public int time;

        public Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}

/* INPUT
2
3 2 2
2 1 5
3 2 5
3 3 1
2 1 2
3 1 8
3 2 4
 */
