import java.io.*;
import java.util.*;

public class ParkingSimulation {
    public static void main(String[] args) {
        int totalSpots = 4;
        ParkingLot parkingLot = new ParkingLot(totalSpots);
//gates is a HashMap that maps an integer (representing a gate number) to a Gate object.
        Map<Integer, Gate> gates = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            gates.put(i, new Gate(i));
        }

        List<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

               //gateNumber extracts the gate number from parts[0].
              //id extracts the car's ID from parts[1].
              //arrivalTime extracts the car's arrival time from parts[2].
              //parkingDuration extracts the car’s parking duration from parts[3].
                int gateNumber = Integer.parseInt(parts[0].substring(parts[0].lastIndexOf(" ") + 1));
                int id = Integer.parseInt(parts[1].substring(parts[1].lastIndexOf(" ") + 1));
                int arrivalTime = Integer.parseInt(parts[2].substring(parts[2].lastIndexOf(" ") + 1));
                int parkingDuration = Integer.parseInt(parts[3].substring(parts[3].lastIndexOf(" ") + 1));

                Gate gate = gates.get(gateNumber);
                if (gate != null) {//If the gate exists (gate != null), a new Car object is created with id, gate, arrivalTime, parkingDuration,
                    // and parkingLot. This car is then added to the cars list.
                    cars.add(new Car(id, gate, arrivalTime, parkingDuration, parkingLot));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return;
        }
      //An ArrayList of Thread objects is created to manage threads for each Car.
        List<Thread> threads = new ArrayList<>();
        for (Car car : cars) {
            Thread t = new Thread(car);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join(); //) waits for each thread to finish.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        parkingLot.printReport(new ArrayList<>(gates.values()));
    }
}
