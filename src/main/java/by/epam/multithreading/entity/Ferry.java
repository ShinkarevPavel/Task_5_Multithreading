package main.java.by.epam.multithreading.entity;

import main.java.by.epam.multithreading.exception.VehicleException;
import main.java.by.epam.multithreading.state.VehicleStateType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Ferry {
    private static Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static Ferry ferry;
    private ReentrantLock locker = new ReentrantLock();
    private AtomicBoolean isCarrying = new AtomicBoolean(false);
    private Queue<Vehicle> vehicles = new ArrayDeque<>();
    private double square;
    private double carrying;

    private Ferry() {
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setCarrying(double carrying) {
        this.carrying = carrying;
    }

    public static Ferry getInstance() {
        while (ferry == null) {
            if (isInitialized.compareAndSet(false, true)) {
                ferry = new Ferry();
            }
        }
        return ferry;
    }

    public void onBoarding(Vehicle vehicle) throws VehicleException {
        try {
            locker.lock();
            checkCapacity(vehicle);
            if (isCarrying.get()) {
                vehicle.setState(VehicleStateType.ON_FERRY);
                vehicles.add(vehicle);
                logger.info(vehicle.getVehicleName() + " onboarded");
            } else {
                vehicle.setState(VehicleStateType.WAITING);
                logger.info(vehicle.getVehicleName() + " get " + vehicle.getCarState() + " state.");
                shipping();
                onBoarding(vehicle);
            }
        } catch (Exception e) {
            throw new VehicleException("Error with onboarding" + e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    private void shipping() {
        try {
            TimeUnit.SECONDS.sleep(3);
            for (Vehicle veh : vehicles) {
                veh.setState(VehicleStateType.MOVED);
                this.square += veh.getSquare();
                this.carrying += veh.getWeight();
                logger.info(veh.getVehicleName() + " get " + veh.getCarState() + " state.");
            }
            vehicles.clear();
            isCarrying.set(false);
            TimeUnit.SECONDS.sleep(3);
            logger.info("Ferry moving back");
        } catch (InterruptedException e) {
            logger.error("Issue with shipping " + e.getMessage());
        }
    }

    private void checkCapacity(Vehicle vehicle) {
        if (square >= vehicle.getSquare() && carrying >= vehicle.getWeight()) {
            this.square -= vehicle.getSquare();
            this.carrying -= vehicle.getWeight();
            isCarrying.set(true);
        } else {
            isCarrying.set(false);
        }
    }
}
