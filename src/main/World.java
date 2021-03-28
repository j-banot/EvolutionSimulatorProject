package main;

public class World {

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1.toString());
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2.toString());
        System.out.println((position1.add(position2)).toString());
    }
}
