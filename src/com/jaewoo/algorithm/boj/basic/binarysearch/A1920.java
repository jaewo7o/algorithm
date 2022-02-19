package com.jaewoo.algorithm.boj.basic.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer token = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(numbers);

        int M = Integer.parseInt(br.readLine());
        token = new StringTokenizer(br.readLine());
        for (int i = 0, v; i < M; i++) {
            v = Integer.parseInt(token.nextToken());
            System.out.println(binarySearch(numbers, 0, numbers.length - 1, v));
        }
    }

    private static int binarySearch(int[] numbers, int start, int end, int find) {
        if (start > end) {
            return 0;
        }

        int mid = (start + end) / 2;
        int midVal = numbers[mid];
        if (midVal == find) {
            return 1;
        } else if (find < midVal) {
            return binarySearch(numbers, start, mid - 1, find);
        } else if (find > midVal) {
            return binarySearch(numbers, mid + 1, end, find);
        }

        return 0;
    }
}

/* INPUT
5
4 1 5 2 3
5
1 3 7 9 5
 */

/* OUTPUT
1
1
0
0
1
 */
