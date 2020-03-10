package nl.bingley.feigenbaum;

import javax.swing.*;
import java.awt.*;

public class Runner {

    private static final int START_GENERATION = 1000;
    private static final int END_GENERATION = 4000;
    private static final int FERTILITY_DECIMALS = 1000;

    public static void main(String[] args) {
//        Runner runner = new Runner();
//        runner.runOne(2.8864); // 1
//        runner.runOne(3.14); // 2
//        runner.runOne(2.719); // 1
//        runner.runOne(3.602); // 88
//        runner.runOne(3.606); // 20

        GraphPanel graphPanel = initializeWindow();
        runAll(graphPanel);
    }

    private void runOne(double fertility) {
        Calculator calculator = new Calculator();
        PopulationHistory stablePopulations = calculator.findStablePopulations(fertility);
        System.out.println("At " + fertility + " Stable populations: " + stablePopulations.size() + " with: " + stablePopulations);
    }

    private static void runAll(GraphPanel graphPanel) {
        Calculator calculator = new Calculator();
        int previousStable = 0;
        for (int i = START_GENERATION; i < END_GENERATION; i++) {
            PopulationHistory stablePopulations = calculator.findStablePopulations(i / (double) FERTILITY_DECIMALS);
            graphPanel.add(stablePopulations);
            if (!graphPanel.isPainting()) {
                graphPanel.repaint();
            }
            if (stablePopulations.size() != previousStable && stablePopulations.size() != 99) {
                previousStable = stablePopulations.size();
                System.out.println("At " + i / (double) FERTILITY_DECIMALS + " Stable populations: " + previousStable);
            }
        }
    }

    private static GraphPanel initializeWindow() {
        JFrame frame = new JFrame();
        GraphPanel graphPanel = new GraphPanel(START_GENERATION, FERTILITY_DECIMALS);
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        graphPanel.setFont(font);
        frame.getContentPane().add(graphPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setVisible(true);
        return graphPanel;
    }
}