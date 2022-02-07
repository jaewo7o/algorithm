package com.jaewoo.algorithm.boj.graph.bellman_ford.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class A3860 {
    private static int W;
    private static int H;
    private static String[][] map;
    private static int[][] dist;
    private static Map<String, GhostHole> holes;

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

            map = new String[W][H];
            dist = new int[W][H];
            for (int i = 0; i < W; i++) {
                Arrays.fill(map[i], "N");
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            int g = Integer.parseInt(br.readLine());
            for (int i = 1, w, h; i <= g; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);

                w = Integer.parseInt(token.nextToken());
                h = Integer.parseInt(token.nextToken());
                map[w][h] = "G";
            }

            int e = Integer.parseInt(br.readLine());
            holes = new HashMap();
            for (int i = 1, sw, sh, ew, eh, t; i <= e; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);

                sw = Integer.parseInt(token.nextToken());
                sh = Integer.parseInt(token.nextToken());
                ew = Integer.parseInt(token.nextToken());
                eh = Integer.parseInt(token.nextToken());
                t = Integer.parseInt(token.nextToken());

                String startHoleNumber = "H" + i + "-S";
                String endHoleNumber = "H" + i + "-E";

                map[sw][sh] = startHoleNumber;
                map[ew][eh] = endHoleNumber;

                holes.put(startHoleNumber, new GhostHole(sw, sh, ew, eh, t));
                holes.put(endHoleNumber, new GhostHole(ew, eh, sw, sh, t));
            }

            boolean isCycle = bellmanFord();

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

    private static boolean bellmanFord() {

        int nextW, nextH, nextDist = 0;
        String mark;

        dist[0][0] = 0;

        for (int w = 0; w < W; w++) {
            for (int h = 0; h < H; h++) {
                mark = map[w][h];
                if ("G".equals(mark)) {
                    continue;
                }

                if (mark.startsWith("H") && mark.endsWith("S")) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    nextW = w + dw[i];
                    nextH = h + dh[i];
                    mark = map[nextW][nextH];

                    if (nextW < 0 || nextH < 0 || nextW >= W || nextH >= H) {
                        continue;
                    }

                    if ("G".equals(mark)) {
                        continue;
                    } else if (mark.startsWith("H") && mark.endsWith("S")) {
                        GhostHole ghostHole = holes.get(mark);
                        nextW = ghostHole.endW;
                        nextH = ghostHole.endH;

                        nextDist = dist[w][h] + 1 + ghostHole.time;
                    } else {
                        nextDist = dist[w][h] + 1;
                    }

                    if (dist[nextW][nextH] > nextDist) {
                        dist[nextW][nextH] = nextDist;

                        if (w == W - 1 && h == H - 1) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static class GhostHole {
        public int startW;
        public int startH;

        public int endW;
        public int endH;

        public int time;

        public GhostHole(int startW, int startH, int endW, int endH, int time) {
            this.startW = startW;
            this.startH = startH;
            this.endW = endW;
            this.endH = endH;
            this.time = time;
        }
    }
}

/* INPUT
3 3
2
2 1
1 2
0
4 3
2
2 1
3 1
1
3 0 2 2 0
4 2
0
1
2 0 1 0 -3
0 0
 */

/* OUTPUT
Impossible
4
Never
 */
