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
 * 添加、更新、删除	 断面
 * */
public class Op_FractureSurface extends ActionSupport {

	private String flag;		//区分操作
	
	private String ID;			
	private String Position;
	private String Distance2Bank;
	private String ID_MonitoringSite;		//外键
	
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
		sql += "update FractureSurface set Position='" + Position + "',Distance2Bank='"
		+ Distance2Bank + "' where ID='" + ID + "' and ID_MonitoringSite='" 
		+ ID_MonitoringSite + "'";
		
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
			System.out.println("--断面更新失败--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("断面更新操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	private void insert(){
		String insert = "insert into FractureSurface values(?,?,?,?)";
		String childID = "SEC" + TimeFormat.getNowTime();
		
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
			preparedStatement.setString(2, Position);
			preparedStatement.setString(3, Distance2Bank);
			preparedStatement.setString(4, ID_MonitoringSite);
			
			preparedStatement.executeUpdate();
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--断面插入错误--");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("断面插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	private void delete(){
		String delete = "delete from FractureSurface where ID='" + ID 
				+ "' and ID_MonitoringSite='" + ID_MonitoringSite + "'";
		
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
			System.out.println("断面删除错误");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("断面删除操作关闭失败");
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

	public String getID_MonitoringSite() {
		return ID_MonitoringSite;
	}

	public void setID_MonitoringSite(String iD_MonitoringSite) {
		ID_MonitoringSite = iD_MonitoringSite;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getDistance2Bank() {
		return Distance2Bank;
	}

	public void setDistance2Bank(String distance2Bank) {
		Distance2Bank = distance2Bank;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
