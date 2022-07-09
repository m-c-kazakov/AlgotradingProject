package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.service.CandlesInformationService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionOfDealRandomGenerator implements RandomStrategyParams {

    GeneratorOfRandomIndicators generatorOfRandomIndicators;
    CandlesInformationService candlesInformationService;

    // TODO вынести в property
    Boolean isNeedToCreateDescriptionToCloseADeal = false;

    @Override
    public void add(DescriptionOfStrategy.DescriptionOfStrategyBuilder dataOfStrategyBuilder) {

        CurrencyPair currencyPair = CurrencyPair.getRandomCurrencyPair();


        List<Indicator> descriptionToOpenADeal = generateIndicators(currencyPair);
        List<Indicator> descriptionToCloseADeal = isNeedToCreateDescriptionToCloseADeal ? generateIndicators(currencyPair) : List.of();
        dataOfStrategyBuilder
                .descriptionToOpenADeal(descriptionToOpenADeal)
                .descriptionToCloseADeal(descriptionToCloseADeal);

        TimeFrame timeFrame = findMinimalTimeFrame(descriptionToOpenADeal, descriptionToCloseADeal);

        dataOfStrategyBuilder.candlesInformation(candlesInformationService.save(timeFrame, currencyPair));

    }

    private TimeFrame findMinimalTimeFrame(List<Indicator> descriptionToOpenADeal,
                                           List<Indicator> descriptionToCloseADeal) {
        List<TimeFrame> timeFrames = Stream.of(descriptionToOpenADeal, descriptionToCloseADeal)
                .filter(indicators -> !isEmpty(indicators))
                .flatMap(List::stream)
                .map(Indicator::getTimeFrame)
                .toList();

        return TimeFrame.getMinimalTimeFrame(timeFrames);
    }

    private List<Indicator> generateIndicators(CurrencyPair currencyPair) {
        int numberOfIndicators = ThreadLocalRandom.current()
                .nextInt(1, 6);
        return Stream.iterate(0, integer -> integer < numberOfIndicators, integer -> integer + 1)
                .map(integer -> generatorOfRandomIndicators.getRandomIndicator(currencyPair))
                .toList();
    }
}