package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.creator.InformationOfIndicatorCreator;
import com.finance.strategyGeneration.service.InformationOfCandleService;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.generatorRandomParametersOfIndicators.GeneratorRandomParametersOfIndicator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneratorOfRandomIndicatorsImpl implements GeneratorOfRandomIndicators {

    GeneratorRandomParametersOfIndicator randomParametersByIndicatorType;
    InformationOfCandleService informationOfCandleService;
    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public InformationOfIndicator createRandomIndicator(CurrencyPair currencyPair) {

        IndicatorType indicatorType = IndicatorType.getRandomIndicatorType();

        TimeFrame timeFrame = TimeFrame.getRandomTimeFrame();

        Map<String, String> parameters = randomParametersByIndicatorType.getRandomParametersByIndicatorType(
                indicatorType);

        InformationOfCandles informationOfCandles = informationOfCandleService.create(timeFrame, currencyPair);


        InformationOfIndicator informationOfIndicator = informationOfIndicatorService.create(
                InformationOfIndicatorCreator.create(indicatorType, informationOfCandles, parameters));

        if (Objects.isNull(
                informationOfIndicator.getInformationOfCandles().getInformationOfCandles().getCurrencyPair())) {
            System.out.println("asdf");
        }
        return informationOfIndicator;
    }
}
