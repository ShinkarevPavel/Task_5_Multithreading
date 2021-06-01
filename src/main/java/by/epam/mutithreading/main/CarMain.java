package main.java.by.epam.mutithreading.main;

import main.java.by.epam.mutithreading.entity.Vehicle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarMain {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 8; i++) {
            service.execute(new Thread(new Vehicle(i)));
        }
        service.shutdown();
    }
}
