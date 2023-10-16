package com.objectcomputing.greatdivider.worker;

public interface ActiveInactiveStateListener {
    
    void activate();
    
    void inactivate();

    String getText();
}
