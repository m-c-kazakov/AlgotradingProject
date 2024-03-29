package com.finance.strategyDescriptionParameters.indicators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SmaParameters {

    CALCULATE_BY("По какому значению рассчитывать индикатор? Цена закрытия, открытия, высшая, нижняя - TypeOfBar"),
    PERIOD("Количество свечей которое будет учитываться для расчета значения");

    String description;

    public static List<String> getParameters() {
        return Arrays.stream(SmaParameters.values())
                .map(Enum::name)
                .toList();
    }

}
