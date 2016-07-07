package monitTree;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DbBean;

public class QueryMonit extends ActionSupport {
	private String monitID;
		public String getMonitID() {
		return monitID;
	}

	public void setMonitID(String monitID) {
		this.monitID = monitID;
	}
		public String MonitSite() throws IOException{
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			String result="";
			DbBean ssd=new DbBean();
			String sql="SELECT InverstigationID FROM MonitoringSite";
			ResultSet rs=null;
			try {
				rs=ssd.executeQuery(sql);
				while(rs.next()){
					result+=rs.getString(1)+",";
				}
				result=result.substring(0, result.length()-1);
				System.out.println("monitSite ID result:"+result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				writer.write("error");
				return null;
			}
			writer.write(result);
			return null;
		}
		
	public String GetNextMonit() throws IOException{
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		String result="";
		String mID=monitID.substring(0,3);
		DbBean ssd=new DbBean();
		String sql="";
		ResultSet rs=null;
		System.out.println("monitID:"+monitID);
		System.out.println("monitName:"+mID);
		try{
		if(mID.equals("MON")){
			sql+="select ID FROM FractureSurface where ID_MonitoringSite = '"+monitID+"'";
			rs=ssd.executeQuery(sql);
			while(rs.next()){
				result+=rs.getString(1)+",";
			}
			//result=result.substring(0, result.length()-1);
		}else if(mID.equals("SEC")){
			sql+="select ID FROM MeasuringLine where ID_FractureSurface = '"+monitID+"'";
			rs=ssd.executeQuery(sql);
			while(rs.next()){
				result+=rs.getString(1)+",";
			}
			//result=result.substring(0, result.length()-1);
			
		}else if(mID.equals("LIN")){
			result+="01";
		}else if(monitID.equals("jjhh")){
			sql+="select InverstigationID from MonitoringSite";
			rs=ssd.executeQuery(sql);
			while(rs.next()){
				result+=rs.getString(1)+",";
			}
		}
		
		}catch(Exception e){
			result+="00";
			writer.write(result);
			return null;
		}
		if(result!=""&&!result.equals("01")&&!result.equals("00")){
			result=result.substring(0, result.length()-1);
		}
		writer.write(result);
		return null;
	}
	public String DeleteSelectMonit() throws IOException{
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		String mID=monitID.substring(0,3);
		DbBean ssd=new DbBean();
		String sql="";
		String sql2="";
		ResultSet rs=null;
		try{
			if(mID.equals("MON")){
				sql+="select ID from FractureSurface where ID_MonitoringSite='"+monitID+"'";
				rs=ssd.executeQuery(sql);
				while(rs.next()){
					sql2="delete from MeasuringLine where ID_FractureSurface='"+rs.getString(1)+"'";
					ssd.executeUpdate(sql2);
				}
				sql2="delete from FractureSurface where ID_MonitoringSite='"+monitID+"'";
				ssd.executeUpdate(sql2);
				sql2="delete from MonitoringSite where InverstigationID='"+monitID+"'";
			}else if(mID.equals("SEC")){
				sql+="delete from MeasuringLine where ID_FractureSurface ='"+monitID+"'";
				ssd.executeUpdate(sql);
				sql2+="delete from FractureSurface where ID='"+monitID+"'";
				ssd.executeUpdate(sql2);
			}else if(mID.equals("LIN")){
				sql+="delete from MeasuringLine where ID="+monitID+"'";
				ssd.executeQuery(sql);
			}else{
					writer.write("isnull");
					return null;
			}
			
			}catch(Exception e){
				System.out.println(e);
				writer.write("error");
				return null;
			}
			writer.write("success");
		return null;
	}
	public String ShowMessage() throws IOException{
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		String mID=monitID.substring(0,3);
		String result="";
		DbBean ssd=new DbBean();
		String sql="";
		ResultSet rs=null;
		try{
		if(mID.equals("MON")){
			sql+="select * from MonitoringSite where InverstigationID='"+monitID+"'";	
		}else if(mID.equals("SEC")){
			sql+="select * from FractureSurface where ID='"+monitID+"'";
		}else if(mID.equals("LIN")){
			sql+="select * from MeasuringLine where ID='"+monitID+"'";
		}
		rs=ssd.executeQuery(sql);
		while(rs.next()){
			for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				result+=rs.getString(i)+",";
		}
		result=result.substring(0,result.length()-1);
		writer.write(result);
		}catch(Exception e){
			writer.write("error");
			System.out.println(e);
		}
		
		return null;
	}
}
