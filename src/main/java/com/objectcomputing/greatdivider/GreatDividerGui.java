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
        f.setSize(400, 400);
        JPanel glassPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(GP_COLOR);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        glassPanel.setOpaque(false);
        f.setGlassPane(glassPanel);
        JPanel primitivePanel = createTabPanel(new MigLayout("inset 20"));
        f.add(primitivePanel);
        JTextField dividendField = createTextField("",3);
        JTextField divisorField = createTextField("",3);
        JTextField answerField = createTextField("",16);
        answerField.setEnabled(false);

        primitivePanel.add(createLabel("Dividend",SwingConstants.LEADING), "gap para");
        primitivePanel.add(dividendField, "span");
        primitivePanel.add(createLabel("Divisor",SwingConstants.LEADING), "gap para");
        primitivePanel.add(divisorField, "span");
        primitivePanel.add(createLabel("Answer",SwingConstants.LEADING), "gap para");
        primitivePanel.add(answerField,"span");
        primitivePanel.add(createLabel("Status",SwingConstants.LEADING), "gap para");
        JLabel status = createLabel("",SwingConstants.LEADING);
        primitivePanel.add(status, "span");


        JButton calculateButton = new JButton("=");
        primitivePanel.getRootPane().setDefaultButton(calculateButton);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        calculateButton.addActionListener(new DivisionActionListener(dividendField,
                divisorField,f,answerField,progressBar,calculateButton,status));

        primitivePanel.add(calculateButton, "span");//adding button in JFrame
        primitivePanel.add(progressBar, "span");
        f.setVisible(true);//making the frame visible
        primitivePanel.add(new JButton("+"));
        primitivePanel.add(new JButton("1"));
        primitivePanel.add(new JButton("2"));
        primitivePanel.add(new JButton("3"),"wrap");
        primitivePanel.add(new JButton("-"));
        primitivePanel.add(new JButton("4"));
        primitivePanel.add(new JButton("5"));
        primitivePanel.add(new JButton("6"),"wrap");
        primitivePanel.add(new JButton("*"));
        primitivePanel.add(new JButton("7"));
        primitivePanel.add(new JButton("8"));
        primitivePanel.add(new JButton("9"),"wrap");
        primitivePanel.add(new JButton("/"));
        primitivePanel.add(new JButton("."));
        primitivePanel.add(new JButton("0"));
        primitivePanel.add(new JButton("C"),"wrap");

    }
    protected static final Color GP_COLOR = new Color(0, 0, 0, 30);

}
