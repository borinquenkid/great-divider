package com.objectcomputing.greatdivider.gui;

import com.objectcomputing.greatdivider.worker.ProgressListener;

import javax.swing.*;

public class CalculationProgressBar extends JProgressBar implements ProgressListener {


    public CalculationProgressBar() {
        super(0,100);
        setValue(0);
        setStringPainted(true);
    }

    public void setProgress(int progress) {
        setValue(progress);
    }
}
