package com.finance.utils.controller;

import com.finance.strategyDescriptionParameters.*;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyDescriptionParameters.indicators.SmaParameters;
import com.finance.utils.dto.RequestDataOfStrategy;
import com.finance.utils.intagration.IntegrationTestBased;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class IndicatorDataControllerImplTest extends IntegrationTestBased {

    ObjectMapper objectMapper;
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void generateDataOfIndicators() {

        String json =
                "{\"typeOfDeal\":\"BUY\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\",\"per\":1},\"descriptionToOpenADeal\":[{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\",\"per\":1},\"parameters\":{\"PERIOD\":\"36\",\"CALCULATE_BY\":\"LOW\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\"},{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\",\"per\":60},\"parameters\":{\"PERIOD\":\"61\",\"CALCULATE_BY\":\"HIGH\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\"},{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\",\"per\":60},\"parameters\":{\"PERIOD\":\"65\",\"CALCULATE_BY\":\"LOW\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\"},{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\",\"per\":1},\"parameters\":{\"PERIOD\":\"80\",\"CALCULATE_BY\":\"CLOSE\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\"}],\"descriptionToCloseADeal\":[],\"indicatorsOfDescriptionToOpenADeal\":[{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\",\"per\":1},\"parameters\":{\"PERIOD\":\"36\",\"CALCULATE_BY\":\"LOW\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\"},{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\",\"per\":60},\"parameters\":{\"PERIOD\":\"61\",\"CALCULATE_BY\":\"HIGH\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\"},{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\",\"per\":60},\"parameters\":{\"PERIOD\":\"65\",\"CALCULATE_BY\":\"LOW\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"H1\"},{\"indicatorType\":\"SMA\",\"candlesInformation\":{\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\",\"per\":1},\"parameters\":{\"PERIOD\":\"80\",\"CALCULATE_BY\":\"CLOSE\"},\"currencyPair\":\"EURUSD\",\"timeFrame\":\"M1\"}],\"theSmallestTimeFrame\":\"M1\",\"indicatorsOfDescriptionToCloseADeal\":[]}";

        RequestDataOfStrategy requestDataOfStrategy = RequestDataOfStrategy.builder()
                .typeOfDeal(TypeOfDeal.BUY)
                .candlesInformation(
                        CandlesInformation.builder().currencyPair(CurrencyPair.EURUSD).timeFrame(TimeFrame.M1).build())
                // TODO валидацию полей
                .descriptionToOpenADeal(List.of(
                        Indicator.builder()
                                .indicatorType(IndicatorType.SMA)
                                .candlesInformation(CandlesInformation
                                        .builder()
                                        .timeFrame(TimeFrame.M1)
                                        .currencyPair(CurrencyPair.EURUSD)
                                        .build())
                                .parameters(Map.of(SmaParameters.CALCULATE_BY.name(), TypeOfBar.CLOSE.name(),
                                        SmaParameters.PERIOD.name(), "2"))
                                .build()))
                .descriptionToCloseADeal(List.of())
                .build();

        // TODO дописать тест на анализ результата
        mockMvc.perform(
                        post("/generateDataOfIndicators")
                                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                                .content(json))
                .andExpectAll(
                        status().is2xxSuccessful()
                ).andReturn();
    }
}
