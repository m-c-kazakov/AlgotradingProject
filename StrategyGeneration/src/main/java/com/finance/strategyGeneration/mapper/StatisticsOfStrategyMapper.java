package com.finance.strategyGeneration.mapper;

import com.finance.strategyGeneration.model.StatisticsOfStrategy;
import com.finance.utils.StatisticsDataOfStrategyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatisticsOfStrategyMapper {

    @Mapping(target = "id", ignore = true)
    StatisticsOfStrategy mapTo(StatisticsDataOfStrategyDto statisticsDataOfStrategyDto);
}
