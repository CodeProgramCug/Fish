package db_tool;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserQuery extends ActionSupport{
	public String username=null;
	public Statement statement = null;
	public DBConnection user=null;
	public ResultSet resultSet = null;

	public String getData() throws Exception//按照用户姓名查询其子节点数据
	{
		try{
			this.user=DBConnection.getInstance();//建立连接
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		
		try
		{
			this.statement=this.user.getStatement();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="SELECT username FROM [Account] where superiorName='"+this.username+"'";
		System.out.println(sql);
		try
		{
		this.resultSet=this.statement.executeQuery(sql);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String user_name=null;
		String data="";
		try{
		while(this.resultSet.next())
		{
			user_name=resultSet.getString(1);
			/*String password=resultSet.getString(2)+":";
			String TelePhone=resultSet.getString(3)+":";
			String Email=resultSet.getString(4)+":";
			String Description=resultSet.getString(5)+":";
			String active=resultSet.getString(6)+":";
			String superior=resultSet.getString(7)+":";
			String roleName=resultSet.getString(8)+";"*/
			
			data+=user_name;
			data+=":";
			/*data+=password;
			data+=TelePhone;
			data+=Email;
			data+=Description;
			data+=active;
			data+=superior;
			data+=roleName;*/
		
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writer.write("error");
			return null;
		}
		System.out.println(data);
		data=data.replace("user:","");
		if(data.isEmpty())
		{
			writer.write("");
		}
		else if(data=="user")
		{
			writer.write("");
		}
		writer.write(data);
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String userQuery() throws Exception//按照用户姓名查询其子节点数据
	{
		try{
			this.user=DBConnection.getInstance();//建立连接
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		
		try
		{
			this.statement=this.user.getStatement();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="SELECT * FROM [Account] where username='"+this.username+"'";
		System.out.println(sql);
		try
		{
		this.resultSet=this.statement.executeQuery(sql);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String data="";
		try{
		while(this.resultSet.next())
		{
			String user_name=resultSet.getString(1)+":";
			String password=resultSet.getString(2)+":";
			String TelePhone=resultSet.getString(3)+":";
			String Email=resultSet.getString(4)+":";
			String Description=resultSet.getString(5)+":";
			String active=resultSet.getString(6)+":";
			String superior=resultSet.getString(7)+":";
			String roleName=resultSet.getString(8)+";";
			data+=user_name;
			data+=password;
			data+=TelePhone;
			data+=Email;
			data+=Description;
			data+=active;
			data+=superior;
			data+=roleName;
		
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writer.write("error");
			return null;
		}
		System.out.println(data);
		data=data.replace("user;","");
		
		writer.write(data);
		return null;
	}
	public void deleteQuery() throws Exception
	{
		
		try{
			this.user=DBConnection.getInstance();//建立连接
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		
		try
		{
			this.statement=this.user.getStatement();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean  result=false;
		String sql="delete FROM [Account] where username='"+this.username+"'";
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
}
