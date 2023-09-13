package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

public class DividerWorker extends SwingWorker<BigDecimal, Void> {

    private final JTextField t1;
    private final JTextField t2;
    private final JLabel answer;
    private final JButton b;

    private final DecimalFormat df = new DecimalFormat("#.######E00");

    public DividerWorker(JTextField t1, JTextField t2, JLabel answer, JButton b) {

        this.t1 = t1;
        this.t2 = t2;
        this.answer = answer;
        this.b = b;
    }

    @Override
    protected BigDecimal doInBackground() throws Exception {
        b.setEnabled(false);
        b.setText("Calculating");
        Thread.sleep(5 * 1000);
        BigDecimal numerator = new BigDecimal(t1.getText());
        BigDecimal denominator = new BigDecimal(t2.getText());
        if (!denominator.equals(new BigDecimal(0))) {
            return numerator.divide(denominator, 7, RoundingMode.HALF_UP);
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
        String label = divide != null ? df.format(divide) : "Cant divide by zero";
        answer.setText(label);
        b.setEnabled(true);
        b.setText("Calculate");

    }
}
