package nl.bingley.feigenbaum;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    private static final int SAMPLE_SIZE = 3000;

    public PopulationHistory findStablePopulations(double fertility) {
        PopulationHistory allPopulations = new PopulationHistory();
        double birthRate = 0.5;
        while (allPopulations.size() < SAMPLE_SIZE) {
            birthRate = calculatePopulation(fertility, birthRate);
            allPopulations.add(birthRate);
        }
        return getStablePopulations(allPopulations);
    }

    private PopulationHistory getStablePopulations(List<Double> populationHistory) {
        Integer maxFrequency = populationHistory.stream()
                .map(population -> Collections.frequency(populationHistory, population))
                .max(Integer::compareTo).orElse(0);

        List<Double> stablePopulations = populationHistory.stream()
                .filter(population -> Collections.frequency(populationHistory, population) >= maxFrequency - 1)
                .distinct()
                .collect(Collectors.toList());
        if (stablePopulations.size() == SAMPLE_SIZE) {
            return new PopulationHistory(populationHistory.subList(populationHistory.size() - 100, populationHistory.size() - 1));
        } else {
            return new PopulationHistory(stablePopulations);
        }
    }

    private double calculatePopulation(double fertility, double birthRate) {
        return (fertility * birthRate * Math.abs(1 - birthRate)) % 1;
//        double mod = 1 - birthRate;
//        if (mod <= 0) {
//            mod = 0.000000000000000000000000000000000000000000000001;
//        }
//        double population = fertility * birthRate * mod;
//        if (population >= 1) {
//            return 0.999999999999999999999999999999999999999999999999;
//        } else {
//            return population;
//        }
    }
}
