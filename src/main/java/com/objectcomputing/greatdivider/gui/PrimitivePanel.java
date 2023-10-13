package com.objectcomputing.greatdivider.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.objectcomputing.greatdivider.MigLayoutUtil.createLabel;

public class PrimitivePanel extends JPanel {


    private final RootFrame rootFrame;

    AnswerField answerField;
    JLabel statusLabel;
    CalculationProgressBar progressBar;

    public PrimitivePanel(RootFrame rootFrame) {
        super(new MigLayout("inset 20, debug"));
        this.rootFrame = rootFrame;
        setOpaque(true);


        progressBar = new CalculationProgressBar();
        answerField = new AnswerField(rootFrame.getActiveInactiveStateListeners(), progressBar);
        statusLabel = createLabel("",SwingConstants.LEADING);


        add(new CalcOperationButton("+", this));
        add(new CalcNumberButton("1", this));
        add(new CalcNumberButton("2", this));
        add(new CalcNumberButton("3", this),"wrap");
        add(new CalcOperationButton("-", this));
        add(new CalcNumberButton("4", this));
        add(new CalcNumberButton("5", this));
        add(new CalcNumberButton("6", this),"wrap");
        add(new CalcOperationButton("*", this));
        add(new CalcNumberButton("7", this));
        add(new CalcNumberButton("8", this));
        add(new CalcNumberButton("9", this),"wrap");
        add(new CalcOperationButton("/", this));
        add(new CalcNumberButton(".", this));
        add(new CalcNumberButton("0", this));
        add(new CalcOperationButton("C", this),"wrap");
        CalcOperationButton calculateButton = new CalcOperationButton("=", this);
        add(calculateButton,"south");
        add(createLabel("Answer",SwingConstants.CENTER), "gap para");
        add(answerField,"span");
        add(createLabel("Status",SwingConstants.CENTER), "gap para");
        add(statusLabel, "span");
        add(progressBar, "span");
        rootFrame.getRootPane().setDefaultButton(calculateButton);

    }


    public RootFrame getRootFrame() {
        return rootFrame;
    }

    public AnswerField getAnswerField() {
        return answerField;
    }
}
