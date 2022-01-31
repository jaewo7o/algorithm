package com.jaewoo.algorithm.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A2839 {
    /*
        DP로도 해결 가능한 문제 : https://jhhj424.tistory.com/40
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        while (true) {
            if (n % 5 == 0) {
                count += n / 5;
                break;
            } else {
                if (n < 3) {
                    count = -1;
                    break;
                } else {
                    n = n - 3;
                    count++;
                }
            }
        }

        System.out.println("count = " + count);
    }
}
