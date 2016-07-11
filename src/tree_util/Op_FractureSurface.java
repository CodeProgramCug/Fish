package tree_util;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.DbBean;
import db_tool.TimeFormat;

/**
 * 添加、更新、删除	 断面
 * */
public class Op_FractureSurface extends ActionSupport {

	private String flag;		//区分操作
	
	private final static String START = "SEC";				//主键 开头
	
	private String ID;			
	private String Position;
	private String Distance2Bank;
	private String ID_MonitoringSite;
	
	private PrintWriter writer = null;
	
	private DBConnection db_connection = null;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
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
		String sql="";
		sql += "update FractureSurface set Position='" + Position + "',Distance2Bank='"
		+ Distance2Bank + "' where ID='" + ID + "'";
		
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
		
		System.out.println("Position---"+Position);
		System.out.println("Distance2Bank---"+Distance2Bank);
		String insert = "insert into FractureSurface values(?,?,?,?)";
		String childID = START + TimeFormat.getNowTime();
		
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
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
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

	public String getID_MonitoringSite() {
		return ID_MonitoringSite;
	}

	public void setID_MonitoringSite(String iD_MonitoringSite) {
		ID_MonitoringSite = iD_MonitoringSite;
	}
	
}
