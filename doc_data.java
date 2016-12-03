package sql;

import java.sql.*;
import com.mysql.jdbc.PreparedStatement;

public class doc_data{
	String url = "jdbc:mysql://localhost:3306/test" ;    
    String username = "root" ;   
    String password = "123" ;
    String[] column = {"姓名","性别","职位","从医经历"};
    Connection connec = null;
    PreparedStatement ptmt = null;
    ResultSet res = null;
    
	public void connect(){
		try{ 
  	  		Class.forName("com.mysql.jdbc.Driver");//驱动的名称
  	  		connec = DriverManager.getConnection(url , username , password ) ;   
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	public String query(int num) throws SQLException{
		String sql = "" + " select * from doctor " + "where num=" + num; 
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
	    res = ptmt.executeQuery();
	    String doctor_info = "";
        java.sql.ResultSetMetaData rsmd =res.getMetaData();
        while(res.next()){
       	 	for(int i = 1;i < 5;i++){
       	 		if(i != 1) doctor_info += column[i-1]+" : "+res.getString(i+1)+"\r\n";
       	 		else doctor_info += column[i-1]+" : "+res.getString(i+1)+'\t';
       		
       	 	}
        }
        return doctor_info;
	}
	
	public String doc_data(int num) throws SQLException
	{
		connect();
		String res = query(num);
   	    return res;
	}
}
