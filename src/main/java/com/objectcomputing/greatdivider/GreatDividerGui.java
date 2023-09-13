package com.objectcomputing.greatdivider;

import net.miginfocom.swing.MigLayout;
import static org.apache.commons.lang3.math.NumberUtils.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class GreatDividerGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndRun());
    }

    private static void createAndRun() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 300);
        f.setLayout(new MigLayout());
        JTextField t1, t2;
        t1 = new JTextField(3);

        t2 = new JTextField(3);
        f.add(t1, "cell 0 0");
        f.add(t2, "cell 0 1");

        JButton b = new JButton("calculate");
        f.getRootPane().setDefaultButton(b);
        final JLabel answer = new JLabel();
        answer.setText("    ");

        f.add(answer, "cell 0 2");
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        b.addActionListener(e -> {
            boolean anyBlanks = !isCreatable(t1.getText())
                    || !isCreatable(t2.getText());
            if (anyBlanks) {
                answer.setText("Fill in both dividend and divisor");
            } else if(createInteger(t2.getText()) == 0) {
                answer.setText("Can not divide by zero");
            } else {
                new DividerWorker(t1, t2, answer, b, progressBar, f).execute();
            }

        });

        f.add(b, "cell 0 3");//adding button in JFrame
        f.add(progressBar, "cell 0 4");
        f.setVisible(true);//making the frame visible
    }

    private static final Pattern IS_NUMERIC = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return IS_NUMERIC.matcher(strNum).matches();
    }


}
