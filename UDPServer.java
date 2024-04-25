import java.util.*;
import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){
        try{
            byte[] sen=new byte[1024];
            byte[] rcv=new byte[1024];

            DatagramSocket ds=new DatagramSocket(9999);
            DatagramPacket dp=new DatagramPacket(rcv,rcv.length);
                    ds.receive(dp);
            String s=new String(dp.getData(),0,dp.getLength());
            InetAddress ia =dp.getAddress();
            int n=s.length();
            String str=Integer.toString(n);
            sen=str.getBytes();
            DatagramPacket se=new DatagramPacket(sen,sen.length,ia,dp.getPort());

            ds.send(se);
            ds.close();
        }
        catch(Exception e)
        { System.out.println(e); }
    }
}