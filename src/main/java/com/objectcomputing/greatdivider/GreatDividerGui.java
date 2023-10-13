package com.objectcomputing.greatdivider;

import com.objectcomputing.greatdivider.gui.PrimitivePanel;
import com.objectcomputing.greatdivider.gui.RootFrame;

import javax.swing.*;

public class GreatDividerGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GreatDividerGui().createAndRun());
    }

    public void createAndRun() {
        RootFrame rootFrame = new RootFrame();
        JPanel primitivePanel = new PrimitivePanel(rootFrame);
        rootFrame.add(primitivePanel);
        rootFrame.setVisible(true);

    }


}
