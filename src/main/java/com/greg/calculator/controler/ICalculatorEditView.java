package com.greg.calculator.controler;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public interface ICalculatorEditView {

    AbstractAction bindingButtonsEvent(Character param);

    ActionListener buttonsEvent();

    ActionListener getResult();

    ActionListener getAnsResult();

    ActionListener resetCalc();

    ActionListener backspaceInputField();

    KeyAdapter keyBlock();

}