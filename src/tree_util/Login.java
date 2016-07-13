package tree_util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;

public class Login extends ActionSupport {

	private String username;
	private String password;
	
	private Account account = null;
	
	private List<String> userPower = null;		//用户权限
	
	private PrintWriter writer = null;
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try {
			writer = ServletActionContext.getResponse().getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = "select * from Account where username='" + username
				+ "' and passwold='" + password + "'";
		
		account = new Account();
		DBConnection db_connection = DBConnection.getInstance();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = db_connection.getStatement();
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				account.setID(resultSet.getString("id"));
				account.setUsername(username);
				account.setTelephone(resultSet.getString("Telephone"));
				account.setEmail(resultSet.getString("Email"));
				account.setDescription(resultSet.getString("Description"));
				account.setActive(resultSet.getString("active"));
				account.setSuperiorName(resultSet.getString("superiorName"));
				account.setRoleName(resultSet.getString("roleName"));
				System.out.println("存储成功");
			}
			
			//用户所有信息存放在session
			ServletActionContext.getRequest().getSession().setAttribute("account",account);
			
			System.out.println("----------------");
			System.out.println(account.getID());
			System.out.println(account.getUsername());
			System.out.println(account.getTelephone());
			System.out.println(account.getEmail());
			System.out.println(account.getDescription());
			System.out.println(account.getActive());
			System.out.println(account.getSuperiorName());
			System.out.println(account.getRoleName());
			System.out.println("----------------");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			e.printStackTrace();
			return ERROR;
		} finally{
			try {
				db_connection.close(resultSet,statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("成功跳转");
		return SUCCESS;
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
