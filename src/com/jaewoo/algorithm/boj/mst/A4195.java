package com.jaewoo.algorithm.boj.mst;

import java.io.*;
import java.util.*;

public class A4195 {
    static int T;
    static int V;
    static int M;
    static int[] parents;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            M = Integer.parseInt(br.readLine());

            int index = 0;
            Map<String, Integer> member = new HashMap<>();
            Queue<Relation> q = new LinkedList<>();
            String a, b;
            for (int i=1; i<=M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                a = st.nextToken();
                b = st.nextToken();
                q.offer(new Relation(a, b));

                if (!member.containsKey(a)) {
                    member.put(a, ++index);
                }

                if (!member.containsKey(b)) {
                    member.put(b, ++index);
                }
            }

            parents = new int[member.size() + 1];
            numbers = new int[member.size() + 1];
            for (int i=1; i<=member.size(); i++) {
                parents[i] = i;
                numbers[i] = 1;
            }

            int p1, p2;
            for (int i=1; i<=M; i++) {
                Relation r = q.poll();

                p1 = member.get(r.a);
                p2 = member.get(r.b);

                p1 = getParent(p1);
                p2 = getParent(p2);

                if (p1 != p2) {
                    parents[p2] = p1;
                    numbers[p1] += numbers[p2];
                    numbers[p2] = 1;

                    bw.write(numbers[p1] + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
    }

    private static int getParent(int s) {
        if (parents[s] == s) {
            return s;
        }

        parents[s] = getParent(parents[s]);
        return parents[s];
    }

    private static class Relation {
        public String a;
        public String b;

        public Relation(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Relation{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
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