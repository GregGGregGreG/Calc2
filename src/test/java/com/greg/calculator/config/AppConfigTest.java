package com.greg.calculator.config;

import com.greg.calculator.view.desktop.view.CalculatorView;
import org.springframework.context.annotation.Configuration;

/**
 * Created by GREG on 28.06.2014.
 */
@Configuration
public class AppConfigTest extends AppConfig {
    @Override
    public CalculatorView calculatorView() {
        return null;
    }


}
