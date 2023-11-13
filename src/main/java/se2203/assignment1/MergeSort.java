package se2203.assignment1;

import javafx.application.Platform;

import java.util.Arrays;

public class MergeSort implements SortingStrategy  {

    private int [] list;
    private SortingHubController controller;

    public MergeSort(int [] arr, SortingHubController controller){
        list = arr;
        this.controller = controller;
    }

    @Override
    public void Sort(int[] numbers) {
        mergeSortHelper(numbers, 0, numbers.length - 1);
    }

    private void mergeSortHelper(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortHelper(array, left, mid);
            mergeSortHelper(array, mid + 1, right);

            merge(array, left, mid, right);
            Platform.runLater(() -> controller.updateGraph());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

//        //aglorthim goes here
//
//        if (numbers.length > 1) {
//            int mid = numbers.length / 2;
//            int[] left = Arrays.copyOfRange(numbers, 0, mid);
//            int[] right = Arrays.copyOfRange(numbers, mid, numbers.length);
//            Sort(left);
//            Sort(right);
//            merge(numbers, left, right);
//
//        }
//
//    }

//    private void merge(int[] array, int[] left, int[] right) {
//        int i = 0;
//        int j = 0;
//        int k = 0;
//        while (i < left.length && j < right.length) {
//            if (left[i] <= right[j]) {
//                array[k++] = left[i++];
//            } else {
//                array[k++] = right[j++];
//            }
//
//        }
//        while (i < left.length) {
//            array[k++] = left[i++];
//        }
//        while (j < right.length) {
//            array[k++] = right[j++];
//
//
//        }
//
//    }

    @Override
    public void run() {
        Thread thread = new Thread(()->{
            Sort(list);

        });
        thread.start();

    }
}
