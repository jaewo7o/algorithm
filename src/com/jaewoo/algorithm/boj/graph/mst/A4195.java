package com.jaewoo.algorithm.boj.graph.mst;

import java.io.*;
import java.util.*;

public class A4195 {
    static int T;
    static int M;
    static int[] parents;
    static int[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            M = Integer.parseInt(br.readLine());

            int index = 0;
            int nodeSize = 0;
            Map<String, Integer> member = new HashMap<>();
            Queue<Relation> q = new LinkedList<>();
            String a, b;
            for (int i = 1; i <= M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                a = st.nextToken();
                b = st.nextToken();

                if (!member.containsKey(a)) {
                    member.put(a, ++index);
                }

                if (!member.containsKey(b)) {
                    member.put(b, ++index);
                }

                q.offer(new Relation(member.get(a), member.get(b)));
            }

            nodeSize = member.size();
            parents = new int[nodeSize + 1];
            sizes = new int[nodeSize + 1];
            for (int i = 1; i <= nodeSize; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }

            int p1, p2;
            int count = 0;
            while (count < nodeSize - 1) {
                Relation r = q.poll();

                p1 = getParent(r.u);
                p2 = getParent(r.v);

                if (p1 != p2) {
                    System.out.println(union(p1, p2));
                    count++;
                }
            }
        }
    }

    private static int getParent(int s) {
        if (parents[s] == s) {
            return s;
        }

        parents[s] = getParent(parents[s]);
        return parents[s];
    }

    private static int union(int u, int v) {
        int pu = getParent(u);
        int pv = getParent(v);

        if (pu > pv) {
            parents[u] = pv;
            sizes[pv] += sizes[u];
            return sizes[pv];
        } else {
            parents[v] = pu;
            sizes[pu] += sizes[v];
            return sizes[pu];
        }
    }

    private static class Relation {
        public int u;
        public int v;

        public Relation(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
}

/* INPUT
2
3
Fred Barney
Barney Betty
Betty Wilma
3
Fred Barney
Betty Wilma
Barney Betty
*/
