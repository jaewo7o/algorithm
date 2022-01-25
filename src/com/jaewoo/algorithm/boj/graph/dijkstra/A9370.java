package com.jaewoo.algorithm.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A9370 {

    public static final int INF = 10000;
    static int T;
    static int N;
    static int[] dist;
    static int[][] maps;

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

            maps = new int[N + 1][N + 1];
            for (int i=1; i<=N; i++) {
                Arrays.fill(maps[i], INF);
            }

            for (int i=1, u, v, w; i<=m; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                // 길의 weight를 2배처리해서 2의 배수로 만든다.
                maps[u][v] = w * 2;
                maps[v][u] = w * 2;
            }

            // 통과해야 할 간선은 1을 빼서 2의 배수가 안되도록 변경한다.
            maps[g][h] = maps[h][g] = maps[g][h] - 1;

            int[] targets = new int[ t + 1 ];
            for (int i=1; i<=t; i++) {
                targets[i] = Integer.parseInt(br.readLine());
            }

            dist = new int[N + 1];

            Arrays.fill(dist, INF);

            dijkstra(s);

            Arrays.sort(targets);
            for (int i=1; i<=t; i++) {
                // 도착 거리가 2의 배수가 아니라면 간선을 거친 경우로 판단하고 결과를 출력한다.
                if (dist[targets[i]] % 2 == 1) {
                    System.out.print(targets[i] + " ");
                }
            }

            System.out.print("\n");
        }
    }

    private static void dijkstra(int s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(s);
        dist[s] = 0;

        while(!pq.isEmpty()) {
            int now = pq.poll();

            for (int j=1; j<=N; j++) {
                int nextWeight = dist[now] + maps[now][j];
                if (dist[j] > nextWeight) {
                    dist[j] = nextWeight;
                    pq.offer(j);
                }
            }
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
