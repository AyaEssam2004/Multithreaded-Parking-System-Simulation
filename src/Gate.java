
import java.util.concurrent.atomic.AtomicInteger;

class Gate {
    private final int id;
    //AtomicInteger is a thread-safe integer type provided by Java's java.util.concurrent.atomic package. It allows atomic operations on the integer, which is helpful in multi-threaded environments.
//prevent any possible race conditions when multiple threads access or modify the intege
// Thread-safe counter for the number of cars served by this gate, initialized to 0
    private final AtomicInteger carsServed = new AtomicInteger(0);
   // private int carsServed = 0;
    public Gate(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void incrementCarsServed() {
        carsServed.incrementAndGet();
    }

    public int getCarsServed() {
        return carsServed.get();
    }


//    // Synchronized method to ensure thread-safe increment
//    public synchronized void incrementCarsServed() {
//        carsServed++;
//    }
//
//    // Synchronized method to safely get the number of cars served
//    public synchronized int getCarsServed() {
//        return carsServed;
    }
