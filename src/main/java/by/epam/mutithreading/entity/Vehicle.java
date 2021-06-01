package main.java.by.epam.mutithreading.entity;

import main.java.by.epam.mutithreading.state.VehicleStateType;

import java.util.Random;

public class Vehicle extends Thread {
    private VehicleStateType state;
    private String name;
    private Random random = new Random();
    private double square;
    private double weight;

    public Vehicle(int name) {
        this.name = "Vehicle " + name;
        this.state = VehicleStateType.READY;
        this.square = random.nextDouble() + 3.9;
        this.weight = random.nextDouble() + 1.4;
    }

    public double getSquare() {
        return square;
    }


    public String getVehicleName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getCarState() {
        return state.toString();
    }

    public void setState(VehicleStateType state) {
        this.state = state;
    }

    public void run() {
        Ferry.getInstance().onBoarding(this);
    }
}
