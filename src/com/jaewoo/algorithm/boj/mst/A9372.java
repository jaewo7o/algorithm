package com.jaewoo.algorithm.boj.mst;

import java.io.*;
import java.util.*;

public class A9372 {
    static int T;
    static int N;
    static int M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            Queue<Route> routes = new LinkedList<>();
            int s, e;
            for (int i=1; i<=M; i++) {
                st = new StringTokenizer(br.readLine());

                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                routes.add(new Route(s, e));
            }

            parents = new int[N + 1];
            for (int i=1; i<=N; i++) {
                parents[i] = i;
            }

            int sum = 0;
            for (int i=1; i<=M; i++) {
                Route route = routes.poll();

                if (findParent(route.s) != findParent(route.e)) {
                    sum++;
                    union(route.s, route.e);
                }
            }

            bw.write(sum + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void union(int s, int e) {
        s = findParent(s);
        e = findParent(e);
        if (s > e) {
            parents[s] = e;
        } else {
            parents[e] = s;
        }
    }

    private static int findParent(int s) {
        if (parents[s] == s) {
            return s;
        }

        parents[s] = findParent(parents[s]);
        return parents[s];
    }

    private static class Route {
        public int s;
        public int e;

        public Route(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "s=" + s +
                    ", e=" + e +
                    '}';
        }
    }
}
