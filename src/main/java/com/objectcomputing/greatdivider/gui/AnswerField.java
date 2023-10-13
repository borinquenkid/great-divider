package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ActiveInactiveStateListener;
import com.objectcomputing.greatdivider.worker.AnswerListener;
import com.objectcomputing.greatdivider.worker.CalculationWorker;
import com.objectcomputing.greatdivider.worker.ProgressListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class AnswerField extends JTextField implements ActionListener, AnswerListener {

    private final LinkedList<String> commands = new LinkedList<>();
    private final List<ActiveInactiveStateListener> activeInactiveStateListeners;
    private final ProgressListener progressListener;

    public AnswerField(List<ActiveInactiveStateListener> activeInactiveStateListenerLIst, ProgressListener progressListener) {
        super("", 16);
        activeInactiveStateListeners = activeInactiveStateListenerLIst;
        this.progressListener = progressListener;
        setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof  CalcOperationButton || source instanceof  CalcNumberButton) {

            String actionCommand = e.getActionCommand();
            if (Operation.getOperation(actionCommand) == Operation.CALCULATE) {
                setAnswer("   ");
                activeInactiveStateListeners.forEach(ActiveInactiveStateListener::inactivate);

                new CalculationWorker(new LinkedList<>(commands), activeInactiveStateListeners
                        , progressListener, this).doSync();
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
                setAnswer(getText()+ actionCommand);
                commands.add(actionCommand);
            }

         }
    }


    @Override
    public void setAnswer(String text) {
        setText(text);
    }
}
