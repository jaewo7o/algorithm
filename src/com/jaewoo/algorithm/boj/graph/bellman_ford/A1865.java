package com.jaewoo.algorithm.boj.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1865 {
    static int T;
    static int N;
    static int M;
    static int W;

    static int[] dist;
    static List<Link>[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N + 1];
            links = new List[N + 1];
            for (int i=1; i<=N; i++) {
                links[i] = new ArrayList<>();
            }

            for (int i=1, u, v, w; i<=M+W; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                if (i <= M) {
                    links[u].add(new Link(v, w));
                    links[v].add(new Link(u, w));
                } else {
                    links[u].add(new Link(v, -w));
                }
            }

            System.out.println("Bellman Ford Output");
            boolean cycle = bellmanFord(1);
            printResult(cycle);

            System.out.println("SPFA Output");
            cycle = spfa(1);
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
        dist[start] = 0;
        for (int i=1; i<N; i++) {
            for (int j=1; j<=N; j++) {
                for (Link e : links[j]) {
                    if (dist[j] != Integer.MAX_VALUE && dist[e.v] > dist[j] + e.w) {
                        dist[e.v] = dist[j] + e.w;
                    }
                }
            }
        }

        for (int j=1; j<=N; j++) {
            for (Link e : links[j]) {
                if (dist[j] != Integer.MAX_VALUE && dist[e.v] > dist[j] + e.w) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean spfa(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        int[] cycle = new int[N + 1];
        cycle[start]++;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Link link : links[now]) {
                int next = link.v;
                int nextCost = dist[now] + link.w;

                if (dist[next] > nextCost) {
                    dist[next] = nextCost;

                    if (++cycle[next] >= N) {
                        return true;
                    }

                    q.offer(next);
                }
            }
        }

        return false;
    }

    private static class Link {
        public int v;
        public int w;

        public Link(int v, int w) {
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
