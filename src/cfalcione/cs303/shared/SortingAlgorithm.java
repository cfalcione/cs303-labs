package cfalcione.cs303.shared;

import java.util.Timer;

public abstract class SortingAlgorithm <T extends Comparable<T>> {

    public abstract void sort(T[] array);

    public abstract String name();

    /**
     * Sorts an array in place and returns
     * the number of naoseconds required to
     * do so
     * @param array the array to be sorted
     * @return
     */
    public long time(T[] array) {
        long startTime = System.nanoTime();

        this.sort(array);

        return System.nanoTime() - startTime;
    }
}
