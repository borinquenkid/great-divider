package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.CalcOperationButton;
import com.objectcomputing.greatdivider.gui.Operation;

import java.util.List;

public class ActivityListenerTrigger {

    public void execute(Operation operation, List<ActiveInactiveStateListener> activeInactiveStateListeners) {

        if (operation == Operation.CALCULATE) {
            activeInactiveStateListeners.forEach(ActiveInactiveStateListener::inactivate);
        } else if (operation != null) {
            activeInactiveStateListeners.stream()
                    .filter(it -> it instanceof CalcOperationButton)
                    .forEach(ActiveInactiveStateListener::inactivate);
        } else {
            activeInactiveStateListeners.stream()
                    .filter(it -> it instanceof CalcOperationButton)
                    .forEach(ActiveInactiveStateListener::activate);
        }

    }
}
