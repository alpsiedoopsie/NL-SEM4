import java.util.*;
import java.net.*;
import java.io.*;

public class UDPClient{
    public static void main(String args[]){
        try{

            byte[] sen=new byte[1024];
            byte[] rcv=new byte[1024];

            DatagramSocket dp=new DatagramSocket();
            InetAddress ia=InetAddress.getByName("localhost");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String s=br.readLine();
            sen=s.getBytes();
            DatagramPacket ddp=new DatagramPacket(sen,sen.length,ia,9999);

            dp.send(ddp);

            DatagramPacket acp=new DatagramPacket(rcv, rcv.length);
            dp.receive(acp);
            String len=new String(acp.getData(),0,acp.getLength());
            System.out.println(len);

        }
        catch(Exception e)
        { System.out.println(e); }
    }
}