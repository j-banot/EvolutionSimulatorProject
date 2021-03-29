package agh.cs.oop.main;

public class World {

    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal.toString());
        String[] moveDirections = {"r", "f", "f", "f"};
        MoveDirection[] moveSequence = OptionsParser.parse(moveDirections);
        for (MoveDirection move : moveSequence) {
            animal.move(move);
        }
        System.out.println(animal.toString());
    }
}
