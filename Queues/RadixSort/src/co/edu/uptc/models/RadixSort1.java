package co.edu.uptc.models;

import co.edu.uptc.structures.MyPriorityQueue;

public class RadixSort1 {

    public static void sort(int[] numbers) {
        MyPriorityQueue<Integer> queues = new MyPriorityQueue<>(10);

        for (int place = 1; place <= 1000000; place *= 10) {
            for (int number : numbers) {
                int digit = (number / place) % 10;
                queues.push(number, digit);
            }

            int index = 0;
            while (!queues.isEmpty()) {
                numbers[index++] = queues.poll();
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {442, 503, 145, 555, 250, 325, 102, 420, 143, 234};
        System.out.println("Original array:");
        printArray(numbers);

        sort(numbers);

        System.out.println("Sorted array:");
        printArray(numbers);
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}