package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.Operation;

import java.util.LinkedList;

//This class has side effects, so run first!!!
public class CalculatorTextGenerator {

    public String execute(Operation operation, String actionCommand, String currentText, LinkedList<String> commands) {
        if (operation == Operation.CALCULATE || operation == Operation.CLEAR) {
            return "";
        }  else if (operation == Operation.DEC) {
            if (commands.isEmpty()) {
                return "0.";
            } else {
                return commands.removeLast() + ".";
            }
        } else {
            return currentText + actionCommand;
        }
    }
}
