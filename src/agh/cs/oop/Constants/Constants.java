package agh.cs.oop.Constants;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Constants {
    public static final String TITLE = "Evolution Simulator";
    public static final int BOARD_WIDTH = 1000;
    public static final int BOARD_HEIGHT = 1000;

    //Values coming from JSON input file
    public static int NUMBER_OF_SQUARES_X;
    public static int NUMBER_OF_SQUARES_Y;
    public static int NUMBER_OF_MAPS_TO_GENERATE;
    public static int GAME_SPEED; //constant defining map time refresh frequency
    public static int START_ENERGY;
    public static int NUMBER_OF_ANIMALS;
    public static int MOVE_ENERGY;
    public static int PLANT_ENERGY;
    public static int SIZE_OF_JUNGLE_SQUARE;

    //Dinosaur level based on it's energy, Level 1 - lowest, Level 4 - highest
    public static int LEVEL1; //White Dino
    public static int LEVEL2; //Blue Dino
    public static int LEVEL3; //Red Dino
    public static int LEVEL4; //Pink Dino

    static final String dinosPath = "src/agh/cs/oop/Graphics/Dinos/";
    static final String plantsPath = "src/agh/cs/oop/Graphics/Plants/";
    static final String terrainPath = "src/agh/cs/oop/Graphics/Terrain/";

    public static final String GRASS_SQUARE_PATH = terrainPath + "grass.png";
    public static final String SAND_SQUARE_PATH = terrainPath + "sand.png";
    public static final String PLANT_ON_GRASS_PATH = plantsPath + "PlantOnGrass.png";
    public static final String PLANT_ON_SAND_PATH = plantsPath + "PlantOnSand.png";
    public static final String WHITE_DINO_ON_GRASS_PATH = dinosPath + "WhiteDinoOnGrass.png";
    public static final String WHITE_DINO_ON_SAND_PATH = dinosPath + "WhiteDinoOnSand.png";
    public static final String BLUE_DINO_ON_GRASS_PATH = dinosPath + "BlueDinoOnGrass.png";
    public static final String BLUE_DINO_ON_SAND_PATH = dinosPath + "BlueDinoOnSand.png";
    public static final String RED_DINO_ON_GRASS_PATH = dinosPath + "RedDinoOnGrass.png";
    public static final String RED_DINO_ON_SAND_PATH = dinosPath + "RedDinoOnSand.png";
    public static final String PINK_DINO_ON_GRASS_PATH = dinosPath + "PinkDinoOnGrass.png";
    public static final String PINK_DINO_ON_SAND_PATH = dinosPath + "PinkDinoOnSand.png";

    public static void getValueFromJSON() throws FileNotFoundException {
        FileReader reader = new FileReader("src/agh/cs/oop/inputValues.json");
        JsonParser jsonParser = new JsonParser();
        Gson gson = new Gson();
        InputValues startValue = gson.fromJson(jsonParser.parse(reader), InputValues.class);
        initializeVariables(startValue);
    }

    public static void initializeVariables(InputValues inputValues) {
        NUMBER_OF_SQUARES_X = inputValues.numberOfSquaresX;
        NUMBER_OF_SQUARES_Y = inputValues.numberOfSquaresY;
        NUMBER_OF_MAPS_TO_GENERATE = inputValues.numberOfMaps;
        GAME_SPEED = inputValues.gameSpeed;
        START_ENERGY = inputValues.startEnergy;
        NUMBER_OF_ANIMALS = inputValues.numberOfAnimals;
        MOVE_ENERGY = inputValues.moveEnergy;
        PLANT_ENERGY = inputValues.plantEnergy;
        SIZE_OF_JUNGLE_SQUARE = inputValues.sizeOfJungleSquare;
        LEVEL1 = START_ENERGY / 2;
        LEVEL2 = START_ENERGY;
        LEVEL3 = (int) (1.5 * START_ENERGY);
        LEVEL4 = 2 * START_ENERGY;
    }

    private class InputValues {
        int numberOfSquaresX;
        int numberOfSquaresY;
        int numberOfMaps;
        int startEnergy;
        int numberOfAnimals;
        int moveEnergy;
        int sizeOfJungleSquare;
        int plantEnergy;
        int gameSpeed;
    }
}
