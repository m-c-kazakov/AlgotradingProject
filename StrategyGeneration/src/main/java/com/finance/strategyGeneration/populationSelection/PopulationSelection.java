package com.finance.strategyGeneration.populationSelection;

import com.finance.dataHolder.DescriptionOfStrategy;

import java.util.List;
import java.util.Set;

public interface PopulationSelection {
    Set<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation);
}
