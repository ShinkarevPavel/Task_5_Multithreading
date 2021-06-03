package main.java.by.epam.multithreading.main;

import main.java.by.epam.multithreading.exception.VehicleException;
import main.java.by.epam.multithreading.factory.VehicleFactory;
import main.java.by.epam.multithreading.parser.VehicleParser;
import main.java.by.epam.multithreading.reader.VehicleReader;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class CarMain {
    public static void main(String[] args) {
        List<Double> par = new ArrayList<>();
        VehicleReader reader = new VehicleReader();
        VehicleParser parser = new VehicleParser();
//        try {
//            par = parser.dataParser(reader.parameterReader("data/data.txt"));
//        } catch (VehicleException e) {
//            System.out.println(e.getMessage());
//        }
        List<Double> ebobo = parser.dataParser("2.2 4.8 1.9 2.0");
        for (Double e : ebobo) {
            System.out.println(e);
        }

//        VehicleFactory vehicleFactory = new VehicleFactory();
//        vehicleFactory.startProgram(par);
    }
}
