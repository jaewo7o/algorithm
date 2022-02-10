package com.jaewoo.algorithm.boj.graph.spfa.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A3860 {
    private static int W;
    private static int H;
    private static int[][] map;
    private static int[][] dist;
    private static int[][] visit;
    private static Map<String, List<Edge>> paths;

    private static int[] dw = {1, 0, -1, 0};
    private static int[] dh = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if ("0 0".equals(line)) {
                break;
            }

            StringTokenizer token = new StringTokenizer(line);

            W = Integer.parseInt(token.nextToken());
            H = Integer.parseInt(token.nextToken());

            map = new int[W][H];
            dist = new int[W][H];
            visit = new int[W][H];
            paths = new HashMap<>();
            for (int i = 0; i < W; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            int g = Integer.parseInt(br.readLine());
            for (int i = 1, w, h; i <= g; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);

                w = Integer.parseInt(token.nextToken());
                h = Integer.parseInt(token.nextToken());
                map[w][h] = -1;
            }

            for (int w = 0; w < W; w++) {
                for (int h = 0; h < H; h++) {
                    paths.put(getPosition(w, h), new ArrayList());
                }
            }

            int e = Integer.parseInt(br.readLine());
            for (int i = 1, sw, sh, ew, eh, t; i <= e; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);

                sw = Integer.parseInt(token.nextToken());
                sh = Integer.parseInt(token.nextToken());
                ew = Integer.parseInt(token.nextToken());
                eh = Integer.parseInt(token.nextToken());
                t = Integer.parseInt(token.nextToken());

                map[sw][sh] = 1;
                paths.get(getPosition(sw, sh)).add(new Edge(ew, eh, t));
            }

            searchPath();
            boolean isCycle = spfa();

            if (isCycle) {
                System.out.println("Never");
            } else {
                if (dist[W - 1][H - 1] == Integer.MAX_VALUE) {
                    System.out.println("Impossible");
                } else {
                    System.out.println(dist[W - 1][H - 1]);
                }
            }
        }

    }

    private static String getPosition(int w, int h) {
        StringBuilder sb = new StringBuilder();
        return sb.append(w).append("-").append(h).toString();
    }

    private static int[] getPosArray(String position) {
        int[] pos = new int[2];
        int index = 0;
        for (String token : position.split("-")) {
            pos[index++] = Integer.parseInt(token);
        }

        return pos;
    }

    private static void searchPath() {
        for (int w = 0, nextW; w < W; w++) {
            for (int h = 0, nextH; h < H; h++) {
                // 도착점은 path 생성 필요 없음
                if (w == -1 && h == -1) {
                    continue;
                }

                // 비석위치는 이동이 불가능하기 때문에 Path 생성 필요 없음
                // 귀신구멍은 별도로 이동 path를 만들기 때문에 여기서 Path 생성 필요 없음
                if (map[w][h] != 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    nextW = w + dw[i];
                    nextH = h + dh[i];

                    // map을 벗어 나는 경우는 skip
                    if (nextW < 0 || nextH < 0 || nextW >= W || nextH >= H) {
                        continue;
                    }

                    // 비석위치가 아닌 경우는 path 연결 가능
                    if (map[w][h] != -1) {
                        paths.get(getPosition(w, h)).add(new Edge(nextW, nextH, 1));
                    }
                }
            }
        }
    }

    private static boolean spfa() {
        Queue<String> q = new LinkedList<>();
        dist[0][0] = 0;
        visit[0][0] = 1;
        q.offer(getPosition(0, 0));

        int[] pos;
        int[] nextPos;
        int nextDist, nextW, nextH;
        while (!q.isEmpty()) {
            pos = getPosArray(q.poll());

            for (Edge edge : paths.get(getPosition(pos[0], pos[1]))) {
                nextDist = dist[pos[0]][pos[1]] + edge.t;

                nextPos = getPosArray(edge.p);
                nextW = nextPos[0];
                nextH = nextPos[1];
                if (dist[nextW][nextH] > nextDist) {
                    dist[nextW][nextH] = nextDist;

                    String nextPosition = getPosition(nextW, nextH);
                    if (!q.contains(nextPosition)) {
                        q.offer(nextPosition);
                    }

                    if (++visit[nextW][nextH] > W * H) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class Edge {
        public String p;
        public int t;

        public Edge(int w, int h, int t) {
            this.p = getPosition(w, h);
            this.t = t;
        }
    }
}
