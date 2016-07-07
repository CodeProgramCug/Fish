package fishDataInput;

import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import db_tool.DbBean;

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
		
		Map<String, Object> attribute1=ActionContext.getContext().getSession();
		MonitPerson = (String) attribute1.get("userName");
		int count;
		ArrayList fishData=new ArrayList();
		ArrayList colname=new ArrayList();
		DbBean ssd=new DbBean();
		Map attribute=ActionContext.getContext().getSession();
		ResultSet rs=null;
		String sql="";
		//sql+="select * from Base_Fish where MonitPerson=..."
		if(Qtype.equals("1")){
			sql+="select * from Monit_Phy where MonitPerson='"+MonitPerson+"'";
			
		}else if(Qtype.equals("2")){
			sql+="select * from Monit_Zooplan where MonitPerson='"+MonitPerson+"'";
			
		}else if(Qtype.equals("3")){
			sql+="select * from Monit_Ben where MonitPerson='"+MonitPerson+"'";
		}
		System.out.println("sql22222:"+sql);
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
		attribute.put("Qtype", Qtype);
		return SUCCESS;
	}
}
