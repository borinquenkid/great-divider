package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class GreatDividerGui {

    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame

        JTextField t1,t2;
        t1=new JTextField("");
        t1.setBounds(50,100, 100,30);
        t1.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                return (isNumeric(t1.getText()));
            }
        });
        t2=new JTextField("");
        t2.setBounds(50,150, 100,30);
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

        JButton b=new JButton("calculate");//creating instance of JButton
        b.setBounds(50,200,100, 40);//x axis, y axis, width, height
        final JLabel answer=new JLabel();
        answer.setBounds(50,250, 100,30);
        answer.setText("Not Clicked");
        f.add(answer);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DecimalFormat df = new DecimalFormat("#.######E00");
                BigDecimal divide = new BigDecimal(t1.getText()).divide(new BigDecimal(t2.getText()), 7, RoundingMode.HALF_UP);
                answer.setText(df.format(divide));
            }
        });

        f.add(b);//adding button in JFrame



        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
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
