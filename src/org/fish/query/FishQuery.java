package org.fish.query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.util.SQL_Server_DbBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FishQuery extends ActionSupport {
		public String execute()throws Exception{
			int count;
			ArrayList fishData=new ArrayList();
			ArrayList colname=new ArrayList();
			Map attribute=ActionContext.getContext().getSession();
			String sql="";
			sql+="select * from Base_Fish";
			SQL_Server_DbBean ssd=new SQL_Server_DbBean();
			ResultSet rs=ssd.executeQuery(sql);
			count=rs.getMetaData().getColumnCount();
			for(int i=1;i<count+1;i++){
				colname.add(rs.getMetaData().getColumnName(i));
			}
			while(rs.next()){
				for(int i=1;i<count+1;i++){
					fishData.add(rs.getString(i));
				}
			}
			
			System.out.println("lie shu:"+count);
			System.out.println("data.length:"+fishData.size());
			attribute.put("count", count);
			attribute.put("colname", colname);
			attribute.put("fishData", fishData);
			return SUCCESS;
		}
}
