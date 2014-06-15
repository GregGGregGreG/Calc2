package calculator;

import calculator.logic.ExceptionParserPolishNotation;
import calculator.logic.ParserPolishNotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorRunConsole {

    public static void main(String[] args) throws ExceptionParserPolishNotation {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            System.out.println("Enter expression!");
            while ((input = br.readLine()) != null) {
                System.out.println("Result: " + input + " = " + ParserPolishNotation.parser(input) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
