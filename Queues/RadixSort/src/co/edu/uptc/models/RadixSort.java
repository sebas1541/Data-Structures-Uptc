package co.edu.uptc.models;

import co.edu.uptc.structures.MyPriorityQueue;

public class RadixSort {

    public static void sort(int[] numbers) {
        MyPriorityQueue<Integer> queues = new MyPriorityQueue<>(6);

        for (int place = 1; place <= 100; place *= 10) {

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

}
