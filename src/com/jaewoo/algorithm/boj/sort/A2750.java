package com.jaewoo.algorithm.boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N];
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] result1 = selectionSort(Arrays.copyOf(numbers, N));
        IntStream.of(result1).forEach(System.out::println);
    }

    public static int[] selectionSort(int[] numbers) {
        for (int i=0; i<numbers.length-1; i++) {
            for (int j=i+1; j<numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        return numbers;
    }
}

/*
5
5
2
3
4
1
 */
