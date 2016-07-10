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
 * 添加、更新、删除	卵样本
 * */
public class Op_FishEggs extends ActionSupport {

	private String flag;		//区分操作
	
	private final static String START = "FSE";				//主键、照片 开头
	
	private static String PATH = "";					//照片在服务器上的路径("|"隔开)
	
	private String SampleID;
	private String Period;
	private String Diameter;
	private String EMDiameter;
	private String PigmentProp;
	private String EmbryoProp;
	private String ID_Catches;
	
	private PrintWriter writer = null;
	
	private DBConnection db_connection = null;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		writer = ServletActionContext.getResponse().getWriter();
		db_connection = DBConnection.getInstance();
		
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
		return null;
	}
	
	private void update(){
		//获取照片路径
		//PATH = START + 
		
		String update = "update FishEggs set Photo='" + PATH + "',Period='" + Period
				+ "',Diameter='" + Diameter + "',EMDiameter='" + EMDiameter + "',PigmentProp='"
				+ PigmentProp + "',EmbryoProp='" + EmbryoProp + "' where SampleID='" + SampleID
				+ "' and ID_Catches='" + ID_Catches + "'";
		
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			statement.executeUpdate(update);
			writer.write("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--卵样本更新失败--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("卵样本更新操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void insert(){
		//获取照片路径
		//PATH = START +
		
		String insert = "insert into FishEggs values(?,?,?,?,?,?,?,?)";
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
			preparedStatement.setString(2, PATH);
			preparedStatement.setString(3, Period);
			preparedStatement.setString(4, Diameter);
			preparedStatement.setString(5, EMDiameter);
			preparedStatement.setString(6, PigmentProp);
			preparedStatement.setString(7, EmbryoProp);
			preparedStatement.setString(8, ID_Catches);
			
			preparedStatement.executeUpdate();
			
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--卵样本插入错误--");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("卵样本插入操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void delete(){
		String delete = "delete from FishEggs where SampleID='" + SampleID
				+ "' and ID_Catches='" + ID_Catches + "'";
		
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
			System.out.println("卵样本删除错误");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("卵样本删除操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	public String getPeriod() {
		return Period;
	}
	public void setPeriod(String period) {
		Period = period;
	}
	public String getDiameter() {
		return Diameter;
	}
	public void setDiameter(String diameter) {
		Diameter = diameter;
	}
	public String getEMDiameter() {
		return EMDiameter;
	}
	public void setEMDiameter(String eMDiameter) {
		EMDiameter = eMDiameter;
	}
	public String getPigmentProp() {
		return PigmentProp;
	}
	public void setPigmentProp(String pigmentProp) {
		PigmentProp = pigmentProp;
	}
	public String getEmbryoProp() {
		return EmbryoProp;
	}
	public void setEmbryoProp(String embryoProp) {
		EmbryoProp = embryoProp;
	}
	public String getID_Catches() {
		return ID_Catches;
	}
	public void setID_Catches(String iD_Catches) {
		ID_Catches = iD_Catches;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSampleID() {
		return SampleID;
	}

	public void setSampleID(String sampleID) {
		SampleID = sampleID;
	}
	
}
