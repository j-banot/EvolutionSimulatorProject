package agh.cs.oop.Tools;

import agh.cs.oop.Constants.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicTools {
    //TODO: docummentation, what is buffered image
    BufferedImage WHITE_DINO_ON_GRASS;
    BufferedImage BLUE_DINO_ON_GRASS;
    BufferedImage RED_DINO_ON_GRASS;
    BufferedImage PINK_DINO_ON_GRASS;
    BufferedImage WHITE_DINO_ON_SAND;
    BufferedImage BLUE_DINO_ON_SAND;
    BufferedImage RED_DINO_ON_SAND;
    BufferedImage PINK_DINO_ON_SAND;
    BufferedImage GRASS_SQUARE;
    BufferedImage SAND_SQUARE;
    BufferedImage PLANT_ON_GRASS;
    BufferedImage PLANT_ON_SAND;

    public GraphicTools() throws IOException {
        WHITE_DINO_ON_GRASS = ImageIO.read(new File(Constants.WHITE_DINO_ON_GRASS_PATH));
        BLUE_DINO_ON_GRASS = ImageIO.read(new File(Constants.BLUE_DINO_ON_GRASS_PATH));
        RED_DINO_ON_GRASS = ImageIO.read(new File(Constants.RED_DINO_ON_GRASS_PATH));
        PINK_DINO_ON_GRASS = ImageIO.read(new File(Constants.PINK_DINO_ON_GRASS_PATH));
        WHITE_DINO_ON_SAND = ImageIO.read(new File(Constants.WHITE_DINO_ON_SAND_PATH));
        BLUE_DINO_ON_SAND = ImageIO.read(new File(Constants.BLUE_DINO_ON_SAND_PATH));
        RED_DINO_ON_SAND = ImageIO.read(new File(Constants.RED_DINO_ON_SAND_PATH));
        PINK_DINO_ON_SAND = ImageIO.read(new File(Constants.PINK_DINO_ON_SAND_PATH));
        GRASS_SQUARE = ImageIO.read(new File(Constants.GRASS_SQUARE_PATH));
        SAND_SQUARE = ImageIO.read(new File(Constants.SAND_SQUARE_PATH));
        PLANT_ON_GRASS = ImageIO.read(new File(Constants.PLANT_ON_GRASS_PATH));
        PLANT_ON_SAND = ImageIO.read(new File(Constants.PLANT_ON_SAND_PATH));
    }

    public BufferedImage getWHITE_DINO_ON_GRASS() { return WHITE_DINO_ON_GRASS; }

    public BufferedImage getBLUE_DINO_ON_GRASS() { return BLUE_DINO_ON_GRASS; }

    public BufferedImage getRED_DINO_ON_GRASS() { return RED_DINO_ON_GRASS; }

    public BufferedImage getPINK_DINO_ON_GRASS() { return PINK_DINO_ON_GRASS; }

    public BufferedImage getWHITE_DINO_ON_SAND() { return WHITE_DINO_ON_SAND; }

    public BufferedImage getBLUE_DINO_ON_SAND() { return BLUE_DINO_ON_SAND; }

    public BufferedImage getRED_DINO_ON_SAND() { return RED_DINO_ON_SAND; }

    public BufferedImage getPINK_DINO_ON_SAND() { return PINK_DINO_ON_SAND; }

    public BufferedImage getGRASS_SQUARE() { return GRASS_SQUARE; }

    public BufferedImage getSAND_SQUARE() { return SAND_SQUARE; }

    public BufferedImage getPLANT_ON_GRASS() { return PLANT_ON_GRASS; }

    public BufferedImage getPLANT_ON_SAND() { return PLANT_ON_SAND; }

}
