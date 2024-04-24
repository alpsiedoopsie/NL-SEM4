import java.io.*;
import java.net.*;

public class PalindromeServer {
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
                    
                    // Check if the input string is a palindrome
                    String result = new StringBuilder(inputLine).reverse().toString().equals(inputLine) ? "Palindrome" : "Not a Palindrome";
                    
                    out.println(result); // Send the result back to the client
                }
                
                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
