package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import db_tool.GetSqlSession;
import db_tool.User;

@SuppressWarnings("serial")
public class UserLogin extends ActionSupport{
	
	String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	//将用户以及用户权限保存到session中
	public String login() throws IOException{
		Map<String, Object> attribute=ActionContext.getContext().getSession();
		SqlSession sqlSession = GetSqlSession.getSqlSession();
		System.out.println("Login user:"+username);
		List<String> UserRoleRightlist = sqlSession.selectList("fishSqlMapper.getRoleRight",username);
		Set<String> userRoleSet = new HashSet<String>(UserRoleRightlist);
		User user = sqlSession.selectOne("fishSqlMapper.getUserByPk",username);
		
		attribute.put("user", user);
		attribute.put("roleRight", userRoleSet);
		
		ServletActionContext.getRequest().getSession().setAttribute("userName",username);
		
		sqlSession.commit();
		sqlSession.close();
		return SUCCESS;
	}
}
