package main.java.by.epam.multithreading.entity;

import main.java.by.epam.multithreading.exception.VehicleException;
import main.java.by.epam.multithreading.state.VehicleStateType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Vehicle extends Thread {
    private static Logger logger = LogManager.getLogger();
    private VehicleStateType state;
    private String name;
    private double square;
    private double weight;

    public Vehicle(double square, double weight) {
        this.name = super.getName();
        this.state = VehicleStateType.READY;
        this.square = square;
        this.weight = weight;
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

        try {
            Ferry.getInstance().onBoarding(this);
        } catch (VehicleException e) {
            logger.error(e.getMessage());
        }
    }
}
