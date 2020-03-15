package com.jaewoo.algorithm.boj.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
                dist[i] = Integer.MAX_VALUE;
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

            dist[1] = 0;
            for (int i=1; i<N; i++) {
                for (int j=1; j<=N; j++) {
                    for (Link e : links[j]) {
                        if (dist[j] != Integer.MAX_VALUE && dist[e.v] > dist[j] + e.w) {
                            dist[e.v] = dist[j] + e.w;
                        }
                    }
                }
            }

            boolean cycle = false;
            for (int j=1; j<=N; j++) {
                for (Link e : links[j]) {
                    if (dist[j] != Integer.MAX_VALUE && dist[e.v] > dist[j] + e.w) {
                        cycle = true;
                        break;
                    }
                }
            }

            if (cycle) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
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
