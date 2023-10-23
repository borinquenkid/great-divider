package com.objectcomputing.greatdivider.worker;

import com.objectcomputing.greatdivider.gui.Operation;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandManagerTest {

    @Test
    void test_calculate() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.CALCULATE,actionCommand,text,commands );
        assertTrue(commands.isEmpty());

    }

    @Test
    void test_clear() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.CLEAR,actionCommand,text,commands );
        assertTrue(commands.isEmpty());

    }

    @Test
    void test_dec() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.DEC,actionCommand,text,commands );
        assertTrue(commands.contains(text));

    }

    @Test
    void test_add() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.PLUS,actionCommand,text,commands );
        assertTrue(commands.contains(actionCommand));

    }

    @Test
    void test_minus() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.MINUS,actionCommand,text,commands );
        assertTrue(commands.contains(actionCommand));

    }

    @Test
    void test_mult() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.MULTI,actionCommand,text,commands );
        assertTrue(commands.contains(actionCommand));

    }

    @Test
    void test_div() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(Operation.DIV,actionCommand,text,commands );
        assertTrue(commands.contains(actionCommand));

    }

    @Test
    void test_null() {
        CommandManager manager = new CommandManager();
        String actionCommand = "actionCommand";
        String text = "text";
        LinkedList<String> commands = new LinkedList<>();
        commands.add("FOO");
        manager.execute(null,actionCommand,text,commands );
        assertTrue(commands.contains(actionCommand));

    }
}