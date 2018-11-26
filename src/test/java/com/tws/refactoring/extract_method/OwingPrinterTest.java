package com.tws.refactoring.extract_method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

public class OwingPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final static OwingPrinter owingPrinter = new OwingPrinter();
    private final static String dummyName = "DUMMY NAME";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void should_printOwing_given_single_order() {
        final double amount = 25.5;
        Order order = new Order(amount);
        owingPrinter.printOwing(dummyName, Arrays.asList(order));

        String expectedOutput = "";
        expectedOutput += "*****************************\r\n";
        expectedOutput += "****** Customer totals ******\r\n";
        expectedOutput += "*****************************\r\n";
        expectedOutput += "name: " + dummyName + "\r\n";
        expectedOutput += "amount: " + String.valueOf(amount) + "\r\n";

        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void should_printOwing_given_multiple_orders() {
        final double amount_1 = 25.5;
        final double amount_2 = 60.0;
        Order order_1 = new Order(amount_1);
        Order order_2 = new Order(amount_2);
        owingPrinter.printOwing(dummyName, Arrays.asList(order_1, order_2));

        String expectedOutput = "";
        expectedOutput += "*****************************\r\n";
        expectedOutput += "****** Customer totals ******\r\n";
        expectedOutput += "*****************************\r\n";
        expectedOutput += "name: " + dummyName + "\r\n";
        expectedOutput += "amount: " + String.valueOf(amount_1 + amount_2) + "\r\n";

        assertEquals(expectedOutput, outContent.toString());

    }
}