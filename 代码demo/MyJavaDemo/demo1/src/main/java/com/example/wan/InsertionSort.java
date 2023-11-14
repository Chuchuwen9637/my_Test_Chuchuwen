package com.example.wan;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 6, 1, 8, 7, 4};
        insertionSort(arr);
        System.out.println("排序后的数组：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // 将 arr[0..i-1] 中大于 key 的元素向右移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // 将 key 插入到正确的位置
            arr[j + 1] = key;
        }
    }
}
