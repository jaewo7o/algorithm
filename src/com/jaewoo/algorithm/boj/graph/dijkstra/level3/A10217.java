package com.jaewoo.algorithm.boj.graph.dijkstra.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A10217 {
    private static int N;
    private static int M;

    private static List<Ticket>[] tickets;
    private static int[][] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line);

            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            int K = Integer.parseInt(token.nextToken());

            times = new int[N + 1][M + 1];
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

            dijkstra(1);

            int minTime = Integer.MAX_VALUE;
            for (int i = 1; i <= M; i++) {
                minTime = Math.min(minTime, times[N][i]);
            }

            if (minTime == Integer.MAX_VALUE) {
                System.out.println("Poor KCM");
            } else {
                System.out.println(minTime);
            }
        }
    }

    private static void dijkstra(int start) {
        for (int i = 1; i <= N; i++) {
            Arrays.fill(times[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Airport> pq = new PriorityQueue<>();
        pq.offer(new Airport(start, 0, 0));
        times[start][0] = 0;

        while (!pq.isEmpty()) {
            Airport airport = pq.poll();

            // 마지막 공항에 도착하면 종료
            if (airport.node == N) {
                break;
            }

            for (Ticket t : tickets[airport.node]) {
                int nextNode = t.end;
                int nextTimes = airport.time + t.time;
                int nextMoney = airport.money + t.money;

                // 돈을 초과하는 경우 이동 불가
                if (nextMoney > M) {
                    continue;
                }

                if (times[nextNode][nextMoney] > nextTimes) {
                    times[nextNode][nextMoney] = nextTimes;
                    pq.offer(new Airport(nextNode, nextMoney, nextTimes));
                }
            }
        }
    }

    public static class Ticket {
        public int start;
        public int end;
        public int money;
        public int time;

        public Ticket(int start, int end, int money, int time) {
            this.start = start;
            this.end = end;
            this.money = money;
            this.time = time;
        }
    }

    private static class Airport implements Comparable<Airport> {
        public int node;
        public int money;
        public int time;

        public Airport(int node, int money, int time) {
            this.node = node;
            this.money = money;
            this.time = time;
        }

        @Override
        public int compareTo(Airport a) {
            return this.time - a.time;
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
