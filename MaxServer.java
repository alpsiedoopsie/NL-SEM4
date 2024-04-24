import java.io.*;
import java.net.*;

public class MaxServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999); // Create a server socket on port 9999
            System.out.println("Server listening on port 9999...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    
                    // Parse the input string into an array of integers
                    String[] numbersStr = inputLine.split(",");
                    int[] numbers = new int[numbersStr.length];
                    for (int i = 0; i < numbersStr.length; i++) {
                        numbers[i] = Integer.parseInt(numbersStr[i]);
                    }
                    
                    // Find the maximum value in the array
                    int max = numbers[0];
                    for (int i = 1; i < numbers.length; i++) {
                        if (numbers[i] > max) {
                            max = numbers[i];
                        }
                    }
                    
                    // Send the maximum value back to the client
                    out.println(max);
                }
                
                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
