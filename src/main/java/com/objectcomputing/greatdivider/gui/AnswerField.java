package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ActiveInactiveStateListener;
import com.objectcomputing.greatdivider.worker.AnswerListener;
import com.objectcomputing.greatdivider.worker.CalculationWorker;
import com.objectcomputing.greatdivider.worker.ProgressListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
        if (source instanceof  CalcOperationButton || source instanceof  CalcNumberButton) {
            String actionCommand = e.getActionCommand();
            Operation operation = Operation.getOperation(actionCommand);
            if (operation == Operation.CALCULATE) {
                activeInactiveStateListeners.stream()
                        .filter( it -> Objects.equals(it.getText(), "0"))
                        .forEach(ActiveInactiveStateListener::activate);
                setAnswer("");
                activeInactiveStateListeners.forEach(ActiveInactiveStateListener::inactivate);

                new CalculationWorker(new LinkedList<>(commands)
                        , activeInactiveStateListeners
                        , progressListener
                        , this).execute();
                commands.clear();
            } else if (operation == Operation.CLEAR) {
                activeInactiveStateListeners.stream()
                        .filter( it -> Objects.equals(it.getText(), "0"))
                        .forEach(ActiveInactiveStateListener::activate);
                setText("");
                commands.clear();
            }   else if (operation == Operation.DEC) {
                activeInactiveStateListeners.stream()
                        .filter( it -> Objects.equals(it.getText(), "0"))
                        .forEach(ActiveInactiveStateListener::activate);
                if (commands.isEmpty()) {
                    String text = "0.";
                    commands.add(text);
                    setText(text);
                } else {
                    String text = commands.removeLast() + ".";
                    commands.add(text);
                    setText(text);
                }
            } else if (operation == null && Objects.equals(actionCommand, "0") ) {
                if (commands.isEmpty()) {
                    activeInactiveStateListeners.stream()
                            .filter(it -> Objects.equals(it.getText(), "0"))
                            .forEach(ActiveInactiveStateListener::inactivate);
                    String text = "0";
                    commands.add(text);
                    setText(text);
                } else {
                    //DEC
                    String text = commands.removeLast() + "0";
                    commands.add(text);
                    setText(text);
                }

            } else {
                activeInactiveStateListeners.stream()
                        .filter( it -> Objects.equals(it.getText(), "0"))
                        .forEach(ActiveInactiveStateListener::activate);
                if (commands.isEmpty()) {
                    setText("");
                }
                commands.add(actionCommand);
                setText(getText()+ actionCommand);
                if (operation != null) {
                    activeInactiveStateListeners.stream()
                            .filter( it -> it instanceof CalcOperationButton)
                            .forEach(ActiveInactiveStateListener::inactivate);
                } else {
                    activeInactiveStateListeners.stream()
                            .filter( it -> it instanceof CalcOperationButton)
                            .forEach(ActiveInactiveStateListener::activate);

                }
            }

         }
    }


    @Override
    public void setAnswer(String text) {
        setText(text);
    }
}
