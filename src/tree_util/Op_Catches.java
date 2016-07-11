package tree_util;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.collections.Predicate;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.TimeFormat;

/**
 * 添加、更新、删除	渔获物
 * */
public class Op_Catches extends ActionSupport {

	private String flag;		//区分操作
	
	private final static String START = "CTH";				//主键、照片 开头
	
	private static String PATH = "";					//照片在服务器上的路径("|"隔开)
	
	private String SampleID;
	private String Photo;
	private String Name;
	private String TotalQuality;
	private String EggQuality;
	private String FryQuality;
	private String ID_WaterLayer;			//外键
	
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
		//获取照片路径
		PATH = UpLoadPicture.upload(Photo, START);
		
		String update = "update Catches set Name='" + Name + "',Photo='"
				+ PATH + "',TotalQuality='" + TotalQuality + "',EggQuality='"
				+ EggQuality + "',FryQuality='" + FryQuality + "',ID_WaterLayer='"
				+ ID_WaterLayer + "' where SampleID='" + SampleID + "'";
		
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
			System.out.println("--渔获物更新失败--");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("渔获物更新操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void insert(){
		//获取照片路径
		PATH = UpLoadPicture.upload(Photo, START);
		
		String insert = "insert into Catches values(?,?,?,?,?,?,?)";
		String childID = START + TimeFormat.getNowTime();
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, childID);
			preparedStatement.setString(2, Name);
			preparedStatement.setString(3, PATH);
			preparedStatement.setString(4, TotalQuality);
			preparedStatement.setString(5, EggQuality);
			preparedStatement.setString(6, FryQuality);
			preparedStatement.setString(7, ID_WaterLayer);
			
			preparedStatement.executeUpdate();
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--渔获物插入错误--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("渔获物插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTotalQuality() {
		return TotalQuality;
	}
	public void setTotalQuality(String totalQuality) {
		TotalQuality = totalQuality;
	}
	public String getEggQuality() {
		return EggQuality;
	}
	public void setEggQuality(String eggQuality) {
		EggQuality = eggQuality;
	}
	public String getFryQuality() {
		return FryQuality;
	}
	public void setFryQuality(String fryQuality) {
		FryQuality = fryQuality;
	}
	public String getID_WaterLayer() {
		return ID_WaterLayer;
	}
	public void setID_WaterLayer(String iD_WaterLayer) {
		ID_WaterLayer = iD_WaterLayer;
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

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}
	
}
