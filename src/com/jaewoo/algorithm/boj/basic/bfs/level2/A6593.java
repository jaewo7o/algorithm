package com.jaewoo.algorithm.boj.basic.bfs.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A6593 {

    static int L;
    static int R;
    static int C;

    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dc = {-1, 0, 1, 0, 0, 0};
    static int[] dr = {0, -1, 0, 1, 0, 0};

    static char[][][] maps;
    static boolean[][][] visit;
    static Queue<Position> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ( true ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            maps = new char[L + 1][R + 1][C + 1];
            visit = new boolean[L + 1][R + 1][C + 1];
            q = new LinkedList();

            Position end = null;
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    String line = br.readLine();
                    for (int c = 1; c <= C; c++) {
                        maps[l][r][c] = line.charAt(c - 1);

                        if (maps[l][r][c] == 'S') {
                            q.offer(new Position(l, r, c, 0));
                            visit[l][r][c] = true;
                        }

                        if (maps[l][r][c] == 'E') {
                            end = new Position(l, r, c, 0);
                        }
                    }
                }

                br.readLine();
            }

            int time = bfs(end);

            if (time == 0) {
                System.out.println("Trapped!");
            } else {
                System.out.format("Escaped in %d minute(s)\n", time);
            }
        }
    }

    private static int bfs(Position end) {

        int timeSum = 0;

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i=0, nextLevel, nextRow, nextColumn; i<6; i++) {
                nextLevel = now.level + dl[i];
                nextRow = now.row + dr[i];
                nextColumn = now.column + dc[i];

                // out of building
                if (nextLevel < 1 || nextRow < 1 || nextColumn < 1 || nextLevel > L || nextRow > R || nextColumn > C) {
                    continue;
                }

                // 벽이 있는 경우 이동 불가
                if (maps[nextLevel][nextRow][nextColumn] == '#') {
                    continue;
                }

                if (!visit[nextLevel][nextRow][nextColumn]) {
                    timeSum = now.time + 1;

                    // 도착점에 도달한 경우 경과 시간 반환
                    if (end.level == nextLevel && end.row == nextRow && end.column == nextColumn) {
                        return timeSum;
                    }

                    // 도착점에 도달하지 않은 경우 다음 위치 큐에 추가
                    q.offer(new Position(nextLevel, nextRow, nextColumn, timeSum));
                    // 큐에 담은 위치는 방문완료 표시
                    visit[nextLevel][nextRow][nextColumn] = true;
                }
            }
        }

        return timeSum;
    }

    private static class Position {
        public int level;
        public int row;
        public int column;
        public int time;

        public Position(int level, int row, int column, int time) {
            this.level = level;
            this.row = row;
            this.column = column;
            this.time = time;
        }
    }
}

/* INPUT
3 4 5
S....
.###.
.##..
###.#

#####
#####
##.##
##...

#####
#####
#.###
####E

1 3 3
S##
#E#
###

0 0 0
 */
