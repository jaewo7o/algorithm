package com.jaewoo.algorithm.boj.basic.bfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1967 {

    /*
        성능문제로 플로이드 와샬은 좋은 솔루션이 아님
        BFS로 거리 계산을 한 후 최대 지름 계산 가능함
     */

    private static int N;
    private static List<Link>[] links;
    private static boolean[] isVisit;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        isVisit = new boolean[N + 1];
        dist = new int[N + 1];
        links = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int n = 2, i, j, d; n <= N; n++) {
            StringTokenizer token = new StringTokenizer(br.readLine());

            i = Integer.parseInt(token.nextToken());
            j = Integer.parseInt(token.nextToken());
            d = Integer.parseInt(token.nextToken());

            links[i].add(new Link(j, d));
            links[j].add(new Link(i, d));
        }

        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(isVisit, false);
            Arrays.fill(dist, Integer.MAX_VALUE);
            bfs(i);

            for (int j = 1; j <= N; j++) {
                if (maxDistance < dist[j]) {
                    maxDistance = dist[j];
                }
            }
        }

        System.out.println(maxDistance);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isVisit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Link link : links[now]) {
                int next = link.e;
                if (!isVisit[next]) {
                    isVisit[next] = true;
                    q.offer(next);
                    dist[next] = Math.min(dist[next], dist[now] + link.w);
                }
            }
        }
    }

    private static class Link {
        public int e;
        public int w;

        public Link(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}

/* INPUT
12
1 2 3
1 3 2
2 4 5
3 5 11
3 6 9
4 7 1
4 8 7
5 9 15
5 10 4
6 11 6
6 12 10
 */
