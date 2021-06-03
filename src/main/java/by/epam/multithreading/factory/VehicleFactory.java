package main.java.by.epam.multithreading.factory;

import main.java.by.epam.multithreading.entity.Ferry;
import main.java.by.epam.multithreading.entity.Vehicle;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VehicleFactory {

    public void startProgram(List<Double> parameters) {
        Ferry.getInstance().setSquare(parameters.get(0));
        Ferry.getInstance().setCarrying(parameters.get(1));
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 2; i < parameters.size() - 1; i += 2) {
            service.execute(new Thread(new Vehicle(parameters.get(i), parameters.get(i + 1))));
        }
        service.shutdown();
    }
}
