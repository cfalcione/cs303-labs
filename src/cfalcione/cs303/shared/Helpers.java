package cfalcione.cs303.shared;

public class Helpers {

    public static <T> void printIterable( Iterable<T> iterable, int rowWidth) {
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


}
