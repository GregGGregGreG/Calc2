package com.greg.calculator.logic.parser;

import com.greg.calculator.logic.ExceptionParserPolishNotation;

public interface ParserExpression {
    String parser(String str) throws ExceptionParserPolishNotation;
}
