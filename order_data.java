package sql;
import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

import java.lang.*;
public class order_data{
	String url = "jdbc:mysql://localhost:3306/test" ;    
    String username = "root" ;   
    String password = "123" ; 
   
    String[] column = {"����","�Ա�","ְλ","��ҽ����"};
    Connection connec = null;
    PreparedStatement ptmt = null;
    ResultSet res = null;
    
	public void connect(){
		try{ 
  	  		Class.forName("com.mysql.jdbc.Driver");//����������
  	  		connec = DriverManager.getConnection(url , username , password ) ;   
		}catch(Exception e){
			e.printStackTrace();
		}
    }

	public String query(String num) throws SQLException{//���ݶ����Ų�ѯ������Ϣ
		String sql = "" + " select * from order_data where ordernum=?"; 
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
		ptmt.setString(1,num);
	    res = ptmt.executeQuery();
	    String info = "";
        while(res.next()){info += res.getString(2);}
        if(info != "") return info;
        else return "no";
	}
	
	public void insert(String num,String info) throws SQLException{//�����µ�����
		String sql = "" + " insert into order_data values(?,?)"; 
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
		ptmt.setString(1,num);
		ptmt.setString(2, info);
	    ptmt.executeUpdate();     
	}
	
	public void delete(String num) throws SQLException
	{
		String sql = "" + " delete from order_data where ordernum=?"; 
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
		ptmt.setString(1,num);
	    ptmt.executeUpdate();  
	}
	
	public static void main (String[] args) throws SQLException{
		order_data test = new order_data();
		test.insert("11", "lalalllal");
	}
	public order_data()
	{
		connect();
	}
}
