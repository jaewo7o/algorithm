package com.jaewoo.algorithm.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A11399 {

    static int N;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);
        int sum = 0;
        for (int i=1; i<=N; i++) {
            time[i] = time[i - 1] + time[i];
            sum += time[i];
        }

        System.out.println(sum);
    }
}

/* input
5
3 1 4 3 2
 */