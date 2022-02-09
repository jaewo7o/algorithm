package com.jaewoo.algorithm.boj.graph.dijkstra.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1389 {

    /*
        Dijkstra, Floyd Warshall, bfs/dfs로 문제 풀이 가능
     */

    private static int N;
    private static int M;
    private static List<Integer>[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        links = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1, s, e; i <= M; i++) {
            token = new StringTokenizer(br.readLine());

            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());

            links[s].add(e);
            links[e].add(s);
        }

        int minDist, curDist, minIndex = 0;
        minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            curDist = dijkstra(i);

            if (minDist > curDist) {
                minDist = curDist;
                minIndex = i;
            }
        }

        System.out.println(minIndex);
    }

    private static int dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList();
        q.offer(start);
        dist[start] = 0;

        int current, nextDist;
        while (!q.isEmpty()) {
            current = q.poll();

            for (int next : links[current]) {
                nextDist = dist[current] + 1;

                if (dist[next] > nextDist) {
                    dist[next] = nextDist;
                    q.offer(next);
                }
            }
        }

        int sumDist = 0;
        for (int i = 1; i <= N; i++) {
            sumDist += dist[i];
        }

        return sumDist;
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
