package agh.cs.oop.Enums;

/**
 * Enumerator that supports all methods neccessary while using MapDirections
 */

import agh.cs.oop.Objects.Vector2d;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    /**
     * @ return next clockwise value
     */
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

    /**
     * @ return next counter clockwise value
     */
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

    /**
     * Method that supports changing enum value for a 2D Vector value
     * @ return Vector object corresponding to it's enum value
     */
    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH:
                return new Vector2d(0,1);
            case NORTHEAST:
                return new Vector2d(1,1);
            case EAST:
                return new Vector2d(1,0);
            case SOUTHEAST:
                return new Vector2d(1,-1);
            case SOUTH:
                return new Vector2d(0,-1);
            case SOUTHWEST:
                return new Vector2d(-1,-1);
            case WEST:
                return new Vector2d(-1,0);
            case NORTHWEST:
                return new Vector2d(-1,1);
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    /**
     * Method that supports changing enum value for a int number
     * @ return Integer corresponding to it's enum value
     */
    public int getValueOfDirection() {
        switch(this) {
            case NORTH:
                return 0;
            case NORTHEAST:
                return 1;
            case EAST:
                return 2;
            case SOUTHEAST:
                return 3;
            case SOUTH:
                return 4;
            case SOUTHWEST:
                return 5;
            case WEST:
                return 6;
            case NORTHWEST:
                return 7;
            default: throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }

    /**
     * Method that supports changing int number into enum value
     * @ return Enum value corresponding to it's integer value
     */
    public static MapDirection getDirectionFromValue(int value) {
        switch(value) {
            case 0:
                return NORTH;
            case 1:
                return NORTHEAST;
            case 2:
                return EAST;
            case 3:
                return SOUTHEAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTHWEST;
            case 6:
                return WEST;
            case 7:
                return NORTHWEST;
            default: throw new IllegalArgumentException("Value is out of range 0-3");
        }
    }
}
