package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ActiveInactiveStateListener;

import javax.swing.*;

public class CalcOperationButton extends JButton implements ActiveInactiveStateListener {

    private final String name;

    public CalcOperationButton(String name, PrimitivePanel primitivePanel) {
        super(name);
        setActionCommand(name);
        addActionListener(primitivePanel.getAnswerField());
        primitivePanel.getRootFrame().getActiveInactiveStateListeners().add(this);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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
