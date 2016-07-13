package tree_util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;

/**
 * 检测用户是否存在,密码是否正确
 * */
public class Check extends ActionSupport {

	private String username;
	private String password;
	
	private DBConnection db_connection = null;
	private PrintWriter writer = null;
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		String query_username = "select username from Account";
		boolean query_flag = false;
		
		String check_password = "select * from Account where username='" + username
				+ "' and passwold='" + password + "'";
		boolean check_flag = false;
		
		db_connection = DBConnection.getInstance();
		try {
			writer = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = db_connection.getStatement();
			resultSet = statement.executeQuery(query_username);
			
			while (resultSet.next()) {
				query_flag = true;				//有用户
			}
			
			if(!query_flag){			//没有用户,直接返回
				writer.write("nouserErr");
				return null;
			}
			
			resultSet = statement.executeQuery(check_password);
			while (resultSet.next()) {
				check_flag = true;				//账户,密码正确
			}
			
			if(!check_flag){
				writer.write("passwdErr");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(resultSet,statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
