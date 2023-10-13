package com.objectcomputing.greatdivider.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static com.objectcomputing.greatdivider.MigLayoutUtil.createLabel;

public class PrimitivePanel extends JPanel {



    AnswerField answerField;
    JLabel statusLabel;
    CalculationProgressBar progressBar;

    public PrimitivePanel(RootFrame rootFrame) {
        super(new MigLayout("inset 20"));
        setOpaque(true);
        progressBar = new CalculationProgressBar();
        answerField = new AnswerField(progressBar);
        statusLabel = createLabel("",SwingConstants.LEADING);

        add(new CalcOperationButton("+", answerField));
        add(new CalcNumberButton("1", answerField));
        add(new CalcNumberButton("2", answerField));
        add(new CalcNumberButton("3", answerField),"wrap");
        add(new CalcOperationButton("-", answerField));
        add(new CalcNumberButton("4", answerField));
        add(new CalcNumberButton("5", answerField));
        add(new CalcNumberButton("6", answerField),"wrap");
        add(new CalcOperationButton("*", answerField));
        add(new CalcNumberButton("7", answerField));
        add(new CalcNumberButton("8", answerField));
        add(new CalcNumberButton("9", answerField),"wrap");
        add(new CalcOperationButton("/", answerField));
        add(new CalcNumberButton(".", answerField));
        add(new CalcNumberButton("0", answerField));
        add(new CalcOperationButton("C", answerField),"wrap");
        CalcOperationButton calculateButton = new CalcOperationButton("=", answerField);
        add(calculateButton,"south");
        add(createLabel("Answer",SwingConstants.CENTER), "gap para");
        add(answerField,"span");
        add(createLabel("Status",SwingConstants.CENTER), "gap para");
        add(statusLabel, "span");
        add(progressBar, "span");
        rootFrame.getRootPane().setDefaultButton(calculateButton);
    }
}
