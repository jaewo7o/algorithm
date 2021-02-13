package com.jaewoo.algorithm.boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A2750 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N];
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] result1 = selectionSort(Arrays.copyOf(numbers, N));
        System.out.println("selectionSort Result : \n");
        IntStream.of(result1).forEach(System.out::println);

        int[] result2 = bubbleSort(Arrays.copyOf(numbers, N));
        System.out.println("bubbleSort Result : \n");
        IntStream.of(result2).forEach(System.out::println);

        int[] result3 = insertionSort(Arrays.copyOf(numbers, N));
        System.out.println("insertionSort Result\n");
        IntStream.of(result3).forEach(System.out::println);
    }

    private static int[] insertionSort(int[] numbers) {

        return numbers;
    }

    private static int[] bubbleSort(int[] numbers) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N-1-i; j++) {
                if (numbers[j] > numbers[j+1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        return numbers;
    }

    public static int[] selectionSort(int[] numbers) {
        for (int i=0; i<N-1; i++) {
            int min = numbers[i];
            int minIndex = i;
            for (int j=i+1; j<N; j++) {
                if (min > numbers[j]) {
                    min = numbers[j];
                    minIndex = j;
                }
            }

            numbers[minIndex] = numbers[i];
            numbers[i] = min;
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