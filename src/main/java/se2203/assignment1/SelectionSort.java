package se2203.assignment1;

import javafx.application.Platform;

public class SelectionSort implements SortingStrategy{

    private int[] list;
    private SortingHubController controller;

    public SelectionSort(int[] array, SortingHubController controller) {
        list = array;
        this.controller = controller;

    }

    @Override
    public void Sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;

            Platform.runLater(() -> controller.updateGraph());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }



    @Override
    public void run() {
    Thread thread = new Thread(()->{
        Sort(list);

    });
    thread.start();

    }
}
