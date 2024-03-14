package co.edu.uptc.test;

import co.edu.uptc.models.RadixSort;

public class Test {
    public static void main(String[] args) {
        int[] numbers = {442, 503, 145, 555, 250, 325, 102, 420, 143, 234};
        System.out.println("Arreglo original:");
        printArray(numbers);

        RadixSort.sort(numbers);

        System.out.println("Arreglo ordenado:");
        printArray(numbers);
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
