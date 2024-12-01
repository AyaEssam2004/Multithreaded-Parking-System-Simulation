
# Multithreaded Parking System Simulation

## Description
This project simulates a multithreaded parking system using **semaphores** to manage parking spots across 3 gates. It demonstrates the parking lot management with vehicles arriving at different times and staying for specified durations. The program handles scenarios where parking spots are unavailable, and cars wait until a spot is free.

## Features
- **Parking Management**: Simulates a parking system with 4 available parking spots.
- **Multithreading**: Each car is represented as a thread, allowing for concurrent operations.
- **Gate Handling**: The system uses 3 gates, each serving a different car at a time.
- **Waiting Mechanism**: If no spots are available, cars wait in a queue until a spot becomes free.
- **Semaphore for Synchronization**: A custom semaphore implementation controls access to parking spots, ensuring that only one car occupies a spot at any time.

## Components
- **Car Class**: Represents each car, including its ID, gate, arrival time, parking duration, and waiting time.
- **Gate Class**: Represents a gate, with an atomic counter to track how many cars have been served by the gate.
- **OwnSemaphore Class**: A custom semaphore implementation to manage parking spot availability.
- **ParkingLot Class**: Manages the parking lot's parking spots, queues, and car arrivals/departures.
- **ParkingSimulation Class**: The main class that simulates the entire parking system by reading input, creating cars, and managing threads.

## How to Run
1. Clone the repository.
2. Ensure you have **Java 8 or higher** installed.
3. Create an **input.txt** file with the following format for each car:
   ```
   Gate Number, Car ID, Arrival Time (in seconds), Parking Duration (in seconds)
   ```
   Example:
   ```
   Gate 1, Car 101, 0, 5
   Gate 2, Car 102, 2, 6
   Gate 3, Car 103, 4, 3
   ```
4. Run the program using the following command:
   ```
   javac ParkingSimulation.java
   java ParkingSimulation
   ```
5. The simulation will print the car activities and a summary report at the end.

## Output Example
```
Car 101 from Gate 1 arrived at time 0
Car 102 from Gate 2 arrived at time 2
Car 101 from Gate 1 parked. (Parking Status: 1 spots occupied)
Car 102 from Gate 2 waiting for a spot.
Car 103 from Gate 3 arrived at time 4
Car 103 from Gate 3 waiting for a spot.
Car 101 from Gate 1 left after 5 units of time. (Parking Status: 0 spots occupied)
...
```

## Project Setup
1. Clone the repository:
   ```
   git clone https://github.com/AyaEssam2004/Multithreaded-Parking-System-Simulation.git
   ```
2. Navigate to the project folder and compile the Java files:
   ```
   javac *.java
   ```

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing
Feel free to fork the repository and submit pull requests. For any issues or suggestions, create a new issue in the GitHub repository.

