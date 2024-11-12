import java.util.Scanner;

class TravelingSalesman {
    private int numCities; // Number of cities
    private int[][] costMatrix; // Cost matrix
    private int bestCost; // Best cost found
    private int[] bestPath; // Best path found

    public TravelingSalesman(int numCities) {
        this.numCities = numCities;
        this.costMatrix = new int[numCities][numCities]; // Initialize cost matrix
        this.bestCost = Integer.MAX_VALUE; // Start with max value
        this.bestPath = new int[numCities + 1]; // To store the best path
    }

    // Function to read the cost matrix from user input
    public void readCostMatrix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                costMatrix[i][j] = sc.nextInt(); // Fill the cost matrix
            }
        }
    }

    // Solve the TSP problem
    public void solve() {
        boolean[] visited = new boolean[numCities]; // Track visited cities
        visited[0] = true; // Start from the first city
        findPath(0, 1, 0, visited); // Start finding paths
    }

    // Recursive function to find the best path
    private void findPath(int currentCity, int count, int cost, boolean[] visited) {
        // If all cities are visited
        if (count == numCities) {
            cost += costMatrix[currentCity][0]; // Return to the start city
            if (cost < bestCost) { // Check if it's the best cost
                bestCost = cost; // Update best cost
                updateBestPath(visited); // Update best path
            }
            return;
        }

        // Explore unvisited cities
        for (int nextCity = 0; nextCity < numCities; nextCity++) {
            if (!visited[nextCity]) { // If the city is not visited
                visited[nextCity] = true; // Mark as visited
                findPath(nextCity, count + 1, cost + costMatrix[currentCity][nextCity], visited); // Recursive call
                visited[nextCity] = false; // Backtrack
            }
        }
    }

    // Update the best path found
    private void updateBestPath(boolean[] visited) {
        for (int i = 0; i < numCities; i++) {
            bestPath[i] = visited[i] ? i + 1 : 0; // Store the path
        }
        bestPath[numCities] = 1; // Return to the starting city
    }

    // Print the best path and cost
    public void printResult() {
        System.out.print("Best Path: ");
        for (int city : bestPath) {
            System.out.print(city + " "); // Print the best path
        }
        System.out.println("\nTotal Cost: " + bestCost); // Print total cost
    }
}

public class Assignment10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of cities: ");
        int numCities = sc.nextInt(); // Input number of cities

        TravelingSalesman tsp = new TravelingSalesman(numCities); // Create instance
        tsp.readCostMatrix(); // Read cost matrix from user
        tsp.solve(); // Solve the TSP
        tsp.printResult(); // Print the result

        sc.close(); // Close the scanner
    }
}
