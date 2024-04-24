package practie;

import java.io.*;
import java.net.*;

public class maxserver {
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
                    int num=Integer.parseInt(inputLine);
                    int factorial = 1;
                    boolean flag=true;
                    for(int i = 1; i <= num; i++){
                        if(num-(i*i)==0)
                        {
                           factorial=i; 
                           flag=false;
                           break;
                        }
                    }

                    // Send the maximum value back to the client
                    if(flag==false)
                    out.println("Square root of"+num+"is"+factorial);
                    else{
                        out.println("Not a square");
                    }
                }
                
                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
