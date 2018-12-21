package cfalcione.cs303.lab07;

import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.util.Arrays;

public class Lab07 extends Lab{

    public void main(String[] args) {
        Integer[] array = Helpers.getRandomArray(16);
        Helpers.printArray(array, 16);

        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(Arrays.asList(array));
        System.out.println(tree.getRoot().inOrder());

    }
}
