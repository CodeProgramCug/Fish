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
 * 添加、更新、删除	 测线
 * */
public class Op_MeasuringLine extends ActionSupport {

	private String flag;		//区分操作
	
	private String ID;			
	private String StartLongitude;
	private String StartLatitude;
	private String EndLongitude;
	private String EndLatitude;
	private String ID_FractureSurface;			//外键
	
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
		String sql="";
		sql+="update MeasuringLine set StartLongitude='" + StartLongitude
				+ "',StartLatitude='" + StartLatitude + "',EndLongitude='" + EndLongitude
				+ "',EndLatitude='" + EndLatitude + "' where ID='" + ID 
				+ "' and ID_FractureSurface='" + ID_FractureSurface + "'";
		
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
			System.out.println("--测线更新失败--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("测线更新操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void insert(){
		String insert = "insert into MeasuringLine values(?,?,?,?,?,?)";
		String childID = "LIN" + TimeFormat.getNowTime();
		
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
			preparedStatement.setString(2, StartLongitude);
			preparedStatement.setString(3, StartLatitude);
			preparedStatement.setString(4, EndLongitude);
			preparedStatement.setString(5, EndLatitude);
			preparedStatement.setString(6, ID_FractureSurface);
			
			preparedStatement.executeUpdate();
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--测线插入错误--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("测线插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	private void delete(){
		String delete = "delete from MeasuringLine where ID='" + ID 
				+ "' and ID_FractureSurface='" + ID_FractureSurface + "'";
		
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("测线删除失败");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("测线删除操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getID_FractureSurface() {
		return ID_FractureSurface;
	}

	public void setID_FractureSurface(String iD_FractureSurface) {
		ID_FractureSurface = iD_FractureSurface;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
