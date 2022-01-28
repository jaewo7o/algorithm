package com.jaewoo.algorithm.boj.basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1389 {

    private static int N;
    private static int M;
    private static List<Integer>[] links;

    private static int[] dist;
    private static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringTokenizer token = new StringTokenizer(line);
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        links = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1, s, e; i <= M; i++) {
            line = br.readLine();
            token = new StringTokenizer(line);

            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());
            links[s].add(e);
            links[e].add(s);
        }

        int minKebinBacon = Integer.MAX_VALUE;
        int minKebinBaconIndex = 0;
        for (int i = 1, kebinBacon; i <= N; i++) {
            kebinBacon = bfs(i);

            if (kebinBacon < minKebinBacon) {
                minKebinBacon = kebinBacon;
                minKebinBaconIndex = i;
            }
        }

        System.out.println(minKebinBaconIndex);
    }

    private static int bfs(int start) {
        dist = new int[N + 1];
        isVisit = new boolean[N + 1];

        Queue<Integer> q = new LinkedList();
        q.offer(start);
        dist[start] = 0;
        isVisit[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : links[current]) {
                if (!isVisit[next]) {
                    isVisit[next] = true;
                    q.offer(next);
                    dist[next] = dist[current] + 1;
                }
            }
        }

        int sum = 0;
        for (int i=1; i<=N; i++) {
            sum += dist[i];
        }

        return sum;
    }
}

/* INPUT
5 5
1 3
1 4
4 5
4 3
3 2
 */
