package com.greg.calculator.logic.calculation;

import com.greg.calculator.logic.Operator;

import java.math.BigDecimal;

public interface Calculations {

    BigDecimal calculation(Operator operator, BigDecimal second, BigDecimal first);

    BigDecimal calculation(Operator operator, BigDecimal digit);

}
