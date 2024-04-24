import java.io.*;
import java.net.*;

public class MaxClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999); // Connect to the server
            
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.print("Enter numbers separated by commas: ");
            String userInputLine = userInput.readLine();
            
            out.println(userInputLine); // Send user input to the server
            
            String serverResponse = in.readLine(); // Receive response from the server
            System.out.println("Maximum value received from server: " + serverResponse);
            
            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
