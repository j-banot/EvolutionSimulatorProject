package agh.cs.oop;

/**
 * Simple class containing main method where game is being executed
 */

import agh.cs.oop.Constants.Constants;
import agh.cs.oop.GUI.GameFrame;

import java.io.IOException;

public class GameSimulation {

    public static void main(String[] args) throws IOException{
        Constants.getValueFromJSON();
        for(int i = 0; i < Constants.NUMBER_OF_MAPS_TO_GENERATE; i++) {
            try {
                new GameFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
