package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class SymOfDealTypeExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(Set<SpecificationOfStrategy> dataOfStrategies) {
        List<SpecificationOfStrategy> SpecificationOfStrategyElements = dataOfStrategies.stream()
                .toList();

        SpecificationOfStrategy firstParent = SpecificationOfStrategyElements.get(0);
        SpecificationOfStrategy secondParent = SpecificationOfStrategyElements.get(1);

        SpecificationOfStrategy firstChild = firstParent
                .withSumOfDealType(secondParent.getSumOfDealType())
                .withSumOfDealConfigurationData(secondParent.getSumOfDealConfigurationData());

        SpecificationOfStrategy secondChild = secondParent
                .withSumOfDealType(firstParent.getSumOfDealType())
                .withSumOfDealConfigurationData(firstParent.getSumOfDealConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
