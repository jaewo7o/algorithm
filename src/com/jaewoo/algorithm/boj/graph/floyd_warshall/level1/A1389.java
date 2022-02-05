package com.jaewoo.algorithm.boj.graph.floyd_warshall.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1389 {

    /*
        floydwarshal과 dfs/bfs로 문제 풀이가 가능
     */

    static int N;
    static int M;
    static int[][] relation;

    static int INF = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        relation = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 자기자신과는 관계가 0으로 세팅
                if (i != j) {
                    relation[i][j] = INF;
                }
            }
        }

        for (int i = 1, s, e; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            relation[s][e] = 1;
            relation[e][s] = 1;
        }

        floyd();

        int minKebinBacon = INF;
        int minPerson = 0;
        int kebinBacon;
        for (int i = 1; i <= N; i++) {
            kebinBacon = 0;
            for (int j = 1; j <= N; j++) {
                kebinBacon += relation[i][j];
            }

            if (kebinBacon < minKebinBacon) {
                minKebinBacon = kebinBacon;
                minPerson = i;
            }
        }

        System.out.println(minPerson);
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (relation[i][j] > relation[i][k] + relation[k][j]) {
                        relation[i][j] = relation[i][k] + relation[k][j];
                    }
                }
            }
        }
    }
}

/* input
5 5
1 3
1 4
4 5
4 3
3 2

 */
