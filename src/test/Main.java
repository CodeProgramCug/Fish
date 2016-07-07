package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import db_tool.GetSqlSession;
import db_tool.User;



public class Main {
	public static void main(String args[]) throws SQLException, IOException{
		
		SqlSession sqlSession = GetSqlSession.getSqlSession();
		
		//查找一个by主键
		User listUserinfo = sqlSession.selectOne("fishSqlMapper.getUserByPk","张三");
		System.out.println(listUserinfo.getUsername());
		
		//查找全部
		List<User> listUserinfo1 = sqlSession.selectList("fishSqlMapper.getAllStaff","admin");
		for(int i = 0  ;i < listUserinfo1.size() ; i++ ){
			User useritem = listUserinfo1.get(i);
			System.out.println(useritem.getUsername()+
								useritem.getDescription()
					);
		}
		//当然还有其他的功能查找更多的东西和功能，但是目前先在暂时用到的这个功能，还有其他的比如插入数据，修改数据以及其他的东西，之后再慢慢添加各种功能，一定需确保的是每一次的修改都是有效的
		
		/*
		List<String> strlist = sqlSession.selectList("fishSqlMapper.getRoleRight","admin");
		for(int i = 0  ;i < strlist.size() ; i++ ){
			String useritem = strlist.get(i);
			System.out.println(useritem);
		}
		
		if(strlist.contains("Ben_read")){
			System.out.println("可以查找！");
		}
		*/
		
		sqlSession.commit();
		sqlSession.close();
	}
}
