package agh.cs.oop.Tools;

import java.util.Random;

public class HelperMethods {

    // The min parameter  is inclusive, whereas max is exclusive.
    public static int getRandomNumberFromRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
