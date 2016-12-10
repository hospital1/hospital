package tcp;

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;
import java.sql.SQLException;

import sql.ill_data;
import sql.order_data;  
public class server extends Thread {  
  
    public static int PORT = 5000;
    public ServerSocket s = null;  
    public Socket socket = null;  
    public BufferedReader br = null;  
    public PrintWriter pw = null;
    public String clientinfo ="";
    public String orderinfo="";//后期要记得更新，要不然加一个用户info就被覆盖（我就是单纯的懒）
    String[] array = null;//clientinfo
    int[][] num = new int[8][3];
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
        	try  
            {  
            	br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//get message from server 
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);//send message to server
                while(flag){
                	clientinfo = br.readLine(); 
                    System.out.println(clientinfo);
                	array = clientinfo.split("&");
                    if(array[0] .equals("ordernow")) {
                    	for(int i = 2;i < array.length-1;i++)
                    	{
                    		orderinfo += array[i]+"&";
                    	}
                    	orderinfo += array[array.length-1];
                    	int docnum = Integer.parseInt(array[1]);
                    	num[docnum/10][docnum%10]++;//因为默认为0，所以先加后传送
                    	
                    	if(num[docnum/10][docnum%10] <= 40){
                    		tcpsend("num&"+ num[docnum/10][docnum%10]+"&" +docnum+num[docnum/10][docnum%10]);//又给自己挖了一个坑，回头记得填
                    		String ordernum = "" + docnum + num[docnum/10][docnum%10];
                    		order_data od = new order_data();
                    		od.insert(ordernum, orderinfo);
                    	}
                    	else tcpsend("no" + "&"+"当前医师挂号已满");
                    	
                    }else if(array[0].equals("query")){//订单查询
                    	order_data od = new order_data();
                    	String res = od.query(array[1]);
                    	if(res != "no") tcpsend("ordermes"+"&"+res);
                    	else tcpsend("ordernull&" + "未查询到该订单信息");
                    }else if(array[0].equals("exit")){//退出
                    	flag = false;
                    }else if(array[0].equals("querybyillness")){//按病情查询
                    	ill_data querybyillness = new ill_data();
                    	String depart = querybyillness.query(array[1]);
                    	if(depart != "no"){
                    		String[] deres = depart.split("&");
                    		tcpsend("departok&" + deres[0] + "&" + deres[1]);//科室名+ 科室号
                    	}else{
                    		querybyillness.insert(array[1]);
                    		tcpsend("departno&未查到该病情记录");
                    	}
                    	
                    
                    }else if(array[0].equals("cancel")){
                    	String ordernum = array[1];
                    	order_data od = new order_data();
                    	od.delete(ordernum);//删除订单信息
                    	int docnum = Integer.parseInt(array[1].substring(0,2));
                    	num[docnum/10][docnum%10]--;//挂号数减少
                    	tcpsend("deleteok&" +"取消成功");
                    }
                }
                
                
            }  
            catch (IOException | SQLException e)  
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