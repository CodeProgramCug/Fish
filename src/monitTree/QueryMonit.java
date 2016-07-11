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
		
		String sql = "SELECT InverstigationID FROM MonitoringSite";
		
		String result="";
		
		DBConnection dbConnection = DBConnection.getInstance();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = dbConnection.getStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			rs = statement.executeQuery(sql);
			while(rs.next()){
				result+=rs.getString(1)+",";
			}
			
			result = result.substring(0, result.length()-1);
			writer.write(result);
			
			System.out.println("monitSite ID result:"+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writer.write("error");
		} finally{
			try {
				dbConnection.close(rs,statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
		
	//获取当前结点的下一级结点
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
			
			if(result != "" && !result.equals("01") && !result.equals("00")){
				result = result.substring(0, result.length()-1);
			}
			
			writer.write(result);
			
		}catch(Exception e){
			writer.write(result += "00");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(rs, statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	//删除选的结点
	public String DeleteSelectMonit(){
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
				//先删除关联的第三张表
				/*String delete2 = "delete from WaterLayer_CatchTools where ID_CatchTools='"
						+ monitID + "'";
				statement.executeUpdate(delete2);
				System.out.println("@@@@关联的第三张表#######删除成功");*/
				
				//删除网具表的记录
				delete = "delete from CatchTools where SampleID='" + monitID + "'";
				
			}else if(mID.equals("CTH")){
				//删除渔获物
				delete = "delete from Catches where SampleID='" + monitID + "'";
				
			}else if(mID.equals("FSS")){
				//删除鱼样本
				delete = "delete from Fishes where SampleID='" + monitID + "'";
				
			}else if(mID.equals("FSE")){
				//删除卵样本
				delete = "delete from FishEggs where SampleID='" + monitID + "'";
				
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
		String sql="";
		
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
			
			rs = statement.executeQuery(sql);
			
			while(rs.next()){
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
					result+=rs.getString(i)+",";
			}
			
			result = result.substring(0,result.length()-1);
			
			writer.write(result);
			
			}catch(Exception e){
				writer.write("error");
				System.out.println(e);
			}finally{
				try {
					db_connection.close(rs, statement);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
