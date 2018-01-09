package cfalcione.cs303;

import cfalcione.cs303.lab01.Lab01;

public class Main {

    public static void main(String[] args) {
        Lab lab = getCurrentLab();
        lab.main(args);
    }

    private static Lab getCurrentLab() {
        return new Lab01();
    }
}
