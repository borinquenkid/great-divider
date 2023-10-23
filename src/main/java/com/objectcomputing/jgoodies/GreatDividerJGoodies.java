package com.objectcomputing.jgoodies;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.objectcomputing.greatdivider.gui.AnswerField;
import com.objectcomputing.greatdivider.gui.CalcNumberButton;
import com.objectcomputing.greatdivider.gui.CalcOperationButton;
import com.objectcomputing.greatdivider.gui.CalculationProgressBar;
import com.objectcomputing.greatdivider.gui.RootFrame;

import javax.swing.*;

import static com.objectcomputing.greatdivider.MigLayoutUtil.createLabel;

public class GreatDividerJGoodies {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GreatDividerJGoodies().createAndRun());
    }

    public void createAndRun() {
        RootFrame rootFrame = new RootFrame();
        JPanel primitivePanel = buildPrimitivePanel(rootFrame);
        primitivePanel.setOpaque(true);
        rootFrame.add(primitivePanel);
        rootFrame.setVisible(true);
    }

    /**
     * This Javadoc is for the syntax of column and rowspec*
     * columnSpec ::= [columnAlignment:] size [:resizeBehavior]
     * rowSpec ::= [rowAlignment :] size [:resizeBehavior]
     * columnAlignment ::= LEFT | CENTER | RIGHT | FILL | L | C | R | F
     * rowAlignment ::= TOP | CENTER | BOTTOM | FILL | T | C | B | F
     * size ::= constantSize | componentSize | boundedSize
     * componentSize ::= MIN | PREF | DEFAULT | M | P | D
     * constantSize ::= <integer>integerUnit | <double>doubleUnit
     * integerUnit ::= PX | PT | DLU
     * doubleUnit ::= IN | MM | CM
     * boundedSize ::= MIN(constantSize;componentSize)| MAX(constantSize;componentSize)
     * resizeBehavior ::= NONE | GROW | GROW(<double>) | G(<double>)
     *
     * @param rootFrame the Root Frame
     * @return Constructed panel
     */
    private JPanel buildPrimitivePanel(RootFrame rootFrame) {
        CalculationProgressBar progressBar = new CalculationProgressBar();
        AnswerField answerField = new AnswerField(progressBar);
        answerField.setEnabled(false);
        JLabel statusLabel = createLabel("", SwingConstants.LEADING);
        String encodedColumnSpecs = "pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow";
        ColumnSpec[] columnSpecs = ColumnSpec.decodeSpecs(encodedColumnSpecs);
        String encodedRowSpecs = "pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow, 2dlu, pref:grow";
        RowSpec[] rowSpecs = RowSpec.decodeSpecs(encodedRowSpecs);
        FormLayout layout = new FormLayout(columnSpecs, rowSpecs);
        PanelBuilder builder = new PanelBuilder(layout);
        builder.border(Borders.DIALOG);
        CellConstraints cc = new CellConstraints();
        builder.add(new CalcOperationButton("+", answerField),cc.xy(1,1));
        builder.add(new CalcNumberButton("1", answerField),cc.xy(3,1));
        builder.add(new CalcNumberButton("2", answerField),cc.xy(5,1));
        builder.add(new CalcNumberButton("3", answerField),cc.xy(7,1));
        builder.add(new CalcOperationButton("-", answerField),cc.xy(1,3));
        builder.add(new CalcNumberButton("4", answerField),cc.xy(3,3));
        builder.add(new CalcNumberButton("5", answerField),cc.xy(5,3));
        builder.add(new CalcNumberButton("6", answerField),cc.xy(7,3));
        builder.add(new CalcOperationButton("*", answerField),cc.xy(1,5));
        builder.add(new CalcNumberButton("7", answerField),cc.xy(3,5));
        builder.add(new CalcNumberButton("8", answerField),cc.xy(5,5));
        builder.add(new CalcNumberButton("9", answerField),cc.xy(7,5));
        builder.add(new CalcOperationButton("/", answerField),cc.xy(1,7));
        builder.add(new CalcNumberButton(".", answerField),cc.xy(3,7));
        builder.add(new CalcNumberButton("0", answerField),cc.xy(5,7));
        builder.add(new CalcOperationButton("C", answerField),cc.xy(7,7));
        CalcOperationButton calculateButton = new CalcOperationButton("=", answerField);
        builder.add(calculateButton,cc.xyw(1,9,7));
        rootFrame.getRootPane().setDefaultButton(calculateButton);
        builder.addLabel("Answer",cc.xy(1,11)).setLabelFor(answerField);
        builder.add(answerField, cc.xyw(3,11,5));
        builder.addLabel("Status",cc.xy(1,13)).setLabelFor(statusLabel);
        builder.add(statusLabel, cc.xyw(3,13,5));
        builder.add(progressBar, cc.xyw(1,15,7));

        return builder.getPanel();
    }


}
