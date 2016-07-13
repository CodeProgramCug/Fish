package db_tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

public class NewUser {
	private String username;
	private String password;
	private String tel;
	private String email;
	private String identify;
	private String superior;
	private String roleName;
	private String status;
	public Statement statement = null;
	public DBConnection user=null;
	public ResultSet resultSet = null;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getSuperior() {
		return superior;
	}
	public void setSuperior(String superior) {
		this.superior = superior;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void newQuery() throws IOException
	{
		System.out.println("new");
		try{
			this.user=DBConnection.getInstance();//建立连接
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		boolean result=false;
		try
		{
			this.statement=this.user.getStatement();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="INSERT INTO [Account] (username,passwold,Telephone,Email,Description,active,superiorName,roleName)VALUES('"+this.username+"','"+this.password+"','"+this.tel+"','"+this.email+"','"+this.identify+"','"+this.status+"','"+this.superior+"','"+this.roleName+"')";
		System.out.println(sql);
		try
		{
		result=this.statement.execute(sql);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result)
		{
			writer.write("success");
		}
		else
		{
			writer.write("failure");
		}
	}
	public void updateQuery() throws IOException
	{
		
		System.out.println("in");
		try{
			this.user=DBConnection.getInstance();//建立连接
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		int result=0;
		try
		{
			this.statement=this.user.getStatement();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="update [Account] set username='"+this.username+"'",password="'"+this.password+"'",Email="'"+this.email+"'",Description="'"+this.identify+"'",active="'"+this.status+"'",superiorName="'"+this.superior+"'",roleName="'"+this.roleName+"'";
		System.out.println(sql);
		try
		{
		result=this.statement.executeUpdate(sql);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			writer.write("failure");
			e.printStackTrace();
			
		}
		if(result>=1)
		{
			writer.write("success");
		}
		else
		{
			writer.write("failure");
		}
	}
}
