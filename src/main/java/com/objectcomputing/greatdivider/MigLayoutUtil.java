package com.objectcomputing.greatdivider;

import javax.swing.*;
import java.awt.*;

/**
 * Copying stuff from MigLayout Demo for reuse
 */
public class MigLayoutUtil {

    private static final boolean OPAQUE = false;

    public static JPanel createTabPanel(LayoutManager lm)
    {
        JPanel panel = new JPanel(lm);
        panel.setOpaque(OPAQUE);
        return panel;
    }

    public static JLabel createLabel(String text, int align)
    {
        return new JLabel(text, align);
    }

    public static JTextField createTextField(String text, int cols)
    {
        return new JTextField(text, cols);
    }
}
