import java.util.*;
import java.util.concurrent.*;

class ParkingLot {
    private final OwnSemaphore parkingSpots;
    private final Queue<Car> waitingQueue;
    private int occupiedSpots = 0;
    private int totalCarsServed = 0;

    public ParkingLot(int totalSpots) {
        this.parkingSpots = new OwnSemaphore(totalSpots, true);
        this.waitingQueue = new LinkedList<>();
    }

    // Increment the number of occupied spots safely
    private synchronized void incrementOccupiedSpots() {
        occupiedSpots++;
    }

    // Decrement the number of occupied spots safely
    private synchronized void decrementOccupiedSpots() {
        occupiedSpots--;
    }

    // Get the current number of occupied spots safely
    private synchronized int getOccupiedSpots() {
        return occupiedSpots;
    }

    // Increment the total number of cars served safely
    private synchronized void incrementTotalCarsServed() {
        totalCarsServed++;
    }

    // Get the total number of cars served safely
    private synchronized int getTotalCarsServed() {
        return totalCarsServed;
    }

    // Method to attempt to park a car
    public synchronized void parkCar(Car car) throws InterruptedException {
        // If a parking spot is available, acquire it immediately
        if (parkingSpots.tryAcquire()) {
            incrementOccupiedSpots();
            System.out.println("Car " + car.getId() + " from Gate " + car.getGate().getId() + " parked. (Parking Status: " + getOccupiedSpots() + " spots occupied)");
            car.getGate().incrementCarsServed();
        } else {
            // If no spot is available, add car to waiting queue
            System.out.println("Car " + car.getId() + " from Gate " + car.getGate().getId() + " waiting for a spot.");
            waitingQueue.add(car);
            long startTime = System.currentTimeMillis();

            // Wait until a parking spot becomes available
            while (!parkingSpots.tryAcquire()) {
                wait();
            }

            // Once acquired, update the waiting time and remove from queue
            car.setWaitingTime((int) ((System.currentTimeMillis() - car.waitStartTime) / 1000));
            waitingQueue.remove(car);
            incrementOccupiedSpots();
            System.out.println("Car " + car.getId() + " from Gate " + car.getGate().getId() + " parked after waiting for " + car.getWaitingTime() + " units of time. (Parking Status: " + getOccupiedSpots() + " spots occupied)");
            car.getGate().incrementCarsServed();
        }
    }

    // Method to handle a car leaving the parking lot
    public synchronized void leaveCar(Car car) {
        decrementOccupiedSpots();
        incrementTotalCarsServed();
        parkingSpots.release(); // Release a parking spot

        System.out.println("Car " + car.getId() + " from Gate " + car.getGate().getId() + " left after " + car.getParkingDuration() + " units of time. (Parking Status: " + getOccupiedSpots() + " spots occupied)");
        notifyAll(); // Notify waiting cars that a spot may be available
    }

    // Method to print a summary report of the parking lot activity
    public void printReport(List<Gate> gates) {
        System.out.println("Total Cars Served: " + getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + getOccupiedSpots());

        // Print the number of cars served by each gate
        for (Gate gate : gates) {
            System.out.println("- Gate " + gate.getId() + " served " + gate.getCarsServed() + " cars.");
        }
    }
}
