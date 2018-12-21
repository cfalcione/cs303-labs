package cfalcione.cs303.lab01;


import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.util.*;

public class Lab01 extends Lab {

    public static final int ARRAY_SIZE = 512;

    public void main(String[] args) {
        Integer[] array = Helpers.getRandomArray(ARRAY_SIZE);
        Integer target = Helpers.getRandomElement(array);
        Arrays.sort(array);

        System.out.println("Looking for " + target + " in:");
        Helpers.printArray(array, 16);

        int linearIndex = linearSearch(array, target);
        System.out.println("Linear Search: " + linearIndex);
        int binaryIndex = binarySearch(array, target);
        System.out.println("Binary Search: " + binaryIndex);
    }

    public static int linearSearch(Integer[] array, Integer target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) return i;
        }
        return -1;
    }

    public static int binarySearch(Integer[] array, Integer target) {
        //assume array is already sorted
        return binarySearch(array, target, 0, array.length - 1);
    }

    public static int binarySearch(Integer[] array, Integer target, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) / 2;

        if (target > array[mid])
            return binarySearch(array, target, mid + 1, end);
        if (target < array[mid])
            return binarySearch(array, target, start, mid - 1);
        return mid;
    }



}
