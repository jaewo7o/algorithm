package com.jaewoo.algorithm.boj.graph.dijkstra.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A10217 {

    private static List<Ticket>[] tickets;
    private static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line);

            int N = Integer.parseInt(token.nextToken());
            int M = Integer.parseInt(token.nextToken());
            int K = Integer.parseInt(token.nextToken());

            times = new int[N + 1];
            tickets = new List[N + 1];
            for (int n = 1; n <= N; n++) {
                tickets[n] = new ArrayList<>();
            }

            for (int k = 1, u, v, c, d; k <= K; k++) {
                token = new StringTokenizer(br.readLine());

                u = Integer.parseInt(token.nextToken());
                v = Integer.parseInt(token.nextToken());
                c = Integer.parseInt(token.nextToken());
                d = Integer.parseInt(token.nextToken());

                tickets[u].add(new Ticket(u, v, c, d));
            }

            boolean isReachable = dijkstra(1, M);

            if (!isReachable) {
                System.out.println("Poor KCM");
            } else {
                System.out.println(times[N]);
            }
        }
    }

    private static boolean dijkstra(int start, int M) {
        Arrays.fill(times, Integer.MAX_VALUE);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(start);
        times[start] = 0;

        int nextTimes = 0;
        while (!pq.isEmpty()) {
            int current = pq.poll();

            for (Ticket t : tickets[current]) {
                nextTimes = times[current] + t.d;
                if (times[t.v] > nextTimes) {
                    times[t.v] = nextTimes;
                    M = M - t.c;

                    if (M < 0) {
                        return false;
                    }

                    pq.offer(t.v);
                }
            }
        }

        return true;
    }

    public static class Ticket implements Comparable<Ticket> {
        public int u;
        public int v;
        public int c;
        public int d;

        public Ticket(int u, int v, int c, int d) {
            this.u = u;
            this.v = v;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Ticket t) {
            return this.d - t.d;
        }
    }
}


/* INPUT
2
3 100 3
1 2 1 1
2 3 1 1
1 3 3 30
4 10 4
1 2 5 3
2 3 5 4
3 4 1 5
1 3 10 6
 */
