package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DataOfStrategy;

import java.util.List;

public interface PopulationService {
    List<DataOfStrategy> findTheBestIndividual(int numberOfIndividuals);
}