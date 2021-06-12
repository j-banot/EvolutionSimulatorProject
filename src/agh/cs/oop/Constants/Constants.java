package agh.cs.oop.Constants;

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
}
