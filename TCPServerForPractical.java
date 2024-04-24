import java.io.*;
import java.net.*;

class TCPServerForPractical {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String clientInput = inFromClient.readLine();
            //int num = Integer.parseInt(clientInput);
            int[] arr = parseIntArray(clientInput);
            //int truthVal = calcArmstrong(num);
            //int truthVal = palindromeOrNot(num);
            //int truthVal = reverseInteger(num);
            int truthVal = maxFromArray(arr);
            System.out.println("Received: " + arr);
            String valToShow = Integer.toString(truthVal) +'\n';
            outToClient.writeBytes(valToShow + " ");
            //if(truthVal == 1)
            //outToClient.writeBytes(valToShow + " means true ");
            //else
            //outToClient.writeBytes(valToShow + " means false ");
        }
    }

    public static int calcArmstrong(int num) {
        int temp = num;
        int sum = 0;
        while (num != 0) {
            int rem = num % 10;
            sum += (rem * rem * rem);
            num /= 10;
        }
        if (temp == sum) return 1;
        else return 0;
    }
    public static int palindromeOrNot(int num){
    	if(num < 0) return 0;
    	int sum = 0;
    	int rem;
    	int temp = num;
    	while(num != 0){
    	    rem = num % 10;
    	    sum = (sum*10) + rem;
    	    num /= 10;
    	}
    	if(temp == sum) return 1;
    	else return 0;
    }
    public static int reverseInteger(int num){
    	if (num < 0) {
        num = -num;
    	}
    	int sum = 0;
    	int rem;
    	while(num != 0){
    	    rem = num % 10;
    	    sum = (sum*10) + rem;
    	    num /= 10;
    	}
    	return sum;
    }
    public static int maxFromArray(int[] arr){
    	if(arr.length < 2) return arr[0];
    	int max = Integer.MIN_VALUE;
    	for(int i = 0; i< arr.length; i++){
    	    max = Math.max(arr[i], max);
    	}
    	return max;
    }
    public static int[] parseIntArray(String input) {
    // Split the input string by space or comma (assuming integers are separated by space or comma)
    	String[] parts = input.split("[,\\s]+");
    
    // Initialize an array to store the parsed integers
    	int[] result = new int[parts.length];
    
    // Parse each substring into an integer and store it in the array
	for (int i = 0; i < parts.length; i++) {
	    result[i] = Integer.parseInt(parts[i]);
	}
        return result;
    }
}
