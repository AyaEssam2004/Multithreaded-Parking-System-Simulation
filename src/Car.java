import java.util.concurrent.*;

class Car implements Runnable {
    private final int id;
    private final Gate gate;
    private final int arrivalTime;
    private final int parkingDuration;
    private final ParkingLot parkingLot;
    public long waitStartTime;
    private int waitingTime = 0;

    public Car(int id, Gate gate, int arrivalTime, int parkingDuration, ParkingLot parkingLot) {
        this.id = id;
        this.gate = gate;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
    }

    // Overridden run method for the car thread execution
    @Override
    public void run() {
        try {
            // Simulate car arrival by waiting for arrivalTime in seconds
            Thread.sleep(arrivalTime * 1000);
            // Start timing wait period as car arrives at the parking lot
             waitStartTime = System.currentTimeMillis(); //start timing wait period
            System.out.println("Car " + id + " from Gate " + gate.getId() + " arrived at time " + arrivalTime);
           // Try to park the car in the parking lot
            parkingLot.parkCar(this);
           // Car stays parked for the duration specified by parkingDuration
            Thread.sleep(parkingDuration * 1000);
            // Car leaves the parking lot after parking duration is over
            parkingLot.leaveCar(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getId() { return id; }
    public Gate getGate() { return gate; }
    public int getParkingDuration() { return parkingDuration; }
    public void setWaitingTime(int time) { this.waitingTime = time; }
    public int getWaitingTime() { return waitingTime; }
}
