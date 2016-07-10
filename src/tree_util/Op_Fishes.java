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
 * 添加、更新、删除	鱼样本
 * */
public class Op_Fishes extends ActionSupport {

	private String flag;		//区分操作
	
	private final static String START = "FSS";				//主键、照片 开头
	
	private static String PATH = "";					//照片在服务器上的路径("|"隔开)
	
	private String SampleID;
	private String Photo;
	private String BodyLength;
	private String Length;
	private String BodyWeight;
	private String Age;
	private String ID_Catches;			//外键
	
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
		
		String update = "update Fishes set Photo='" + PATH + "',BodyLength='"
				+ BodyLength + "',Length='" + Length + "',BodyWeight='" + BodyWeight
				+ "',Age='" + Age + "' where SampleID='" + SampleID + "'";
		
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
			System.out.println("--鱼样本更新失败--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("鱼样本更新操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	private void insert(){
		//获取照片路径
		PATH = UpLoadPicture.upload(Photo, START); 
		
		String insert = "insert into Fishes values(?,?,?,?,?,?,?)";
		String childID = START + TimeFormat.getNowTime();
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, childID);
			preparedStatement.setString(2, PATH);
			preparedStatement.setString(3, BodyLength);
			preparedStatement.setString(4, Length);
			preparedStatement.setString(5, BodyWeight);
			preparedStatement.setString(6, Age);
			preparedStatement.setString(7, ID_Catches);
			
			preparedStatement.executeUpdate();
			writer.write(childID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--鱼样本插入错误--");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("鱼样本插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getBodyLength() {
		return BodyLength;
	}
	public void setBodyLength(String bodyLength) {
		BodyLength = bodyLength;
	}
	public String getLength() {
		return Length;
	}
	public void setLength(String length) {
		Length = length;
	}
	public String getBodyWeight() {
		return BodyWeight;
	}
	public void setBodyWeight(String bodyWeight) {
		BodyWeight = bodyWeight;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
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
