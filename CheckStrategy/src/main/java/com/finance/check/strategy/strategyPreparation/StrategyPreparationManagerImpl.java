package com.finance.check.strategy.strategyPreparation;

import com.finance.check.strategy.service.StrategyExecutorImpl;
import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.dataHolder.StatisticsDataOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StrategyPreparationManagerImpl implements StrategyPreparationManager {

    StrategyExecutorConfiguration strategyExecutorConfiguration;
    DataOfStrategyGeneratorService dataOfStrategyGeneratorService;

    @Override
    public StrategyExecutorImpl prepare(DescriptionOfStrategy descriptionOfStrategy) {

        DescriptionOfStrategy descriptionOfStrategyWithCandleAndIndicatorData =
                dataOfStrategyGeneratorService.generateDataOfIndicators(descriptionOfStrategy);

        StatisticsDataOfStrategy statisticsDataOfStrategy = StatisticsDataOfStrategy.builder()
                .specificationOfStrategyId(descriptionOfStrategyWithCandleAndIndicatorData.getId())
                .score(descriptionOfStrategyWithCandleAndIndicatorData.getStartScore())
                .valueOfAcceptableRisk(generateValueOfAcceptableRisk(descriptionOfStrategyWithCandleAndIndicatorData))
                .maximumPercentDrawdownFromStartScore(0L)
                .averagePercentDrawdownOfScore(0L)
                .maximumValueFromScore(0L)
                .numberOfWinningDeals(0)
                .numberOfLosingDeals(0)
                .build();

        return strategyExecutorConfiguration.configurate(descriptionOfStrategyWithCandleAndIndicatorData,
                statisticsDataOfStrategy);
    }

    private int generateValueOfAcceptableRisk(DescriptionOfStrategy descriptionOfStrategy) {
        // TODO Возможно уровень приемлемого риска стоит делать выше, чтобы находить стратегии,
        //  которые правильно открывают сделки, но не вовремя их закрывают
        return (int) (descriptionOfStrategy.getStartScore() * (100 - descriptionOfStrategy.getAcceptableRisk()) / 100);
    }

}
