package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ActiveInactiveStateListener;

import javax.swing.*;
import java.awt.*;

public class GlassPanel extends JPanel
        implements ActiveInactiveStateListener {

    public GlassPanel() {
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(GP_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private static final Color GP_COLOR = new Color(0, 0, 0, 30);

    @Override
    public void activate() {
        setVisible(true);
    }

    @Override
    public void inactivate() {
        setVisible(false);
    }
}
