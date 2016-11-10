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
  
    public static int PORT = 5000;
    public ServerSocket s = null;  
    public Socket socket = null;  
    public BufferedReader br = null;  
    public PrintWriter pw = null;
    public String clientinfo ="";
    public String orderinfo="";//后期要记得更新，要不然加一个用户info就被覆盖（我就是单纯的懒）
    String[] array = null;//clientinfo
    int[] num = new int[7];//save the num foe patient 
    public boolean flag = true;//to judge whether the client is on
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
        	for(int i =0;i < 7;i++) num[i] = 1;
        	try  
            {  
            	br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//get message from server 
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);//send message to server
                while(flag){
                	clientinfo = br.readLine(); 
                    System.out.println(clientinfo);
                	array = clientinfo.split("&");
                    if(array[0] .equals("ordernow")) {
                    	for(int i = 1;i < array.length-1;i++)
                    	{
                    		orderinfo += array[i]+"&";
                    	}
                    	orderinfo += array[array.length-1];
                    	tcpsend("num"+"&"+ num[0]);//又给自己挖了一个坑，回头记得填
                		num[0]++;
                    }else if(array[0].equals("query")){
                    	tcpsend("ordermes"+"&"+orderinfo);
                    }else if(array[0].equals("exit"))
                    {
                    	flag = false;
                    }
                }
                
                
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
                 System.out.println(str);
             } catch (Exception e) {  
                 e.printStackTrace();  
             }
        }
        
        public String tcprec()//get message
        {
    		try {
    			String str = br.readLine();
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