package main.java.by.epam.mutithreading.entity;

import main.java.by.epam.mutithreading.state.VehicleStateType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Ferry {
    private static Logger logger = LogManager.getLogger();
    private static final AtomicBoolean IS_INITIALISED = new AtomicBoolean(false);
    private ReentrantLock locker = new ReentrantLock();
    private static Ferry ferry;
    private AtomicBoolean isCarrying = new AtomicBoolean(false);
    private List<Vehicle> vehicles = new ArrayList<>();
    private double square;
    private double carrying;
    {
        square = 12.25;
        carrying = 4.5;
    }


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
            if (IS_INITIALISED.compareAndSet(false, true)) {
                ferry = new Ferry();
            }
        }
        return ferry;
    }

    public void onBoarding (Vehicle vehicle) {
        locker.lock();
        checkCapacity(vehicle);
        if (isCarrying.get()) {
            vehicle.setState(VehicleStateType.ON_FERRY);
            vehicles.add(vehicle);
            System.out.println(vehicle.getVehicleName() + " onboarding");
        } else {
            vehicle.setState(VehicleStateType.WAITING);
            System.out.println(vehicle.getVehicleName() + " " + vehicle.getCarState());
            shipping();
            onBoarding(vehicle);
        }
        locker.unlock();
    }
    private void shipping() {
        try {
            TimeUnit.SECONDS.sleep(3);
            for (Vehicle veh : vehicles) {
                veh.setState(VehicleStateType.MOVED);
                this.square += veh.getSquare();
                this.carrying += veh.getWeight();
                System.out.println(veh.getVehicleName() + " " + veh.getCarState() + " arrived. Ferry is empty");
            }
            vehicles.removeAll(vehicles.subList(0, vehicles.size()));
            isCarrying.set(false);
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Ferry moving back");
        }catch (InterruptedException e) {
            System.out.println();
        }
    }

    private void checkCapacity(Vehicle vehicle) {
        if (square >=  vehicle.getSquare()  && carrying  >=  vehicle.getWeight()) {
            this.square -= vehicle.getSquare();
            this.carrying -= vehicle.getWeight();
            isCarrying.set(true);
        } else {
            isCarrying.set(false);
        }
    }
}
