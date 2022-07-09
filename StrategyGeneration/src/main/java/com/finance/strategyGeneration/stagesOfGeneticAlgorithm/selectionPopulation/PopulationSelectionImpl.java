package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation;

import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationSelectionImpl implements PopulationSelection {

    СheckingTheUniquenessOfStrategies сheckingTheUniquenessOfStrategies;


    @Override
    public Set<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterMutation) {

        return сheckingTheUniquenessOfStrategies.execute(populationAfterMutation);
    }
}