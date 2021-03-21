package com.jaewoo.algorithm.boj.graph.maximum_flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class A2188 {

    static int stringToInt(String string) {
        char c = string.charAt(0);
        if (c <= 'Z') {
            return c - 'A';
        } else {
            return c - 'a' + 26;
        }
    }

    private static int[][] f;
    private static int[][] c;
    private static int[] prev;
    private static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        f = new int[52][52];
        c = new int[52][52];
        prev = new int[52];
        adj = new ArrayList[52];

        for (int i = 0; i < 52; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = stringToInt(st.nextToken());
            int v = stringToInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            c[u][v] = w;
            adj[u].add(v);
            adj[v].add(u);
        }

        int start = stringToInt("A");
        int end = stringToInt("Z");
        int result = maxFlow(start, end);
        System.out.println(result);
    }

    private static int maxFlow(int start, int end) {
        int result = 0;

        while (true) {
            Arrays.fill(prev, -1);
            PriorityQueue<Integer> pq = new PriorityQueue();
            pq.offer(start);

            while (!pq.isEmpty()) {
                int now = pq.poll();
                for (int next : adj[now]) {
                    if (c[now][next] - f[now][next] > 0 && prev[next] == -1) {
                        pq.offer(next);
                        prev[next] = now;
                        if (next == end) {
                            break;
                        }
                    }
                }
            }

            // bfs에서 종료지점까지 흐르지 않은 경우 더 이상 흐르는 경우가 없음을 의미함
            if (prev[end] == -1) {
                break;
            }

            // 최소 유량을 찾음
            int flow = Integer.MAX_VALUE;
            for (int i = end; i != start; i = prev[i]) {
                flow = Math.min(flow, c[prev[i]][i] - f[prev[i]][i]);
            }

            // 유량 업데이트(음의 유량 고려)
            for (int i = end; i != start; i = prev[i]) {
                f[prev[i]][i] = flow;
                f[i][prev[i]] = -flow;
            }

            result += flow;
        }

        return result;
    }
}

/*
5
A B 3
B C 3
C D 5
D Z 4
B Z 6
 */
