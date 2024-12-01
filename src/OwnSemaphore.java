public class OwnSemaphore {
    private int value;

    public OwnSemaphore(int value, boolean b) {
        this.value = value;
    }

    public synchronized void acquire() throws InterruptedException {
        while (value <= 0) {// No count available, wait until notified.
            wait();
        }
        value--;// Decrement count as one is now acquired.
    }

    public synchronized boolean tryAcquire() {
        if (value > 0) {
            value--; // Acquire  if available.
            return true;
        }
        return false; // No  available, cannot acquire.
    }

    // Method to release
    public synchronized void release() {
        value++;   // Increment  as one is released.
        notifyAll(); // Notify waiting threads that  now available.
    }


}
