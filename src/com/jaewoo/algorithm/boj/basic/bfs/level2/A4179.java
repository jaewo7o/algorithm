package com.jaewoo.algorithm.boj.basic.bfs.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A4179 {

    static int R;
    static int C;
    static char[][] maps;

    static Queue<Point> june = new LinkedList<>();
    static Queue<Point> fire = new LinkedList<>();
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};

    static boolean isEscape = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maps = new char[R + 1][C + 1];
        for (int r=1; r<=R; r++) {
            String line = br.readLine();
            for (int c=1; c<=C; c++) {
                char value = line.charAt(c - 1);
                maps[r][c] = value;

                if (value == 'J') {
                    june.offer(new Point(r, c));
                }

                if (value == 'F') {
                    fire.offer(new Point(r, c));
                }
            }
        }

        int time = 0;
        while (true) {
            spread(fire, 'F');

            spread(june, 'J');

            time++;

            if (isEscape) {
                break;
            }

            if (june.isEmpty()) {
                break;
            }
        }

        if (isEscape) {
            System.out.println(time);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static void spread(Queue<Point> q, char data) {
        boolean[][] visit = new boolean[R + 1][C + 1];
        int length = q.size();

        for (int i=1; i<=length; i++) {
            Point p = q.poll();

            for (int d=0; d<4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (isOut(nr, nc)) {
                    if (data == 'J') {
                        isEscape = true;
                        return;
                    } else {
                        continue;
                    }
                }

                if (visit[nr][nc]) {
                    continue;
                }

                if (maps[nr][nc] == '.') {
                    maps[nr][nc] = data;
                    q.offer(new Point(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isOut(int r, int c) {
        if (r >= 1 && r<=R && c>=1 && c<=C) {
            return false;
        } else {
            return true;
        }
    }

    private static class Point {
        public int r;
        public int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

/*
4 4
####
#JF#
#..#
#..#
 */
