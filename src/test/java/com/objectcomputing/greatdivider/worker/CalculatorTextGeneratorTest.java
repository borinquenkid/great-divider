package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.Operation;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTextGeneratorTest {

    @Test
    void test_calculate() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        String execute = generator.execute(Operation.CALCULATE, actionCommand, text, commands);
        assertEquals("",execute);

    }

    @Test
    void test_clear() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        String execute = generator.execute(Operation.CLEAR, actionCommand, text, commands);
        assertEquals("",execute);

    }

    @Test
    void test_dec() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("9");
        String execute = generator.execute(Operation.DEC, actionCommand, text, commands);
        assertEquals("9.",execute);

    }

    @Test
    void test_dec_emptyCommands() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        String execute = generator.execute(Operation.DEC, actionCommand, text, commands);
        assertEquals("0.",execute);

    }



    @Test
    void test_add() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        String execute = generator.execute(Operation.PLUS, actionCommand, text, commands);
        assertEquals("textactionCommand",execute);

    }

    @Test
    void test_minus() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        String execute = generator.execute(Operation.MINUS, actionCommand, text, commands);
        assertEquals("textactionCommand",execute);

    }

    @Test
    void test_mult() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        String execute = generator.execute(Operation.MULTI, actionCommand, text, commands);
        assertEquals("textactionCommand",execute);

    }

    @Test
    void test_div() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        String execute = generator.execute(Operation.DIV, actionCommand, text, commands);
        assertEquals("textactionCommand",execute);
    }

    @Test
    void test_null() {
        CalculatorTextGenerator generator = new CalculatorTextGenerator();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        String execute = generator.execute(null, actionCommand, text, commands);
        assertEquals("textactionCommand",execute);
    }
}