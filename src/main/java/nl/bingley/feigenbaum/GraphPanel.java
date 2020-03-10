package nl.bingley.feigenbaum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private static final long serialVersionUID = -5900446752418239438L;

    private final int initialGeneration;
    private final int fertilityDecimals;

    private List<PopulationHistory> allPopulations = new ArrayList<>();

    private boolean isPainting = false;

    public GraphPanel(int initialGeneration, int fertilityDecimals) {
        super();
        this.initialGeneration = initialGeneration;
        this.fertilityDecimals = fertilityDecimals;
    }

    public void add(PopulationHistory populationHistory) {
        allPopulations.add(populationHistory);
    }

    public boolean isPainting() {
        return isPainting;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        isPainting = true;
        Rectangle bounds = graphics.getClipBounds();
        graphics.clearRect(0, 0, bounds.width, bounds.height);
        for (int i = allPopulations.size() - 1; i >= 0; i--) {
            int x = bounds.width - allPopulations.size() + i;
            if (x >= 0) {
                drawGridLine(graphics, bounds, i, x);
                drawPopulations(graphics, bounds, allPopulations.get(i), x);
                drawCurrentNumber(graphics);
            } else {
                break;
            }
        }
        graphics.dispose();
        isPainting = false;
    }

    private void drawPopulations(Graphics graphics, Rectangle bounds, PopulationHistory populations, int x) {
        for (Double population : populations) {
            int y = (int) Math.round(bounds.height - population * bounds.height);
            graphics.drawLine(x, y, x, y);
        }
    }

    private void drawGridLine(Graphics graphics, Rectangle bounds, int index, int x) {
        if ((initialGeneration + index) % (fertilityDecimals / 10) == 0) {
            graphics.setColor(Color.lightGray);
            graphics.drawLine(x, 0, x, bounds.height);
            graphics.setColor(Color.BLACK);
        }
    }

    private void drawCurrentNumber(Graphics graphics) {
        double number = (initialGeneration + allPopulations.size()) / (double) fertilityDecimals;
        graphics.setColor(Color.RED);
        graphics.drawString(String.valueOf(number), 10, 20);
        graphics.setColor(Color.BLACK);
    }
}
