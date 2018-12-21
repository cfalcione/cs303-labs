package cfalcione.cs303.shared;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Helpers {

    public static Random random = new Random();

    public static Integer[] getRandomArray(int size) {
        Integer[] output = new Integer[size];
        for (int i = 0; i < size; i++) {
            output[i] = random.nextInt();
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

    public static void printArray(int[] array, int columns) {
        Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        printArray(newArray, columns);
    }

    public static void printArray(int[] array) {
        printArray(array, 16);
    }

    public static <T> void printArray(T[] array, int columns) {
        List<T> list = Arrays.asList(array);
        printIterable(list, columns);
    }

    public static <T> void printArray(T[] array) {
        printArray(array, 1);
    }

    public static <T> T getRandomElement(T[] array) {
        int index = (int) (random.nextDouble() * array.length);
        return array[index];
    }

    public static <T extends Comparable<T>> boolean areArraysEqual(T[] a, T[] b) {
        if (a.length != b.length) return false;

        for(int i = 0; i < a.length; i++) {
            if (a[i].compareTo(b[i]) != 0)
                return false;
        }

        return true;
    }

    @SafeVarargs
    public static <T> T[] makeGenericArray( T ...array) {
        return Arrays.copyOf(array, array.length);
    }

    public static <T extends Comparable<T>> boolean isSorted (T[] array) {
        if (array.length < 2) return true;
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i - 1]) < 0) return false;
        }
        return true;
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
