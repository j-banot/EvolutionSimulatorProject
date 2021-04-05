package agh.cs.oop;

//TO DO: descriptions and comments in the way shown below
// /**
// * Description
// */

import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        ArrayList <MoveDirection> moveDirections = new ArrayList<MoveDirection>();
        int i = 0;
        for (String direction : args) {
            switch (direction) {
                case "f":
                case "forward":
                    moveDirections.add(MoveDirection.FORWARD);
                    i++;
                    break;
                case "b":
                case "backward":
                    moveDirections.add(MoveDirection.BACKWARD);
                    i++;
                    break;
                case "r":
                case "right":
                    moveDirections.add(MoveDirection.RIGHT);
                    i++;
                    break;
                case "l":
                case "left":
                    moveDirections.add(MoveDirection.LEFT);
                    i++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
        MoveDirection[] result = moveDirections.toArray(new MoveDirection[moveDirections.size()]);
        return result;
    }
}
