package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ActiveInactiveStateListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RootFrame extends JFrame implements ActiveInactiveStateListener {

    private final List<ActiveInactiveStateListener> activeInactiveStateListeners = new ArrayList<>();

    public List<ActiveInactiveStateListener> getActiveInactiveStateListeners() {
        return activeInactiveStateListeners;
    }

    public RootFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setGlassPane(new GlassPanel());
    }

    @Override
    public void activate() {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void inactivate() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
}
