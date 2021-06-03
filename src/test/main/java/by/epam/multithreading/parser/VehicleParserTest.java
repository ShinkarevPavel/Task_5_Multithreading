package main.java.by.epam.multithreading.parser;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class VehicleParserTest {
    private final String CORRECT_DATA = "2.2 4.8 1.9 2.0";
    private List<Double> expected;
    private VehicleParser vehicleParser;

    @BeforeTest
    public void before() {
        vehicleParser = new VehicleParser();
        expected = new ArrayList<>();
        expected.add(2.2);
        expected.add(4.8);
        expected.add(1.9);
        expected.add(2.0);
    }

    @Test
    public void testDataParser() {
        List<Double> actual = vehicleParser.dataParser(CORRECT_DATA);
        Assert.assertEquals(actual, expected);
    }

    @AfterTest
    public void after() {
        vehicleParser = null;
    }
}