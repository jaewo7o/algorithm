package com.jaewoo.algorithm.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            int d[] = new int[N + 1];
            d[1] = 1;
            d[2] = 2;
            d[3] = 4;

            for (int n = 4; n <= N; n++) {
                d[n] = d[n - 1] + d[n - 2] + d[n - 3];
            }

            System.out.println(String.valueOf(d[N]));
        }
    }
}
