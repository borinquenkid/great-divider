package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.Operation;

import java.util.LinkedList;

public class CommandManager {

    public void execute(Operation operation, String actionCommand, String text, LinkedList<String> commands) {

        if (operation == Operation.CALCULATE || operation == Operation.CLEAR) {
            commands.clear();
        } else if (operation == Operation.DEC) {
            commands.add(text);
        } else {
            commands.add(actionCommand);
        }
    }
}
