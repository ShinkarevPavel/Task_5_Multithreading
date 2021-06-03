package main.java.by.epam.multithreading.reader;

import main.java.by.epam.multithreading.exception.VehicleException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URI;

public class VehicleReaderTest {
    private final String CORRECT_PATH = "correctData.txt";
    private final String WRONG_PATH = "correctDate.txt";
    private final String EXPECTED_DATA = "12.25 5.50";
    ClassLoader classLoader;
    private VehicleReader vehicleReader;

    @BeforeTest
    public void before() {
        classLoader = ClassLoader.getSystemClassLoader();
        vehicleReader = new VehicleReader();
    }

    @Test
    public void testParameterReader() throws VehicleException {
        String actual = vehicleReader.parameterReader(CORRECT_PATH);
        Assert.assertEquals(actual, EXPECTED_DATA);
    }

    @Test (expectedExceptions = VehicleException.class)
    public void testExceptionReader() throws VehicleException{
        vehicleReader.parameterReader(WRONG_PATH);
    }

    @AfterTest
    public void after() {
        classLoader = null;
        vehicleReader = null;
    }
}