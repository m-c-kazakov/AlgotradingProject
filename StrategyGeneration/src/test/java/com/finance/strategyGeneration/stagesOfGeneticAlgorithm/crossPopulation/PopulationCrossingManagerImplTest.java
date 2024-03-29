package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.intagration.KafkaTestBased;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.repository.SpecificationOfStrategyRepository;
import com.finance.strategyGeneration.service.SchedulingService;
import com.finance.strategyGeneration.service.broker.consumer.DataConsumer;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.PopulationCreationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


class PopulationCrossingManagerImplTest extends KafkaTestBased {

    @MockBean
    DataConsumer dataConsumer;
    @MockBean
    SchedulingService schedulingService;

    @Autowired
    PopulationCrossingManagerImpl populationCrossingManager;
    @Autowired
    PopulationCreationManager populationCreationManager;
    @Autowired
    SpecificationOfStrategyRepository repository;

    @Value("${app.kafka.consumer.max_poll_records_config}")
    Integer max_poll_records_config;
    @Value("${app.populationCreation.numberOfRandomIndividual}")
    Integer numberOfRandomIndividual;
    @Value("${app.populationCreation.numberOfTheBestIndividual}")
    Integer numberOfTheBestIndividual;


    @Test
    void execute() {

        doReturn(max_poll_records_config * 5).when(dataConsumer).poll();

        List<SpecificationOfStrategy> specificationOfStrategies = populationCreationManager.execute();

        populationCrossingManager.execute(specificationOfStrategies);

        Optional<Integer> result = repository.countAll();
        assertThat(result).isPresent();
        result.ifPresent(integer -> {
            assertThat(integer).isGreaterThan(numberOfRandomIndividual + numberOfTheBestIndividual);
        });
    }
}