package tree_util;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.DbBean;

/**
 * 添加、更新、删除	 监测点
 * */
public class Op_MonitoringSite extends ActionSupport {

	private String flag;		//区分操作
	
	private String InverstigationID;				
	private String Institution;
	private String Investigator;
	private String InvestigationDate;
	private String Site;
	private String River;
	private String Photo;
	private String StartTime;
	private String EndTime;
	private String StartLongitude;
	private String StartLatitude;
	private String EndLongitude;
	private String EndLatitude;
	private String Weather;
	private String Temperature;
	
	private PrintWriter writer = null;
	
	private DBConnection db_connection = null;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		writer = ServletActionContext.getResponse().getWriter();
		
		if(flag.equals("insert")){
			//更新操作
			update();
		}else if(flag.equals("update")){
			//插入操作
			insert();
		}else if(flag.equals("delete")){
			//删除操作
			delete();
		}
		return SUCCESS;
	}
	
	private void update(){
		String sql = "";
		sql += "update MonitoringSite set Institution='" + Institution + "',Investigator='" + Investigator + "',InvestigationDate='" + InvestigationDate + "',Site='";
		sql += Site + "',River='" + River + "',Photo='" + Photo + "',StartTime='" + StartTime + "',EndTime='" + EndTime + "',StartLongitude='" + StartLongitude + "',StartLatitude='";
		sql += StartLatitude + "',EndLongitude='" + EndLongitude + "',EndLatitude='" + EndLatitude + "',Weather='" + Weather + "',Temperature='" + Temperature + "'";
		sql += " where InverstigationID='" + InverstigationID + "'";
		
		db_connection = new DBConnection();
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			statement.executeUpdate(sql);
			writer.write("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--监测点更新失败--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("监测点更新操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void insert(){
		String insert = "insert into MonitoringSite values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String childID = "MON" + TimeFormat.getNowTime();
		
		db_connection = new DBConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, childID);
			preparedStatement.setString(2, Institution);
			preparedStatement.setString(3, Investigator);
			preparedStatement.setString(4, InvestigationDate);
			preparedStatement.setString(5, Site);
			preparedStatement.setString(6, River);
			preparedStatement.setString(7, Photo);
			preparedStatement.setString(8, StartTime);
			preparedStatement.setString(9, EndTime);
			preparedStatement.setString(10, StartLongitude);
			preparedStatement.setString(11, StartLatitude);
			preparedStatement.setString(12, EndLongitude);
			preparedStatement.setString(13, EndLatitude);
			preparedStatement.setString(14, Weather);
			preparedStatement.setString(15, Temperature);
			
			preparedStatement.executeUpdate();
			
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--监测点插入错误--");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("监测点插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}

	private void delete(){
		String delete = "delete from MonitoringSite where InverstigationID='" + InverstigationID + "'";
		
		db_connection = new DBConnection();
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			statement.executeUpdate(delete);
			writer.write("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("监测点删除错误");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("监测点删除操作关闭失败");
				e.printStackTrace();
			}
		}
	}

	public String getInverstigationID() {
		return InverstigationID;
	}

	public void setInverstigationID(String inverstigationID) {
		InverstigationID = inverstigationID;
	}

	public String getInstitution() {
		return Institution;
	}

	public void setInstitution(String institution) {
		Institution = institution;
	}

	public String getInvestigator() {
		return Investigator;
	}

	public void setInvestigator(String investigator) {
		Investigator = investigator;
	}

	public String getInvestigationDate() {
		return InvestigationDate;
	}

	public void setInvestigationDate(String investigationDate) {
		InvestigationDate = investigationDate;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getRiver() {
		return River;
	}

	public void setRiver(String river) {
		River = river;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getStartLongitude() {
		return StartLongitude;
	}

	public void setStartLongitude(String startLongitude) {
		StartLongitude = startLongitude;
	}

	public String getStartLatitude() {
		return StartLatitude;
	}

	public void setStartLatitude(String startLatitude) {
		StartLatitude = startLatitude;
	}

	public String getEndLongitude() {
		return EndLongitude;
	}

	public void setEndLongitude(String endLongitude) {
		EndLongitude = endLongitude;
	}

	public String getEndLatitude() {
		return EndLatitude;
	}

	public void setEndLatitude(String endLatitude) {
		EndLatitude = endLatitude;
	}

	public String getWeather() {
		return Weather;
	}

	public void setWeather(String weather) {
		Weather = weather;
	}

	public String getTemperature() {
		return Temperature;
	}

	public void setTemperature(String temperature) {
		Temperature = temperature;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
