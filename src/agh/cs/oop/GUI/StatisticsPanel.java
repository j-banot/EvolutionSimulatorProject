package agh.cs.oop.GUI;

import agh.cs.oop.Constants.Constants;
import agh.cs.oop.Objects.WorldMap;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class StatisticsPanel extends JPanel{

    private GamePanel gamePanel;
    private WorldMap map;
    private Timer timer;
    private int yMargin;
    boolean isRunning;
    private JButton startBtn;
    private JButton stopBtn;
    private JButton nextDayBtn;
    private JButton saveStatsBtn;
    private JButton trackAnimalBtn;
    private StopActionListener stopActionListener;
    private StartActionListener startActionListener;
    private NextDayActionListener nextDayActionListener;
    private SaveStatsActionListener saveStatsActionListener;
    private TrackAnimalActionListener trackAnimalActionListener;
    private JLabel mapStatisticsTitle;
    private JLabel dayInfo;
    private JLabel numberOfAnimalsInfo;
    private JLabel numberOfPlantsInfo;
    private JLabel averageEnergyInfo;
    private JLabel averageAgeOfDeathsInfo;
    private JLabel averageNumberOfChildrenInfo;
    private JLabel trackedAnimalStatisticsTitle;
    private JLabel numberOfChildren;
    private JLabel numberOfOffspring;
    private JLabel deathDate;


    public StatisticsPanel(GamePanel gamePanel, WorldMap map, Timer timer, int yMargin) {
        initializeVariables(yMargin, timer, gamePanel, map);
        initializeLayout();
    }

    private void initializeVariables(int yMargin, Timer timer, GamePanel gamePanel, WorldMap map) {
        this.yMargin = yMargin;
        this.timer = timer;
        this.isRunning = false;
        this.gamePanel = gamePanel;
        this.map = map;

        stopActionListener = new StopActionListener();
        startActionListener = new StartActionListener();
        nextDayActionListener = new NextDayActionListener();
        saveStatsActionListener = new SaveStatsActionListener();
        trackAnimalActionListener = new TrackAnimalActionListener();

        stopBtn = new JButton("STOP");
        startBtn = new JButton("START");
        nextDayBtn = new JButton("NEXT DAY");
        saveStatsBtn = new JButton("SAVE STATS");
        trackAnimalBtn = new JButton("TRACK ANIMAL");

        mapStatisticsTitle = new JLabel("MAP STATS");
        mapStatisticsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        dayInfo = new JLabel("  Day: " + map.getDay());
        numberOfAnimalsInfo = new JLabel("  Number of dinos: " + map.getNumberOfAnimals());
        numberOfPlantsInfo = new JLabel("  Number of plants: " + map.getNumberOfPlants());
        //dominantGenotypesInfo = new JLabel("  Dominant genotype: " + map.getDominantGenotype());
        averageEnergyInfo = new JLabel("  Average animal energy: " + map.getAverageEnergy());
        averageAgeOfDeathsInfo = new JLabel("  Average age of deaths: " + map.getAverageDeathAge());
        averageNumberOfChildrenInfo = new JLabel("  Average number of children: " + map.getAverageNumberOfChildren());
        trackedAnimalStatisticsTitle = new JLabel("");
        numberOfChildren = new JLabel("");
        numberOfOffspring = new JLabel("");
        deathDate = new JLabel("");

        //TODO: change font
        Font titleFont = new Font("Courier", Font.BOLD,22);
        Font font = new Font("Courier", Font.BOLD,16);
        mapStatisticsTitle.setFont(titleFont);
        dayInfo.setFont(font);
        numberOfAnimalsInfo.setFont(font);
        numberOfPlantsInfo.setFont(font);
        averageEnergyInfo.setFont(font);
        averageAgeOfDeathsInfo.setFont(font);
        averageNumberOfChildrenInfo.setFont(font);
        trackedAnimalStatisticsTitle.setFont(titleFont);
        numberOfChildren.setFont(font);
        numberOfOffspring.setFont(font);
        deathDate.setFont(font);
    }

    private void initializeLayout() {
        setPreferredSize(new Dimension(50 + Constants.BOARD_WIDTH / 2, Constants.BOARD_HEIGHT - yMargin));
        setLayout(new GridLayout(0,1));

        JPanel mapStats = new JPanel();
        JPanel trackedAnimal = new JPanel();
        JPanel buttons = new JPanel();

        mapStats.setLayout(new GridLayout(0,1));
        trackedAnimal.setLayout(new GridLayout(0,1));
        GridLayout layout = new GridLayout(0,2);
        layout.setHgap(5);
        layout.setVgap(5);
        buttons.setLayout(layout);

        stopBtn.setEnabled(false);
        saveStatsBtn.setEnabled(false);

        mapStats.add(mapStatisticsTitle);
        mapStats.add(dayInfo);
        mapStats.add(numberOfAnimalsInfo);
        mapStats.add(numberOfPlantsInfo);
        mapStats.add(averageEnergyInfo);
        mapStats.add(averageAgeOfDeathsInfo);
        mapStats.add(averageNumberOfChildrenInfo);

        trackedAnimal.add(trackedAnimalStatisticsTitle);
        trackedAnimal.add(numberOfChildren);
        trackedAnimal.add(numberOfOffspring);
        trackedAnimal.add(deathDate);

        buttons.add(startBtn);
        buttons.add(stopBtn);
        buttons.add(nextDayBtn);
        buttons.add(saveStatsBtn);
        buttons.add(trackAnimalBtn);

        this.add(mapStats);
        this.add(trackedAnimal);
        this.add(buttons);

        stopBtn.addActionListener(stopActionListener);
        startBtn.addActionListener(startActionListener);
        nextDayBtn.addActionListener(nextDayActionListener);
        saveStatsBtn.addActionListener(saveStatsActionListener);
        trackAnimalBtn.addActionListener(trackAnimalActionListener);
    }

    private class StopActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isRunning) {
                timer.stop();
                isRunning = false;
                stopBtn.setEnabled(false);
                startBtn.setEnabled(true);
                nextDayBtn.setEnabled(true);
                saveStatsBtn.setEnabled(true);
                trackAnimalBtn.setEnabled(true);
            }
        }
    }

    private class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isRunning) {
                timer.start();
                isRunning = true;
                stopBtn.setEnabled(true);
                startBtn.setEnabled(false);
                nextDayBtn.setEnabled(false);
                saveStatsBtn.setEnabled(false);
                trackAnimalBtn.setEnabled(false);
            }
        }
    }

    private class NextDayActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isRunning) {
                gamePanel.doOneLoop();
                updateMapStats();
                saveStatsBtn.setEnabled(true);
            }
        }
    }

    private class SaveStatsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Gson gson = new Gson();
            StatsToSave statsToSave = new StatsToSave(Constants.NUMBER_OF_SQUARES_X, Constants.NUMBER_OF_SQUARES_Y,
                    Constants.START_ENERGY, Constants.NUMBER_OF_ANIMALS, Constants.MOVE_ENERGY, Constants.PLANT_ENERGY,
                    Constants.SIZE_OF_JUNGLE_SQUARE, map.getDay(), map.getNumberOfAnimals(), map.getNumberOfPlants(),
                    map.getAverageEnergy(), map.getAverageDeathAge(), map.getAverageNumberOfChildren());
            try {
                String jsonString = gson.toJson(statsToSave);
                String userHomeFolder = System.getProperty("user.home");
                userHomeFolder += "/Desktop/";
                FileWriter writer = new FileWriter(userHomeFolder + "EvolutionStats.txt");
                writer.write(jsonString);
                writer.close();
                showMessageDialog(null, "Stats saved on Desktop!");
            } catch (IOException e2) {
                System.out.println("exception " + e2.getMessage());
                e2.printStackTrace();
            }
        }
    }

    private class TrackAnimalActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isRunning && !gamePanel.isAnimalTracked()) {
                showMessageDialog(null, "Select animal to track!");
                gamePanel.toggleIsFindAnimalToTrackModeOn();
            }
            if (!gamePanel.isAnimalTracked()) {
                trackAnimalBtn.setText("STOP TRACKING");
            } else {
                map.removeOffspringOfTrackedAnimal();
                resetTrackedAnimalStats();
                gamePanel.setFindAnimalToTrackModeON(false);
                gamePanel.isFindAnimalToTrackModeOn();
                gamePanel.setTrackedAnimal(null);
                gamePanel.repaint();
                trackAnimalBtn.setText("TRACK ANIMAL");
            }
        }
    }

    private void resetTrackedAnimalStats() {
        trackedAnimalStatisticsTitle.setText("");
        numberOfChildren.setText("");
        numberOfOffspring.setText("");
        deathDate.setText("");
    }

    public void updateTrackedAnimalStats() {
        trackedAnimalStatisticsTitle.setText("TRACKED ANIMAL STATS");
        trackedAnimalStatisticsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        int numberNewChildren = gamePanel.getTrackedAnimal().getNumberOfChildren() - gamePanel.getNumberOfChildrenWhenTrackingStarted();
        numberOfChildren.setText("  Number of new children: " + numberNewChildren);
        numberOfOffspring.setText("  Number of new offspring: " + map.getNumberOfOffspringOfTrackedAnimal());
        if(!gamePanel.getTrackedAnimal().getIfDead()){
            deathDate.setText("");
        }
        else{
            deathDate.setText("  Animal died on " + gamePanel.getDayOfTrackedAnimalDeath() + " day");
        }
    }

    public void timerStop() {
    }

    public static class StatsToSave{
        // Start Values
        int numberOfX;
        int numberOfTilesY;
        int startAnimalEnergy;
        int numberOfStartAnimals;
        int moveEnergy;
        int plantEnergy;
        double jungleRatio;

        // Current Map Stats
        int day;
        int numberOfAnimals;
        int numberOfGrasses;
        double averageEnergy;
        double averageAgeOfDeaths;
        double averageNumberOfChildren;

        public StatsToSave(int numberOfX, int numberOfTilesY, int startAnimalEnergy, int numberOfStartAnimals,
                           int moveEnergy, int plantEnergy, double jungleRatio, int day, int numberOfAnimals,
                           int numberOfGrasses, double averageEnergy, double averageAgeOfDeaths,
                           double averageNumberOfChildren) {
            this.numberOfX = numberOfX;
            this.numberOfTilesY = numberOfTilesY;
            this.startAnimalEnergy = startAnimalEnergy;
            this.numberOfStartAnimals = numberOfStartAnimals;
            this.moveEnergy = moveEnergy;
            this.plantEnergy = plantEnergy;
            this.jungleRatio = jungleRatio;
            this.day = day;
            this.numberOfAnimals = numberOfAnimals;
            this.numberOfGrasses = numberOfGrasses;
            this.averageEnergy = averageEnergy;
            this.averageAgeOfDeaths = averageAgeOfDeaths;
            this.averageNumberOfChildren = averageNumberOfChildren;
        }
    }

    public void updateMapStats() {
        dayInfo.setText("  Day: " + map.getDay());
        numberOfAnimalsInfo.setText("  Number of dinos: " + map.getNumberOfAnimals());
        numberOfPlantsInfo.setText("  Number of plants: " + map.getNumberOfPlants());
        averageEnergyInfo.setText("  Average animal energy: " + map.getAverageEnergy());
        averageAgeOfDeathsInfo.setText("  Average age of deaths: " + map.getAverageDeathAge());
        averageNumberOfChildrenInfo.setText("  Average number of children: " + map.getAverageNumberOfChildren());
    }

    public void disableButtons(){
        startBtn.setEnabled(false);
        stopBtn.setEnabled(false);
        nextDayBtn.setEnabled(false);
        saveStatsBtn.setEnabled(false);
        trackAnimalBtn.setEnabled(false);
    }
}
