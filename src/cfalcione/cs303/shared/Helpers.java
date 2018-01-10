package cfalcione.cs303.shared;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Helpers {

    public static Random rand = new Random();

    public static Integer[] getRandomArray(int size, Random rand) {
        Integer[] output = new Integer[size];
        for (int i = 0; i < size; i++) {
            output[i] = rand.nextInt();
        }
        return output;
    }

    public static <T> void printIterable(Iterable<T> iterable, int rowWidth) {
        int col = 1;
        for (Object obj : iterable) {
            System.out.print(obj + "\t");
            col++;
            if (col > rowWidth) {
                System.out.println();
                col = 1;
            }
        }
        if (col != 1) System.out.println();
    }

    public static <T> void printInterable(Iterable<T> iterable) {
        printIterable(iterable, 1);
    }

    public static <T> void printArray(T[] array, int columns) {
        List<T> list = Arrays.asList(array);
        printIterable(list, columns);
    }

    public static <T> void printArray(T[] array) {
        printArray(array, 1);
    }

    public static <T> T getRandomElement(T[] array) {
        int index = (int) (rand.nextDouble() * array.length);
        return array[index];
    }


}
