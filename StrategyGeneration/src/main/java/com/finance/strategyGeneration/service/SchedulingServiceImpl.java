package com.finance.strategyGeneration.service;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.GeneticAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SchedulingServiceImpl implements SchedulingService {

    GeneticAlgorithm geneticAlgorithm;

    @Override
    @Scheduled(fixedDelay = 30000)
    public void execute() {
        log.info("START: Запуск генетического алгоритма.");
        List<Long> specificationOfStrategyIds = geneticAlgorithm
                .execute()
                .stream()
                .map(SpecificationOfStrategy::getId)
                .toList();
        log.info("END: Завершение генетического алгоритма. Количество созданных стратегий={}", specificationOfStrategyIds.size());


    }
}