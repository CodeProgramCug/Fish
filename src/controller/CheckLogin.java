package controller;

import java.io.PrintWriter;
import java.net.URLDecoder;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.GetSqlSession;
import db_tool.User;


public class CheckLogin extends ActionSupport{
	private String username,password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
	//如果写入说明用户名不正确
	public String execute() throws Exception{
		username = URLDecoder.decode(username,"utf-8");
		System.out.println("username:"+username);
		
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		SqlSession sqlSession = GetSqlSession.getSqlSession();
		User user =  sqlSession.selectOne("fishSqlMapper.getUserByPk", username);
		
		if(user == null){
			writer.write("nouserErr");
		}else if(!password.equals(user.getPasswold())){
			writer.write("passwdErr");
		}else{
			writer.write("success");
		}
		writer.flush();
		writer.close();
		sqlSession.commit();
		sqlSession.close();
		return null;
	}

}
