package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ActiveInactiveStateListener;

import javax.swing.*;

public class CalcNumberButton extends JButton
        implements ActiveInactiveStateListener {

    public CalcNumberButton(String number, PrimitivePanel primitivePanel) {
        super(number);
        addActionListener(primitivePanel.getAnswerField());
        primitivePanel.getRootFrame().getActiveInactiveStateListeners().add(this);
    }

    @Override
    public void activate() {
        setEnabled(true);
    }

    @Override
    public void inactivate() {
        setEnabled(false);
    }
}
