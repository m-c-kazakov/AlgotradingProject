package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
public class RemoveIndicatorOpeningDeal implements Mutation {

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {
        List<Indicator> indicators = new ArrayList<>(parentDescriptionOfStrategy.getIndicatorsDescriptionToOpenADeal());

        int numberOfDeletedItems = indicators.size() / 2;

        for (int i = 0; i < numberOfDeletedItems; i++) {

            if (indicators.size() / 2 >= 1) {
                int removeIndex = ThreadLocalRandom.current()
                        .nextInt(indicators.size());
                indicators.remove(removeIndex);
            }
        }


        DescriptionOfStrategy descriptionOfStrategyAfterMutation = parentDescriptionOfStrategy.withDescriptionToOpenADeal(
                indicators);

        return Stream.of(parentDescriptionOfStrategy, descriptionOfStrategyAfterMutation);
    }
}