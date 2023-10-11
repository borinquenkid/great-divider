package com.objectcomputing.greatdivider;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;

import static com.objectcomputing.greatdivider.DivisionWarningEnum.OK;
import static com.objectcomputing.greatdivider.MigLayoutUtil.*;

public class GreatDividerGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GreatDividerGui::createAndRun);
    }

    private static void createAndRun() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300);
        JPanel p = createTabPanel(new MigLayout("inset 20"));
        f.add(p);
        JTextField dividendField = createTextField("",3);
        JTextField  divisorField = createTextField("",3);
        JTextField  answerField = createTextField("",10);
        answerField.setEnabled(false);

        p.add(createLabel("Dividend",SwingConstants.LEADING), "gap para");
        p.add(dividendField, "span");
        p.add(createLabel("Divisor",SwingConstants.LEADING), "gap para");
        p.add(divisorField, "span");
        p.add(createLabel("Answer",SwingConstants.LEADING), "gap para");
        p.add(answerField,"span");
        p.add(createLabel("Status",SwingConstants.LEADING), "gap para");
        JLabel status = createLabel("",SwingConstants.LEADING);
        p.add(status, "span");


        JButton calculateButton = new JButton("calculate");
        p.getRootPane().setDefaultButton(calculateButton);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        calculateButton.addActionListener(e -> {
            DivisionWarningEnum result = new DivisionWarningGenerator().apply(dividendField.getText(), divisorField.getText());
            if (result.equals(OK)) {
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
        });

        p.add(calculateButton, "span");//adding button in JFrame
        p.add(progressBar, "span");
        f.setVisible(true);//making the frame visible
    }
}
