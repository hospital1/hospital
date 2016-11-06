package tcp;

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
public class server extends Thread {  
  
    public static int PORT = 8080;
    public ServerSocket s = null;  
    public Socket socket = null;  
    public BufferedReader br = null;  
    public PrintWriter pw = null;
    
    public server() throws IOException
    {
    	s = new ServerSocket(PORT);  
   }
    
    public void run()  
    {  
  
        while (true)  
            {  
                System.out.println("Listenning...");  
                try  
                {  
                	//每个请求交给一个线程去处理  
                    socket = s.accept();  
                    ServerThread th = new ServerThread(socket);  
                    th.start();  
                    sleep(1000);  
                }  
                catch (Exception e)  
                {  
                    e.printStackTrace();  
                }  
                  
            }  
    }  
  
    
    class ServerThread extends Thread  
    {  
  
        Socket sk = null;  
        public ServerThread(Socket sk)  
        {  
            this.sk = sk;  
        }  
        public void run()  
        {  
            try  
            {  
            	br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//get message from server 
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);//send message to server
                String get = tcprec();  
                System.out.println(get);
                tcpsend("thread test");
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
              
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
        
        public String tcprec()//get message
        {
    		try {
    			String str = br.readLine();
    			System.out.println(str+socket.getPort());
        		return str;
    		} catch (IOException e) {
    			// TODO 自动生成的 catch 块
    			return null;
    		}
        }
     
          
    }  
    
    public static void main(String[] agrs) throws IOException {      
          new server().start();
    }  
    
    
    
    
    
}  