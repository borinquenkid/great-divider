package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.Operation;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class CalculationWorker extends SwingWorker<BigDecimal, Void> {

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

    public void doSync() {
        doInBackground();
    }

    @Override
    protected BigDecimal doInBackground() {
        LinkedList<String> compressed = new LinkedList<>();

        final int stepSize1 = 50 / commands.size();
        IntStream.range(0, commands.size())
                .forEach(i -> {
                    String e = commands.get(i);
                    if (Operation.getOperation(e) == null) {
                        //A NUMBER  OR DECIMAL
                        if (compressed.isEmpty() || Operation.getOperation(compressed.getLast()) != null) {
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
        };
        final int stepSize2 = 50 / compressed.size();
        IntStream.range(0, compressed.size())
            .forEach(i -> {
                String e = compressed.get(i);
                Operation opOrNumber = Operation.getOperation(e);
                if (ref.total == null && ref.operation == null & opOrNumber == null) {
                    ref.total = new BigDecimal(e);
                } else if (ref.total != null && opOrNumber != null) {
                    ref.operation = opOrNumber;
                } else if (ref.total != null) {
                    // ARITHMETIC
                    if (ref.operation == Operation.PLUS) {
                        ref.total = ref.total.add(new BigDecimal(e));
                    } else if (ref.operation == Operation.MINUS) {
                        ref.total = ref.total.subtract(new BigDecimal(e));
                    } else if (ref.operation == Operation.MULTI) {
                        ref.total = ref.total.multiply(new BigDecimal(e));
                    } else if (ref.operation == Operation.DIV) {
                        ref.total = ref.total.divide(new BigDecimal(e), 7, RoundingMode.HALF_UP);
                    } else {
                        throw new RuntimeException("BAD OPERATIONS 1");
                    }
                } else {
                    System.out.println("BAD OPERATIONS 6");
                    throw new RuntimeException("BAD OPERATIONS");
                }
                progressListener.setProgress(50 + (i * stepSize2));
            });
        return ref.total;
    }

    @Override
    protected void done() {
        BigDecimal result = null;
        try {
            result = get();
        } catch (Throwable e) {
//This should not happen
        }
        activeInactiveStateListenerList.forEach(ActiveInactiveStateListener::activate);
        progressListener.setProgress(0);
        String answerText = result != null ? df.format(result) : "";
        answerListener.setAnswer(answerText);
    }
}
