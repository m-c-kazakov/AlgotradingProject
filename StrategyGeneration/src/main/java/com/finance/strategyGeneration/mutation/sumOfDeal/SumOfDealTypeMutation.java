package com.finance.strategyGeneration.mutation.sumOfDeal;

import com.finance.dataHolder.DataOfStrategy;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyDescriptionParameters.SumOfDealType;
import com.finance.strategyGeneration.mutation.Mutation;
import com.finance.strategyGeneration.random.RandomSumOfDeal;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class SumOfDealTypeMutation implements Mutation {

    @Override
    public Stream<DataOfStrategy> execute(DataOfStrategy parentDataOfStrategy) {
        SumOfDealType randomSumOfDealType = SumOfDealType.getRandomSumOfDealType();
        Map<SumOfDealConfigurationKey, Object> randomParamsForSumOfDeal = RandomSumOfDeal.getMapWithSupplierGeneratedRandomParams()
                .get(randomSumOfDealType)
                .get();

        DataOfStrategy childDataOfStrategy = parentDataOfStrategy
                .withSumOfDealType(randomSumOfDealType)
                .withSumOfDealConfigurationData(randomParamsForSumOfDeal);

        return Stream.of(parentDataOfStrategy, childDataOfStrategy);
    }
}