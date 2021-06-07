package agh.cs.oop.Enums;

//TODO: descriptions and comments in the way shown below
// /**
// * Description
// */

import agh.cs.oop.Objects.Vector2d;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString(){
        switch(this) {
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            case EAST:
                return "Wschód";
            case WEST:
                return "Zachód";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    //clockwise order next direction
    public MapDirection next(){
        switch(this) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    //clockwise order previous direction
    public MapDirection previous(){
        switch(this) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH:
                return new Vector2d(0,1);
            case SOUTH:
                return new Vector2d(0,-1);
            case EAST:
                return new Vector2d(1,0);
            case WEST:
                return new Vector2d(-1,0);
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public int getValueOfDirection() {
        switch(this) {
            case NORTH:
                return 0;
            case EAST:
                return 1;
            case SOUTH:
                return 2;
            case WEST:
                return 3;
            default: throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }

    public MapDirection getDirectionFromValue(int value) {
        switch(value) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default: throw new IllegalArgumentException("Value is out of range 0-3");
        }
    }
}
