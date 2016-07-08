package tree_util;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;

/**
 * 添加、更新	测点
 * */
public class Op_MeasuringPoint extends ActionSupport {

	private String flag;			//区分操作
	
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
		String update = "update MeasuringPoint set Longitude='" + Longitude + "',Latitude='" + Latitude
				+ "' where ID='" + ID + "' and ID_MeasuringLine='" + ID_MeasuringLine + "'";
		
		db_connection = new DBConnection();
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
		String insert = "insert into MeasuringPoint values(?,?,?,?)";
		String childID = "PNT" + TimeFormat.getNowTime();
		
		db_connection = new DBConnection();
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
	
	private void delete(){
		String delete = "delete from MeasuringPoint where ID='" + ID 
				+ "' and ID_MeasuringLine='" + ID_MeasuringLine + "'";
		
		db_connection = new DBConnection();
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			statement.executeUpdate(delete);
			writer.write("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("测点删除失败");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("测点删除操作关闭失败");
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
