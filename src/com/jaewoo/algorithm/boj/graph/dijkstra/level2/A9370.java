package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A9370 {

    static int T;
    static int N;
    static int[] dist;
    static List<Edge>[] links;

    static int[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int m, t;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            int s, g, h;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            links = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                links[i] = new ArrayList<>();
            }

            boolean isMustPath;
            for (int i=1, u, v, w; i<=m; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                if ((u == g && v == h) || (u == h && v == g)) {
                    isMustPath = true;
                } else {
                    isMustPath = false;
                }

                // 길의 weight를 2배처리해서 2의 배수로 만든다.
                links[u].add(new Edge(v, isMustPath? w * 2 + 1 : w * 2));
                links[v].add(new Edge(u, isMustPath? w * 2 + 1 : w * 2));
            }

            target = new int[t + 1];
            for (int i = 1; i <= t; i++) {
                target[i] = Integer.parseInt(br.readLine());
            }

            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dijkstra(s);

            Arrays.sort(target);
            for (int i = 1; i <= t; i++) {
                if (dist[target[i]] % 2 != 0) {
                    System.out.print(target[i] + " ");
                }
            }
        }
    }

    private static void dijkstra(int s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(s);
        dist[s] = 0;

        while(!pq.isEmpty()) {
            int now = pq.poll();

            for (Edge edge : links[now]) {
                int next = edge.e;
                int nextWeight = dist[now] + edge.w;
                if (dist[next] > nextWeight) {
                    dist[next] = nextWeight;
                    pq.offer(next);
                }
            }
        }

    }

    public static class Edge {
        public int e;
        public int w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}

/* input
2
5 4 2
1 2 3
1 2 6
2 3 2
3 4 4
3 5 3
5
4
6 9 2
2 3 1
1 2 1
1 3 3
2 4 4
2 5 5
3 4 3
3 6 2
4 5 4
4 6 3
5 6 7
5
6
 */
