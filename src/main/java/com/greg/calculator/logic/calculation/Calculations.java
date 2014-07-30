package com.greg.calculator.logic.calculation;

import com.greg.calculator.logic.Operator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

@Component(value = "calculations")
public class Calculations implements ICalculations {

    private static final MathContext mc = new MathContext(12);
    private static final DecimalFormat format = new DecimalFormat("#0.##########");

    @Override
    public String typeDigit(BigDecimal bdNumber) {
        if (bdNumber.toString().length() == 13) return format.format(bdNumber).replaceAll(",", ".");
        return bdNumber.toString().replaceAll("\\.0$", "");
    }

    @Override
    public BigDecimal calculation(Operator operator, BigDecimal second, BigDecimal first) {
        BigDecimal result = null;
        switch (operator) {
            case PLUS:
                result = first.add(second, mc);
                break;
            case MINUS:
                result = first.subtract(second, mc);
                break;
            case MULTIPLY:
                result = first.multiply(second, mc);
                break;
            case DIVISION:
                result = first.divide(second, mc);
                break;
            case POW:
                result = new BigDecimal(String.valueOf((Math.pow(first.doubleValue(), second.doubleValue()))), mc);
                break;
        }
        return result;
    }

    @Override
    public BigDecimal calculation(Operator operator, BigDecimal digit) {
        Double dDigit = digit.doubleValue();
        BigDecimal result = null;
        switch (operator) {
            case SQRT:
                result = new BigDecimal(String.valueOf(Math.sqrt(dDigit)), mc);
                break;
            case COS:
                result = new BigDecimal(String.valueOf(Math.cos(dDigit)), mc);
                break;
        }
        return result;
    }
}
