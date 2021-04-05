package agh.cs.oop;

//TO DO: descriptions and comments in the way shown below
// /**
// * Description
// */

import java.util.Objects;

public class Vector2d {

    final int x;
    final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    // checks if both vector components of a vector are lower than or equal than given one
    public boolean precedes(Vector2d other) {
        if (this.x <= other.x && this.y <= other.y) return true;
        else {return false;}
    }

    //checks if both vector components of a vector are greater than or equal than given
    public boolean follows(Vector2d other) {
        if (this.x >= other.x && this.y >= other.y) return true;
        else {return false;}
    }

    //returns a upper right corner of a rectangle built on both vectors
    public Vector2d upperRight(Vector2d other) {
        int tmpX, tmpY;
        if (this.x >= other.x) tmpX = this.x;
        else { tmpX = other.x;}
        if (this.y >= other.y) tmpY = this.y;
        else { tmpY = other.y;}
        return new Vector2d(tmpX, tmpY);
    }

    //returns a lower left corner of a rectangle built on both vectors
    public Vector2d lowerLeft(Vector2d other) {
        int tmpX, tmpY;
        if (this.x <= other.x) tmpX = this.x;
        else { tmpX = other.x;}
        if (this.y <= other.y) tmpY = this.y;
        else { tmpY = other.y;}
        return new Vector2d(tmpX, tmpY);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d opposite() {
        return new Vector2d(-(this.x), -(this.y));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if (this.x == that.x && this.y == that.y) return true;
        else { return false;}
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
