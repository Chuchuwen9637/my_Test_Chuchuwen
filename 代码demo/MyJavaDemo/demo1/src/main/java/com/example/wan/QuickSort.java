package com.example.wan;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 6, 1, 8, 7, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);  // 对左侧子数组进行快速排序
            quickSort(arr, pivotIndex + 1, high); // 对右侧子数组进行快速排序
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 选择最后一个元素作为基准值
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // 将基准值放到正确的位置上
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
