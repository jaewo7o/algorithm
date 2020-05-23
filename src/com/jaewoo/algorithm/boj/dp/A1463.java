package com.jaewoo.algorithm.boj.dp;

import java.util.Scanner;

public class A1463 {
    // 설명
    // https://blog.naver.com/PostView.nhn?blogId=occidere&logNo=220787315353
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d[] = new int[n + 1];
        d[0] = d[1] = 0;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            } else if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }
        }

        System.out.printf(String.valueOf(d[n]));
    }
}
