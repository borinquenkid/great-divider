package com.objectcomputing.greatdivider;

import net.miginfocom.swing.MigLayout;
import static org.apache.commons.lang3.math.NumberUtils.*;

import javax.swing.*;

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
        JTextField t1 = createTextField("",3);
        JTextField  t2 = createTextField("",3);
        JTextField  answer = createTextField("",10);
        answer.setEnabled(false);

        p.add(createLabel("Dividend",SwingConstants.LEADING), "gap para");
        p.add(t1, "span");
        p.add(createLabel("Divisor",SwingConstants.LEADING), "gap para");
        p.add(t2, "span");
        p.add(createLabel("Answer",SwingConstants.LEADING), "gap para");
        p.add(answer,"span");
        p.add(createLabel("Status",SwingConstants.LEADING), "gap para");
        JLabel status = createLabel("",SwingConstants.LEADING);
        p.add(status, "span");


        JButton b = new JButton("calculate");
        p.getRootPane().setDefaultButton(b);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        b.addActionListener(e -> {
            boolean anyBlanks = !isCreatable(t1.getText()) || !isCreatable(t2.getText());
            if (anyBlanks) {
                status.setText("Fill in both dividend and divisor");
            } else if(createInteger(t2.getText()) == 0) {
                status.setText("Can not divide by zero");
            } else {
                new DividerWorker(t1, t2, answer, b, progressBar,status, f).execute();
            }

        });

        p.add(b, "span");//adding button in JFrame
        p.add(progressBar, "span");
        f.setVisible(true);//making the frame visible
    }
}
