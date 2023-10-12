package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.objectcomputing.greatdivider.DivisionWarningEnum.OK;

public class DivisionActionListener implements ActionListener {
    private JTextField dividendField;
    private JTextField divisorField;
    private JFrame f;
    private JTextField answerField;
    private JProgressBar progressBar;
    private JButton calculateButton;
    private JLabel status;

    public DivisionActionListener(JTextField dividendField, JTextField divisorField, JFrame f, JTextField answerField, JProgressBar progressBar, JButton calculateButton, JLabel status) {
        this.dividendField = dividendField;
        this.divisorField = divisorField;
        this.f = f;
        this.answerField = answerField;
        this.progressBar = progressBar;
        this.calculateButton = calculateButton;
        this.status = status;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DivisionWarningEnum result = new DivisionWarningGenerator().apply(dividendField.getText(), divisorField.getText());
        if (result.equals(OK)) {
            f.getGlassPane().setVisible(true);
            answerField.setText("        ");
            progressBar.setValue(0);
            f.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dividendField.setEnabled(false);
            divisorField.setEnabled(false);
            calculateButton.setEnabled(false);
            calculateButton.setText("Calculating");
            new DividerWorker(dividendField, divisorField, answerField, calculateButton, progressBar,status, f).execute();
        } else {
            status.setText(result.getMessage());
        }
    }
}
