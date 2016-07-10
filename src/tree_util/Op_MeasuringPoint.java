package tree_util;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.TimeFormat;

/**
 * 添加、更新、删除	测点
 * */
public class Op_MeasuringPoint extends ActionSupport {

	private String flag;			//区分操作
	
	private final static String START = "PNT";				//主键 开头
	
	private String ID;
	private String Longitude;
	private String Latitude;
	private String ID_MeasuringLine;		//外键	
	
	private PrintWriter writer = null;
	
	private DBConnection db_connection = null;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		writer = ServletActionContext.getResponse().getWriter();
		db_connection = DBConnection.getInstance();
		
		if(flag.equals("insert")){
			//插入操作
			insert();
		}else if(flag.equals("update")){
			//更新操作
			update();
		}
		return null;
	}
	
	private void update(){
		String update = "update MeasuringPoint set Longitude='" + Longitude + "',Latitude='" + Latitude
				+ "' where ID='" + ID + "'";
		
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			statement.executeUpdate(update);
			writer.write("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("测点更新失败");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("测点更新操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void insert(){
		
		System.out.println("测点ID_MeasuringLine--" + ID_MeasuringLine);
		System.out.println("测点Longitude--" + Longitude);
		System.out.println("测点Latitude--" + Latitude);
		
		String insert = "insert into MeasuringPoint values(?,?,?,?)";
		String childID = START + TimeFormat.getNowTime();
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, childID);
			preparedStatement.setString(2, Longitude);
			preparedStatement.setString(3, Latitude);
			preparedStatement.setString(4, ID_MeasuringLine);
			
			preparedStatement.executeUpdate();
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("测点插入失败");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("测点插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getID_MeasuringLine() {
		return ID_MeasuringLine;
	}

	public void setID_MeasuringLine(String iD_MeasuringLine) {
		ID_MeasuringLine = iD_MeasuringLine;
	}
	
}
