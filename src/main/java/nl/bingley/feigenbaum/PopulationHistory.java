package nl.bingley.feigenbaum;

import java.util.ArrayList;
import java.util.List;

public class PopulationHistory extends ArrayList<Double> {
    private static final long serialVersionUID = 4783334766266288170L;


    public PopulationHistory() {
        super();
    }

    public PopulationHistory(List<Double> populations) {
        super(populations);
    }
}
