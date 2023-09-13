package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class GreatDividerGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndRun());
    }

    private static void createAndRun() {
        JFrame f = new JFrame();//creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 500);//400 width and 500 height
        f.setLayout(new GridLayout(5,1));//using no layout managers
        JTextField t1, t2;
        t1 = new JTextField("");
        t1.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                return (isNumeric(t1.getText()));
            }
        });
        t2 = new JTextField("");
        t2.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = t2.getText();
                boolean isNumber = isNumeric(text);
                return (isNumber && (!new BigDecimal(text).equals(new BigDecimal(0))));
            }
        });
        f.add(t1);
        f.add(t2);

        JButton b = new JButton("calculate");
        f.getRootPane().setDefaultButton(b);
        final JLabel answer = new JLabel();
        answer.setText("Not Clicked");

        f.add(answer);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DividerWorker worker = new DividerWorker(t1, t2, answer, b, progressBar, f);
                worker.execute();
            }
        });

        f.add(b);//adding button in JFrame
        f.add(progressBar);





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
