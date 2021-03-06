package tree_util;

import java.io.File;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.TimeFormat;

/**
 * 添加、更新、删除	网具
 * */
public class Op_CatchTools extends ActionSupport {

	private String flag;		//区分操作
	
	private final static String START = "NET";				//主键、照片 开头
	
	private static String PATH = "";					//照片在服务器上的路径("|"隔开)
	
	private String SampleID;
	private String Name;
	private String NetsModel;
	private String NetMouthArea;
	private String NetMouthDip;
	private String StartTime;
	private String EndTime;
	private String NetMouthVelocity;
	private String ID_WaterLayer;				//采样水层的ID,用于更新WaterLayer_CatchTools表
	
	private List<File> Photo;					//文件引用
	private List<String> PhotoFileName;			//文件名字
	private List<String> PhotoContentType;		//文件类型
	
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
		}else if(flag.equals("query")){
			//查询网具是否已有
			allTools();
		}
		return null;
	}
	
	private void update(){
		//获取照片路径
		PATH = UpLoadPicture.upload(Photo, PhotoFileName, START);
		
		String update = "update CatchTools set Name='" + Name + "',Photo='"
				+ PATH + "',NetsModel='" + NetsModel + "',NetMouthArea='"
				+ NetMouthArea + "',NetMouthDip='" + NetMouthDip + "',StartTime='"
				+ StartTime + "',EndTime='" + EndTime + "',NetMouthVelocity='"
				+ NetMouthVelocity + "' where SampleID='" + SampleID + "'";
		
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
			System.out.println("--网具更新失败--");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("网具更新操作关闭失败");
				e.printStackTrace();
			}
		}
	}
	
	private void insert(){
		String childID = START + TimeFormat.getNowTime();
		
		int flag1,flag2;		//标记两次操作是否成功
		flag1 = insertCatchTools(db_connection, childID);
		flag2 = insertWaterLayer_CatchTools(db_connection, childID);
		
		if(flag1 == 1 && flag2 == 2){
			writer.write(childID);
		}else{
			writer.write("error");
		}
		
	}
	//更新表CatchTools(插入)
	private int insertCatchTools(DBConnection db_connection,String childID){
		//获取照片路径
		PATH = UpLoadPicture.upload(Photo, PhotoFileName, START);
		
		String insert = "insert into CatchTools values(?,?,?,?,?,?,?,?,?)";	
		int m = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, childID);
			preparedStatement.setString(2, Name);
			preparedStatement.setString(3, PATH);
			preparedStatement.setString(4, NetsModel);
			preparedStatement.setString(5, NetMouthArea);
			preparedStatement.setString(6, NetMouthDip);
			preparedStatement.setString(7, StartTime);
			preparedStatement.setString(8, EndTime);
			preparedStatement.setString(9, NetMouthVelocity);
			
			preparedStatement.executeUpdate();
			m = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("1--网具插入失败");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("1--网具插入操作关闭失败");
				e.printStackTrace();
			}
		}
		return m;
	}
	//更新表WaterLayer_CatchTools(插入)
	private int insertWaterLayer_CatchTools(DBConnection db_connection,String childID){
		String insert = "insert into WaterLayer_CatchTools values(?,?)";
		
		PreparedStatement preparedStatement = null;
		int m = 0;
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, ID_WaterLayer);
			preparedStatement.setString(2, childID);
			
			preparedStatement.executeUpdate();
			m = 2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("2--网具插入失败");
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("2--网具插入操作关闭失败");
				e.printStackTrace();
			}
		}
		return m;
	}

	//查询网具是否已有
	private void allTools(){
		String query = "select * from WaterLayer_CatchTools where ID_WaterLayer='" + ID_WaterLayer + "'";
		
		int m = 0;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				m ++;
			}
			
			if(m == 1){
				writer.write("enough");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				db_connection.close(resultSet, statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	public String getNetsModel() {
		return NetsModel;
	}
	public void setNetsModel(String netsModel) {
		NetsModel = netsModel;
	}
	public String getNetMouthArea() {
		return NetMouthArea;
	}
	public void setNetMouthArea(String netMouthArea) {
		NetMouthArea = netMouthArea;
	}
	public String getNetMouthDip() {
		return NetMouthDip;
	}
	public void setNetMouthDip(String netMouthDip) {
		NetMouthDip = netMouthDip;
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
	public String getNetMouthVelocity() {
		return NetMouthVelocity;
	}
	public void setNetMouthVelocity(String netMouthVelocity) {
		NetMouthVelocity = netMouthVelocity;
	}

	public String getSampleID() {
		return SampleID;
	}

	public void setSampleID(String sampleID) {
		SampleID = sampleID;
	}

	public String getID_WaterLayer() {
		return ID_WaterLayer;
	}

	public void setID_WaterLayer(String iD_WaterLayer) {
		ID_WaterLayer = iD_WaterLayer;
	}

	public List<File> getPhoto() {
		return Photo;
	}

	public void setPhoto(List<File> photo) {
		Photo = photo;
	}

	public List<String> getPhotoFileName() {
		return PhotoFileName;
	}

	public void setPhotoFileName(List<String> photoFileName) {
		PhotoFileName = photoFileName;
	}

	public List<String> getPhotoContentType() {
		return PhotoContentType;
	}

	public void setPhotoContentType(List<String> photoContentType) {
		PhotoContentType = photoContentType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
