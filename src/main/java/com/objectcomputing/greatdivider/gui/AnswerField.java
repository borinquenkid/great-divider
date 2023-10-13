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
            if (Operation.getOperation(actionCommand) == Operation.CALCULATE) {
                setAnswer("   ");
                activeInactiveStateListeners.forEach(ActiveInactiveStateListener::inactivate);

                new CalculationWorker(new LinkedList<>(commands)
                        , activeInactiveStateListeners
                        , progressListener
                        , this).execute();
                commands.clear();
            } else if (Operation.getOperation(actionCommand) == Operation.CLEAR) {
                setText("     ");
                commands.clear();
                setAnswer("   ");
            }
            else {
                if (commands.isEmpty()) {
                    setAnswer("   ");
                }
                commands.add(actionCommand);
                setAnswer(getText()+ actionCommand);
                if (Operation.getOperation(actionCommand) != null) {
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
