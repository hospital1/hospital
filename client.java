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
    	 socket = new Socket(ip, num); //Initialize socket
    	 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//get message from server 
         pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));//send message to server
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
    		return str;
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        }
    }
    
    
  
}  