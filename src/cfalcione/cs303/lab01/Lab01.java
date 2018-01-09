package cfalcione.cs303.lab01;


import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.util.*;

public class Lab01 extends Lab {

    public static final int ARRAY_SIZE = 512;

    protected Random rand = new Random();

    public void main(String[] args) {
        Integer[] array = this.getRandomArray(ARRAY_SIZE);
        Integer target = this.getTarget(array);

        System.out.println("Looking for " + target + " in:");
        Helpers.printIterable(Arrays.asList(array), 16);

        Arrays.sort(array);
        int linearIndex = this.linearSearch(array, target);
        int binaryIndex = this.binarySearch(array, target);

        System.out.println("Linear Search: " + linearIndex);
        System.out.println("Binary Search: " + binaryIndex);
    }

    public int linearSearch(Integer[] array, Integer target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) return i;
        }
        return -1;
    }

    public int binarySearch(Integer[] array, Integer target) {
        //assume array is already sorted
        return this.doBinarySearch(array, target, 0, array.length - 1);
    }

    private int doBinarySearch(Integer[] array, Integer target, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) / 2;

        if (target > array[mid])
            return doBinarySearch(array, target, mid + 1, end);
        if (target < array[mid])
            return doBinarySearch(array, target, start, mid - 1);
        return mid;
    }

    public Integer[] getRandomArray(int size) {
        Integer[] output = new Integer[size];
        for (int i = 0; i < size; i++) {
            output[i] = rand.nextInt();
        }
        return output;
    }

    public Integer getTarget(Integer[] array) {
        int index = (int) (rand.nextDouble() * array.length);
        return array[index];
    }


}
