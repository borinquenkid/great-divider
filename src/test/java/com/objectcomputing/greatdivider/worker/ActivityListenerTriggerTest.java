package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.CalcNumberButton;
import com.objectcomputing.greatdivider.gui.CalcOperationButton;
import com.objectcomputing.greatdivider.gui.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ActivityListenerTriggerTest {



    @Mock
    CalcOperationButton calcOperationButton;

    @Mock
    CalcNumberButton calcNumberButton;


    @Test
    void test_calculate() {
        List<ActiveInactiveStateListener> listenerList = Collections.singletonList(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.CALCULATE, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
    }

    @Test
    void test_plus() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.PLUS, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
        Mockito.verifyNoInteractions(calcNumberButton);

    }

    @Test
    void test_minus() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.MINUS, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
        Mockito.verifyNoInteractions(calcNumberButton);
    }

    @Test
    void test_multi() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.MULTI, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
        Mockito.verifyNoInteractions(calcNumberButton);
    }

    @Test
    void test_div() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.DIV, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
        Mockito.verifyNoInteractions(calcNumberButton);
    }

    @Test
    void test_dec() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.DEC, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
        Mockito.verifyNoInteractions(calcNumberButton);
    }

    @Test
    void test_clear() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(Operation.CLEAR, listenerList);
        Mockito.verify(calcOperationButton).inactivate();
        Mockito.verifyNoInteractions(calcNumberButton);
    }

    @Test
    void test_null() {
        List<ActiveInactiveStateListener> listenerList = new ArrayList<>();
        listenerList.add(calcNumberButton);
        listenerList.add(calcOperationButton);
        ActivityListenerTrigger trigger = new ActivityListenerTrigger();
        trigger.execute(null, listenerList);
        Mockito.verify(calcOperationButton).activate();
        Mockito.verifyNoInteractions(calcNumberButton);
    }


}



