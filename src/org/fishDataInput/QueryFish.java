package org.fishDataInput;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.util.SQL_Server_DbBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryFish extends ActionSupport {
		private String MonitPerson;
		private String Qtype;
	public String getMonitPerson() {
			return MonitPerson;
		}
		public void setMonitPerson(String monitPerson) {
			MonitPerson = monitPerson;
		}
	public String getQtype() {
			return Qtype;
		}
		public void setQtype(String qtype) {
			Qtype = qtype;
		}
	public String execute()throws Exception{
		int count;
		ArrayList fishData=new ArrayList();
		ArrayList colname=new ArrayList();
		SQL_Server_DbBean ssd=new SQL_Server_DbBean();
		Map attribute=ActionContext.getContext().getSession();
		ResultSet rs=null;
		String sql="";
		
		//sql+="select * from Base_Fish where MonitPerson=..."
		if(Qtype.equals("1")){
			
			
		}else if(Qtype.equals("2")){
			
			
		}else if(Qtype.equals("3")){
			
		}
		
		/*     ----    */
		rs=ssd.executeQuery(sql);
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
