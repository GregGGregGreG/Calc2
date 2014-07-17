package com.greg.calculator.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class CalculatorGui implements Runnable {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new CalculatorGui());
    }

    @Override
    public void run() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}