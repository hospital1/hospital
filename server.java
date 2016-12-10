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
    public String orderinfo="";//����Ҫ�ǵø��£�Ҫ��Ȼ��һ���û�info�ͱ����ǣ��Ҿ��ǵ���������
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
                	//ÿ�����󽻸�һ���߳�ȥ����  
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
                    	num[docnum/10][docnum%10]++;//��ΪĬ��Ϊ0�������ȼӺ���
                    	
                    	if(num[docnum/10][docnum%10] <= 40){
                    		tcpsend("num&"+ num[docnum/10][docnum%10]+"&" +docnum+num[docnum/10][docnum%10]);//�ָ��Լ�����һ���ӣ���ͷ�ǵ���
                    		String ordernum = "" + docnum + num[docnum/10][docnum%10];
                    		order_data od = new order_data();
                    		od.insert(ordernum, orderinfo);
                    	}
                    	else tcpsend("no" + "&"+"��ǰҽʦ�Һ�����");
                    	
                    }else if(array[0].equals("query")){//������ѯ
                    	order_data od = new order_data();
                    	String res = od.query(array[1]);
                    	if(res != "no") tcpsend("ordermes"+"&"+res);
                    	else tcpsend("ordernull&" + "δ��ѯ���ö�����Ϣ");
                    }else if(array[0].equals("exit")){//�˳�
                    	flag = false;
                    }else if(array[0].equals("querybyillness")){//�������ѯ
                    	ill_data querybyillness = new ill_data();
                    	String depart = querybyillness.query(array[1]);
                    	if(depart != "no"){
                    		String[] deres = depart.split("&");
                    		tcpsend("departok&" + deres[0] + "&" + deres[1]);//������+ ���Һ�
                    	}else{
                    		querybyillness.insert(array[1]);
                    		tcpsend("departno&δ�鵽�ò����¼");
                    	}
                    	
                    
                    }else if(array[0].equals("cancel")){
                    	String ordernum = array[1];
                    	order_data od = new order_data();
                    	od.delete(ordernum);//ɾ��������Ϣ
                    	int docnum = Integer.parseInt(array[1].substring(0,2));
                    	num[docnum/10][docnum%10]--;//�Һ�������
                    	tcpsend("deleteok&" +"ȡ���ɹ�");
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
    			// TODO �Զ����ɵ� catch ��
    			return null;
    		}
        }
     
          
    }  
    
    public static void main(String[] agrs) throws IOException {      
          new server().start();
    }  
    
    
    
    
    
}  