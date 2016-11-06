package tcp;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;  
  
public class client {  
	public Socket socket = null;  
    public BufferedReader br = null;  
    public PrintWriter pw = null; 
    
    public client(String ip,int num) throws UnknownHostException, IOException
    {
    	 socket = new Socket(ip, num); //Initialize the port at 8080 
    	 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//get message from server 
         pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));//send message to server
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException {    
        client cl = new client("127.0.0.1",8080);
    	
    	cl.tcpsend("hello ,just for test");
    	String ans = cl.tcprec();
    	
    }  
    
    public void tcpsend(String str)//send message
    {
    	try {  
            pw.println(str);
            pw.flush(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
    public String tcprec()//recieve message
    {
        
    	try {  
    		String str = br.readLine();
    		System.out.println(str);
    		return str;
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        }
    }
    
    
  
}  