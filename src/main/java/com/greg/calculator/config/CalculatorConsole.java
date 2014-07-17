package com.greg.calculator.config;

import com.greg.calculator.logic.ExceptionParserPolishNotation;
import com.greg.calculator.logic.parser.ParserExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CalculatorConsole {
    @Inject
    private ParserExpression parserExpression;

    private String input;
    private String exit;

    public static void main(String[] args) throws ExceptionParserPolishNotation {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigConsole.class);
        CalculatorConsole console = context.getBean(CalculatorConsole.class);
        console.run();
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter pars!");
            while ((input = br.readLine()) != null) {
                try {
                    exit = "exit";
                    if (input.equals(exit)) return;
                    System.out.println("Result: " + input + " = " + parserExpression.parser(input) + "\n");
                } catch (NullPointerException e) {
                    System.out.println("Invalid expression in console");
                } catch (ExceptionParserPolishNotation e) {
                    System.out.println("Token not supported!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
