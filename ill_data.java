package sql;
import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

import java.lang.*;
public class ill_data{
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
	
	public String query(String ill) throws SQLException{//���ݼ�����ѯ����
		String sql = "" + " select * from illdata " + "where illness=?"; 
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
		ptmt.setString(1,ill);
	    res = ptmt.executeQuery();
	    String depart = "";
	    String data = "";
        while(res.next()){
        	depart += res.getString(2) + "&" + res.getInt(3);
        	data = res.getString(2);
        	}
        if(depart != "" && data != "����ʵ") return depart;
        else return "no";
	}
	
	public void insert(String ill) throws SQLException{//�����µ�����
		String sql = "" + " insert into illdata(illness,department,num) values(?,?,?)"; 
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
		ptmt.setString(1,ill);
		ptmt.setString(2, "����ʵ");
		ptmt.setInt(3, 0);
	    ptmt.executeUpdate();     
	}
	
	public static void main (String[] args) throws SQLException{
		ill_data test = new ill_data();
		String de = test.query("xac");
		System.out.println(de);
	}
	public ill_data()
	{
		connect();
	}
}
