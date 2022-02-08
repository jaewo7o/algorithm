package com.jaewoo.algorithm.boj.graph.dijkstra.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1486 {

    /* 백준 풀이
        https://stonejjun.tistory.com/128
     */

    private static int N;
    private static int M;
    private static int T;
    private static int D;

    private static int[][] map;

    private static int[] dn = {0, 1, 0, -1};
    private static int[] dm = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        T = Integer.parseInt(token.nextToken());
        D = Integer.parseInt(token.nextToken());

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            for (int m = 0; m < M; m++) {
                char c = line.charAt(m);

                if (c >= 'A' && c <= 'Z') {
                    map[n][m] = (int) c - 65;
                } else if (c >= 'a' && c <= 'z') {
                    map[n][m] = (int) c - 71;
                }
            }
        }

        Map<String, List<Link>> links = new HashMap<>();
        Map<String, List<Link>> revLinks = new HashMap<>();
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                String position = getPosition(n, m);
                links.put(position, new ArrayList<>());
                revLinks.put(position, new ArrayList<>());
            }
        }

        // 이동 가능한 점들 사이에 링크 연결
        int nextN, nextM, currentHeight, nextHeight, diff, nextDist, revNextDist;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                for (int i = 0; i < 4; i++) {
                    nextN = n + dn[i];
                    nextM = m + dm[i];

                    // out of box skip
                    if (nextN < 0 || nextM < 0 || nextN >= N || nextM >= M) {
                        continue;
                    }

                    // 높이차가 이동 가능하지 않으면 skip
                    currentHeight = map[n][m];
                    nextHeight = map[nextN][nextM];
                    diff = nextHeight - currentHeight;

                    if (Math.abs(currentHeight - nextHeight) > T) {
                        continue;
                    }

                    String position = getPosition(n, m);
                    String revPosition = getPosition(nextN, nextM);

                    if (diff > 0) {
                        nextDist = diff * diff;
                    } else {
                        nextDist = 1;
                    }

                    if (diff <= 0) {
                        revNextDist = 1;
                    } else {
                        revNextDist = diff * diff;
                    }

                    links.get(position).add(new Link(nextN, nextM, nextDist));
                    revLinks.get(revPosition).add(new Link(n, m, revNextDist));
                }
            }
        }

        int[][] dist = new int[N][M];
        int[][] revDist = new int[N][M];

        // 정방향 최소비용 거리 계산
        dijkstra(links, dist);
        // 역방향 최소비용 거리 계산
        dijkstra(revLinks, revDist);

        int maxPosN = 0, maxPosM = 0;
        int maxValue = 0;
        int curValue;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                // 거리값이 최대인 경우 이동 불가한 경우 이기 때문에 최대도달 높이 대상에서 제외
                if (dist[n][m] == Integer.MAX_VALUE || revDist[n][m] == Integer.MAX_VALUE) {
                    continue;
                }

                // 산을 오르고/내리는 전체 시간 합이 주어진 시간 내에 있는 경우에 대해서 최대 높이를 계산하는 로직
                curValue = dist[n][m] + revDist[n][m];
                if (curValue <= D && map[n][m] > maxValue) {
                    maxValue = map[n][m];
                    maxPosN = n;
                    maxPosM = m;
                }
            }
        }

        System.out.println(map[maxPosN][maxPosM]);
    }

    private static void dijkstra(Map<String, List<Link>> links, int[][] dist) {
        for (int n = 0; n < N; n++) {
            Arrays.fill(dist[n], Integer.MAX_VALUE);
        }

        Queue<String> q = new LinkedList<>();
        q.offer(getPosition(0, 0));
        dist[0][0] = 0;

        int nextDist;
        while (!q.isEmpty()) {
            String nextPosition = q.poll();

            for (Link link : links.get(nextPosition)) {
                int[] pos = getPositionToken(nextPosition);
                nextDist = dist[pos[0]][pos[1]] + link.t;

                if (dist[link.n][link.m] > nextDist) {
                    dist[link.n][link.m] = nextDist;
                    q.offer(getPosition(link.n, link.m));
                }
            }
        }
    }

    private static String getPosition(int n, int m) {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("-").append(m);
        return sb.toString();
    }

    private static int[] getPositionToken(String position) {
        String[] split = position.split("-");
        int [] pos = new int[2];
        pos[0] = Integer.parseInt(split[0]);
        pos[1] = Integer.parseInt(split[1]);
        return pos;
    }

    private static class Link {
        public int n;
        public int m;
        public int t;

        public Link(int n, int m, int t) {
            this.n = n;
            this.m = m;
            this.t = t;
        }
    }
}
