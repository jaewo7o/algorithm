package com.jaewoo.algorithm.boj.basic.dfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class A2667 {
    static int N;
    static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String readLine = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = Character.getNumericValue(readLine.charAt(j - 1));
            }
        }

        int totalArea = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1) {
                    totalArea++;
                    count = 0;
                    dfs(i, j);

                    pq.offer(count);
                }
            }
        }

        System.out.println(totalArea);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    private static void dfs(int x, int y) {
        map[x][y] = 0;
        count++;

        int nx, ny;
        for (int k = 0; k < 4; k++) {
            nx = x + dx[k];
            ny = y + dy[k];

            if (nx <= N && ny <= N && nx >= 1 && ny >= 1) {
                if (map[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}

/* INPUT
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
 */
