package cfalcione.cs303;

import cfalcione.cs303.lab01.Lab01;
import cfalcione.cs303.lab02.Lab02;
import cfalcione.cs303.lab03.Lab03;
import cfalcione.cs303.lab04.Lab04;
import cfalcione.cs303.shared.Lab;

public class Main {

    public static void main(String[] args) {
        Lab lab = getCurrentLab();
        lab.main(args);
    }

    private static Lab getCurrentLab() {
        return new Lab04();
    }
}
