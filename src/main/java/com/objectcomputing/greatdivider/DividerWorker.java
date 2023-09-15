package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class DividerWorker extends SwingWorker<BigDecimal, Void> {

    private final JTextField t1;
    private final JTextField t2;
    private final JTextField answer;
    private final JButton b;
    private final JProgressBar progressBar;
    private final JLabel status;
    private final JFrame f;

    private final DecimalFormat df = new DecimalFormat("#.######E00");

    public DividerWorker(JTextField t1,
                         JTextField t2,
                         JTextField answer,
                         JButton b,
                         JProgressBar progressBar,
                         JLabel status,
                         JFrame f) {

        this.t1 = t1;
        this.t2 = t2;
        this.answer = answer;
        this.b = b;
        this.progressBar = progressBar;
        this.status = status;
        this.f = f;
    }

    @Override
    protected BigDecimal doInBackground() {
        answer.setText("        ");
        progressBar.setValue(0);
        f.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        b.setEnabled(false);
        b.setText("Calculating");
        IntStream.rangeClosed(1,5).boxed().forEach( index -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
            progressBar.setValue(20 * index);
        });

        BigDecimal dividend = new BigDecimal(t1.getText());
        BigDecimal divisor = new BigDecimal(t2.getText());
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
        status.setText(label);
        String answerText = divide != null ? df.format(divide) : "";
        answer.setText(answerText);
        b.setEnabled(true);
        b.setText("Calculate");
        f.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
