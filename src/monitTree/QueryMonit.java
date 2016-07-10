package monitTree;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.DbBean;

public class QueryMonit extends ActionSupport {
	private String monitID;
	
	//获取所有的监测点
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
		
	//获取下一级结点
	public String GetNextMonit() throws IOException{
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		
		String result = "";
		String sql = null;
		String mID = monitID.substring(0,3);
		
		System.out.println("monitID:"+monitID);
		System.out.println("monitName:"+mID);
		
		DBConnection db_connection = DBConnection.getInstance();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			if(monitID.equals("jjhh")){
				//搜索监测点
				sql = "select InverstigationID from MonitoringSite";

			}else if(mID.equals("MON")){
				//搜索断面
				sql = "select ID FROM FractureSurface where ID_MonitoringSite = '" + monitID + "'";
				
			}else if(mID.equals("SEC")){
				//搜索测线
				sql = "select ID FROM MeasuringLine where ID_FractureSurface = '"  +monitID + "'";
			
			}else if(mID.equals("LIN")){
				//搜索测点
				sql = "select ID from MeasuringPoint where ID_MeasuringLine='" + monitID + "'";
			
			}else if(mID.equals("PNT")){
				//搜索采样水层
				sql = "select ID from WaterLayer where ID_MeasuringPoint='" + monitID + "'";
				
			}
			
			rs = statement.executeQuery(sql);
			while(rs.next()){
				result+=rs.getString(1)+",";
			}
		
		}catch(Exception e){
			result += "00";
			writer.write(result);
			return null;
		}
		
		if(result != "" && !result.equals("01") && !result.equals("00")){
			result = result.substring(0, result.length()-1);
		}
		
		writer.write(result);
		return null;
	}
	
	//删除选的结点
	/*public String DeleteSelectMonit() throws IOException{
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
	}*/
	
	public String DeleteSelectMonit(){
		
		System.out.println("@@@@@--" + monitID);
		
		PrintWriter writer = null;
		try {
			writer = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//标记哪一个表
		String mID = monitID.substring(0,3);
		String delete = null;			//删除操作的SQL语句	
		
		DBConnection db_connection = DBConnection.getInstance();
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
			if(mID.equals("MON")){			
				//删除监测点
				delete = "delete from MonitoringSite where InverstigationID='" + monitID + "'";
			
			}else if(mID.equals("SEC")){
				//删除断面
				delete = "delete from FractureSurface where ID='" + monitID + "'";
				
			}else if(mID.equals("LIN")){
				//删除测线
				delete = "delete from MeasuringLine where ID='" + monitID + "'";
				
			}else if(mID.equals("PNT")){
				//删除测点
				delete = "delete from MeasuringPoint where ID='" + monitID + "'";
				
			}else if(mID.equals("WLE")){
				//删除采样水层
				delete = "delete from WaterLayer where ID='" + monitID + "'";
				
			}else if(mID.equals("NET")){
				//删除网具
				
			}else if(mID.equals("CTH")){
				//删除渔获物
				
			}else if(mID.equals("FSS")){
				//删除鱼样本
				
			}else if(mID.equals("FSE")){
				//删除卵样本
				
			}
			
			statement.executeUpdate(delete);
			writer.write("success");
			System.out.println("#######删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--DeleteSelectMonit()--" + "删除结点失败");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("--DeleteSelectMonit()--" + "关闭操作失败");
				e.printStackTrace();
			}
		}
		
		return null;
	}
	//获取每一个节点详细信息
	public String ShowMessage() throws IOException{
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		
		String mID=monitID.substring(0,3);
		String result="";
		DbBean ssd=new DbBean();
		String sql="";
		ResultSet rs=null;
		try{
			if(mID.equals("MON")){
				//监测点
				sql += "select * from MonitoringSite where InverstigationID='" + monitID + "'";	
				
			}else if(mID.equals("SEC")){
				//断面
				sql += "select * from FractureSurface where ID='" + monitID + "'";
				
			}else if(mID.equals("LIN")){
				//测线
				sql += "select * from MeasuringLine where ID='" + monitID + "'";
				
			}else if(mID.equals("PNT")){
				//测点
				sql += "select * from MeasuringPoint where ID='" + monitID + "'";
				
			}else if(mID.equals("WLE")){
				//采样水层
				sql += "select * from WaterLayer where ID='" + monitID + "'";
				
			}else if(mID.equals("NET")){
				//网具
				sql += "select * from CatchTools where SampleID='" + monitID + "'";
				
			}else if(mID.equals("CTH")){
				//渔获物
				sql += "select * from Catches where SampleID='" + monitID + "'";
				
			}else if(mID.equals("FSS")){
				//鱼样本
				sql += "select * from Fishes where SampleID='" + monitID + "'";
				
			}else if(mID.equals("FSE")){
				//卵样本
				sql += "select * from FishEggs where SampleID='" + monitID + "'";
				
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
	
	public String getMonitID() {
		return monitID;
	}

	public void setMonitID(String monitID) {
		this.monitID = monitID;
	}
}
