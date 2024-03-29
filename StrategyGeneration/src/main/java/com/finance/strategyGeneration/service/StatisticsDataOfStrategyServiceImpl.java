package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.mapper.StatisticsOfStrategyMapper;
import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.repository.StatisticsOfStrategyRepository;
import com.finance.utils.StatisticsDataOfStrategyDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsDataOfStrategyServiceImpl implements StatisticsDataOfStrategyService {

    StatisticsOfStrategyMapper mapper;
    StatisticsOfStrategyRepository statisticsOfStrategyRepository;
    SpecificationOfStrategyRepository specificationOfStrategyRepository;

    @Override
    public void save(StatisticsDataOfStrategyDto statisticsDataOfStrategyDto) {

        StatisticsOfStrategy statisticsOfStrategy = mapper.mapTo(statisticsDataOfStrategyDto);

        StatisticsOfStrategy statisticsOfStrategyWithId = statisticsOfStrategyRepository.save(statisticsOfStrategy);

        specificationOfStrategyRepository.updateStatisticsOfStrategyId(
                statisticsOfStrategyWithId.getSpecificationOfStrategyId(), statisticsOfStrategyWithId.getId());
    }
}
