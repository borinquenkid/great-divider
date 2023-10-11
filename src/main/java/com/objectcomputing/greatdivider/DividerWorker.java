package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class DividerWorker extends SwingWorker<BigDecimal, Void> {

    private final JTextField dividendField;
    private final JTextField divisorField;
    private final JTextField answerField;
    private final JButton calculateButton;
    private final JProgressBar progressBar;
    private final JLabel statusLabel;
    private final JFrame f;

    private final DecimalFormat df = new DecimalFormat("#.######E00");

    public DividerWorker(JTextField dividendField,
                         JTextField divisorField,
                         JTextField answer,
                         JButton calculateButton,
                         JProgressBar progressBar,
                         JLabel statusLabel,
                         JFrame f) {

        this.dividendField = dividendField;
        this.divisorField = divisorField;
        this.answerField = answer;
        this.calculateButton = calculateButton;
        this.progressBar = progressBar;
        this.statusLabel = statusLabel;
        this.f = f;
    }

    @Override
    protected BigDecimal doInBackground() {
        IntStream.rangeClosed(1,5).boxed().forEach( index -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
            progressBar.setValue(20 * index);
        });
        BigDecimal dividend = new BigDecimal(dividendField.getText());
        BigDecimal divisor = new BigDecimal(divisorField.getText());
        if (!divisor.equals(new BigDecimal(0))) {
            return dividend.divide(divisor, 7, RoundingMode.HALF_UP);
        }
        return null;
    }

    @Override
    protected void done() {
        BigDecimal divide = null;
        try {
            divide = get();
        } catch (InterruptedException | ExecutionException ignore) {
        }

        String label = divide != null ? "" : "Cant divide by zero";
        statusLabel.setText(label);
        String answerText = divide != null ? df.format(divide) : "";
        answerField.setText(answerText);
        dividendField.setEnabled(true);
        divisorField.setEnabled(true);
        calculateButton.setEnabled(true);
        calculateButton.setText("Calculate");
        f.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
