package com.jaewoo.algorithm.boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A2178 {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];
        for (int i=1; i<=N; i++) {
            String line = br.readLine();
            for (int j=1; j<=M; j++) {
                map[i][j] = line.charAt(j-1) - '0';
            }
        }

        bfs(1, 1);
        bw.write(map[N][M] + "\n");
        bw.flush();
        bw.close();
    }

    private static void bfs(int startX, int startY) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startX, startY));

        Point now;
        Point next = new Point();
        while (!q.isEmpty()) {
            now = q.poll();

            for (int i=0; i<4; i++) {
                next.x = now.x + dx[i];
                next.y = now.y + dy[i];

                // MAP 영역을 벗어남
                if (next.x < 1 || next.y < 1 || next.x > N || next.y > M) {
                    continue;
                }

                // 방문한 좌표 통과
                if (visit[next.x][next.y]) {
                    continue;
                }

                // 이동 불가능
                if (map[next.x][next.y] == 0) {
                    continue;
                }

                map[next.x][next.y] = map[now.x][now.y] + 1;
                q.offer(new Point(next.x, next.y));
                visit[next.x][next.y] = true;
            }
        }
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
        }
    }
}

/* INPUT
4 6
101111
101010
101011
111011
 */