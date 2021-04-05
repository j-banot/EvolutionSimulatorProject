package agh.cs.oop;

//TO DO: descriptions and comments in the way shown below
// /**
// * Description
// */

public class World {

    public static void main(String[] args) {
        Animal animal = new Animal();
        RectangularMap map = new RectangularMap(5,5);
        System.out.println(animal.toString());
        String[] moveDirections = {"r", "f", "f", "f"};
        MoveDirection[] moveSequence = OptionsParser.parse(moveDirections);
        for (MoveDirection move : moveSequence) {
            animal.move(move, map);
        }
        System.out.println(animal.toString());
    }
}
