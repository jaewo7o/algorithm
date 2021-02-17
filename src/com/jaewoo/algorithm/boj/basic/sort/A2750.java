package com.jaewoo.algorithm.boj.basic.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class A2750 {
    static int N;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] result1 = selectionSort(Arrays.copyOf(numbers, N));
        System.out.print("selectionSort Result : ");
        IntStream.of(result1).forEach(x -> System.out.print(x + " "));
        System.out.println("\n");

        int[] result2 = bubbleSort(Arrays.copyOf(numbers, N));
        System.out.print("bubbleSort Result : ");
        IntStream.of(result2).forEach(x -> System.out.print(x + " "));
        System.out.println("\n");

        int[] result3 = insertionSort(Arrays.copyOf(numbers, N));
        System.out.print("insertionSort Result : ");
        IntStream.of(result3).forEach(x -> System.out.print(x + " "));
        System.out.println("\n");

        int[] result4 = quickSort(Arrays.copyOf(numbers, N));
        System.out.print("quickSort Result : ");
        IntStream.of(result4).forEach(x -> System.out.print(x + " "));
        System.out.println("\n");

        temp = new int[N];
        int[] result5 = mergeSort(Arrays.copyOf(numbers, N));
        System.out.print("mergeSort Result : ");
        IntStream.of(result5).forEach(x -> System.out.print(x + " "));
    }

    private static int[] mergeSort(int[] numbers) {
        mergeSort(numbers, 0, N - 1);
        return numbers;
    }

    private static void mergeSort(int[] numbers, int left, int right) {
        if (left == right) {
            return;
        }
        
        int middle = (left + right) / 2;
        mergeSort(numbers, left, middle);
        mergeSort(numbers, middle + 1, right);
        merge(numbers, left, right);
    }

    private static void merge(int[] numbers, int left, int right) {
        int middle = (left + right) / 2;
        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (numbers[i] < numbers[j]) {
                temp[k++] = numbers[i];
                i++;
            } else {
                temp[k++] = numbers[j];
                j++;
            }
        }

        if (i > middle) {
            for (int x = j; x <= right; x++) {
                temp[k++] = numbers[x];
            }
        } else {
            for (int x = i; x <= middle; x++) {
                temp[k++] = numbers[x];
            }
        }

        for (int x = left; x <= right; x++) {
            numbers[x] = temp[x];
        }
    }

    private static int[] quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
        return numbers;
    }

    private static void quickSort(int[] numbers, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = partition(numbers, low, high);
        quickSort(numbers, low, mid - 1);
        quickSort(numbers, mid, high);
    }

    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[(low + high)/2];
        while (low <= high) {
            while (numbers[low] < pivot) {
                low++;
            }

            while (numbers[high] > pivot) {
                high--;
            }

            if (low <= high) {
                swap(numbers, low, high);
                low++;
                high--;
            }
        }

        return low;
    }

    private static int[] insertionSort(int[] numbers) {
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (numbers[j - 1] > numbers[j]) {
                    swap(numbers, j - 1, j);
                }
            }
        }
        return numbers;
    }

    private static void swap(int[] numbers, int a, int b) {
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }

    private static int[] bubbleSort(int[] numbers) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                }
            }
        }
        return numbers;
    }

    public static int[] selectionSort(int[] numbers) {
        for (int i = 0; i < N - 1; i++) {
            int min = numbers[i];
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
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

7
6
5
1
4
7
2
3
*/