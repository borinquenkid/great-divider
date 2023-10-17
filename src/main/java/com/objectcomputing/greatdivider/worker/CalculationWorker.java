package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.Operation;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class CalculationWorker extends SwingWorker<String, Void> {

    private final LinkedList<String> commands;
    private final List<ActiveInactiveStateListener> activeInactiveStateListenerList;
    private final ProgressListener progressListener;
    private final AnswerListener answerListener;

    private final DecimalFormat df = new DecimalFormat("#.######E00");


    public CalculationWorker(LinkedList<String> commands
            , List<ActiveInactiveStateListener> activeInactiveStateListenerList
            , ProgressListener progressListener, AnswerListener answerListener) {

        this.commands = commands;
        this.activeInactiveStateListenerList = activeInactiveStateListenerList;
        this.progressListener = progressListener;
        this.answerListener = answerListener;
    }

    //only used for testing
    public void doSync() {
        doInBackground();
    }

    @Override
    protected String doInBackground() {
        LinkedList<String> compressed = new LinkedList<>();

        final int stepSize1 = 50 / commands.size();
        IntStream.range(0, commands.size())
                .forEach(i -> {
                    String e = commands.get(i);
                    Operation token = Operation.getOperation(e);
                    if (token == null || token == Operation.DEC) {
                        //A NUMBER  OR DECIMAL
                        if (compressed.isEmpty()
                                || Operation.getOperation(compressed.getLast()) != null
                        ) {
                            compressed.add(e);
                        } else {
                            compressed.add(compressed.removeLast() + e);
                        }
                    } else {
                        compressed.add(e);
                    }
                    progressListener.setProgress(i * stepSize1);
                });

        var ref = new Object() {
            BigDecimal total = null;
            Operation operation = null;

            String error = null;
        };
        final int stepSize2 = 50 / compressed.size();
        IntStream.range(0, compressed.size())
            .forEach(i -> {
                String e = compressed.get(i);
                Operation opOrNumber = Operation.getOperation(e);
                if (ref.error != null) {
                    //SKIP ERROR
                } else if (ref.total == null) {
                    ref.total = new BigDecimal(e);
                } else if (opOrNumber != null) {
                    ref.operation = opOrNumber;
                } else {
                    // ARITHMETIC
                    if (ref.operation == Operation.PLUS) {
                        ref.total = ref.total.add(new BigDecimal(e));
                    } else if (ref.operation == Operation.MINUS) {
                        ref.total = ref.total.subtract(new BigDecimal(e));
                    } else if (ref.operation == Operation.MULTI) {
                        ref.total = ref.total.multiply(new BigDecimal(e));
                    } else if (ref.operation == Operation.DIV) {
                        BigDecimal divisor = new BigDecimal(e);
                        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
                            ref.error = "DIVIDE BY ZERO";
                        } else {
                            ref.total = ref.total.divide(divisor, 7, RoundingMode.HALF_UP);
                        }
                    } else {
                        ref.error ="BAD OPERATION";
                    }
                }
                progressListener.setProgress(50 + (i * stepSize2));
            });
        return ref.error != null ? ref.error :  df.format(ref.total);
    }

    @Override
    protected void done() {
        String answer = null;
        try {
            answer = get();
        } catch (Throwable e) {
//This should not happen
        }
        activeInactiveStateListenerList.forEach(ActiveInactiveStateListener::activate);
        progressListener.setProgress(0);
        answerListener.setAnswer(answer);
    }
}
