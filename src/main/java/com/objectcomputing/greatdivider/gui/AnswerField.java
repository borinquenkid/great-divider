package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnswerField extends JTextField implements ActionListener, AnswerListener {

    private final LinkedList<String> commands = new LinkedList<>();
    private final List<ActiveInactiveStateListener> activeInactiveStateListeners = new ArrayList<>();
    private final ProgressListener progressListener;

    public AnswerField(ProgressListener progressListener) {
        super("", 16);
        this.progressListener = progressListener;
        setOpaque(true);
    }

    public List<ActiveInactiveStateListener> getActiveInactiveStateListeners() {
        return activeInactiveStateListeners;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (commands.isEmpty()) {
            setText("");
        }
        if (source instanceof  CalcOperationButton || source instanceof  CalcNumberButton) {
            String actionCommand = e.getActionCommand();
            Operation operation = Operation.getOperation(actionCommand);
            String currentText = getText();
            setText(new CalculatorTextGenerator().execute(operation, actionCommand, currentText, commands));
            new ActivityListenerTrigger().execute(operation,activeInactiveStateListeners);
            if (operation == Operation.CALCULATE) {
                new CalculationWorker(new LinkedList<>(commands)
                        , activeInactiveStateListeners
                        , progressListener
                        , this).execute();
            }
            new CommandManager().execute(operation, actionCommand,getText(),commands);


        }
    }


    @Override
    public void setAnswer(String text) {
        setText(text);
    }
}
