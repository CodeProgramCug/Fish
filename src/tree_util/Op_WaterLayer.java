package tree_util;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.DbBean;
import db_tool.TimeFormat;

/**
 * 添加、更新、删除 	采样水层
 * */
public class Op_WaterLayer extends ActionSupport {

	private String flag;		//区分操作
	
	private final static String START = "WLE";				//主键 开头
	
	private String ID;
	private String Layer;
	private String Depth;
	private String Temperature;
	private String WaterLevel;
	private String Velocity;
	private String ID_MeasuringPoint;			//外键
	
	private PrintWriter writer = null;
	
	private DBConnection db_connection = null;
	
	private Account account = null;
	
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
			//查询一个测点对应的采样水层总数操作
			querySum();
		}else if(flag.equals("get_all")){
			//获取所有的采样水层结点
			allWaterLayer();
		}
		return null;
	}
	 
	private void update(){
		String sql = "update WaterLayer set Layer='" + Layer + "',Depth='" + Depth
				+ "',Temperature='" + Temperature + "',WaterLevel='" + WaterLevel
				+ "',Velocity='" + Velocity + "' where ID='" + ID + "'";
		
		Statement statement = null;
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			statement.executeUpdate(sql);
			writer.write("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("采样水层插入失败");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("采样水层插入操作关闭失败");
				e.printStackTrace();
			}
		}
		
	}
	
	private void insert(){

		String insert = "insert into WaterLayer values(?,?,?,?,?,?,?)";
		String childID = START + TimeFormat.getNowTime();
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = db_connection.getPreparedStatement(insert);
			preparedStatement.setString(1, childID);
			preparedStatement.setString(2, Layer);
			preparedStatement.setString(3, Depth);
			preparedStatement.setString(4, Temperature);
			preparedStatement.setString(5, WaterLevel);
			preparedStatement.setString(6, Velocity);
			preparedStatement.setString(7, ID_MeasuringPoint);
			
			preparedStatement.executeUpdate();
			
			writer.write(childID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			System.out.println("--采样水层插入错误--");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("--采样水层插入操作关闭失败--");
				e.printStackTrace();
			}
		}
	}
	
	//获取所有的采样水层
	public String allWaterLayer(){
		//获取session中保存的用户信息
		account = (Account) ActionContext.getContext().getSession().get("account");
		
		String result = "";
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = db_connection.getStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			e.printStackTrace();
		}
		
		//从监测点一级一级通过外键定位到采样水层
		String query_Site = "select InverstigationID from MonitoringSite where user_id='" + account.getID() + "'";
		ResultSet res2 = null;
		ResultSet res3 = null;
		ResultSet res4 = null;
		ResultSet res5 = null;
		
		try {
			resultSet = statement.executeQuery(query_Site);
			while (resultSet.next()) {
				//断面
				String query_Suf = "select ID from FractureSurface where ID_MonitoringSite='" + resultSet.getString(1) + "'";
				res2 = statement.executeQuery(query_Suf);
				while (res2.next()) {
					//测线
					String query_Lin= "select ID from MeasuringLine where ID_FractureSurface='" + res2.getString(1) + "'";
					res3 = statement.executeQuery(query_Lin);
					while (res3.next()) {
						//测点
						String query_Poi = "select ID from MeasuringPoint where ID_MeasuringLine='" + res3.getString(1) + "'";
						res4 = statement.executeQuery(query_Poi);
						while (res4.next()) {
							//采样水层
							String query_Sur = "select ID from WaterLayer where ID_MeasuringPoint='" + res4.getString(1) + "'";
							res5 = statement.executeQuery(query_Sur);
							while (res5.next()) {
								result += res5.getString(1) + ",";
							}
						}
					}
				}
			}
			
			result = result.substring(0, result.length()-1);
			
			System.out.println("采样水层*****--" + result);
			if(result.equals("")){
				writer.write("no");
			}else{
				writer.write(result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("所有的采样水层查询错误");
			writer.write("error");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(res5,null);
				db_connection.close(res4,null);
				db_connection.close(res3,null);
				db_connection.close(res2,null);
				db_connection.close(resultSet,statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
		

	//查询采样水层总数(一对三关系)
	private void querySum(){
		int m = 0;				//标记有几条记录
		
		String query = "select * from WaterLayer where ID_MeasuringPoint='" 
						+ ID_MeasuringPoint + "'";
		
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
			while (resultSet.next()) {
				m++;
			}
			
			if(m == 3){
				writer.write("enough");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			writer.write("error");
			e.printStackTrace();
		}finally{
			try {
				db_connection.close(resultSet,statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String getLayer() {
		return Layer;
	}

	public void setLayer(String layer) {
		Layer = layer;
	}

	public String getDepth() {
		return Depth;
	}

	public void setDepth(String depth) {
		Depth = depth;
	}

	public String getTemperature() {
		return Temperature;
	}

	public void setTemperature(String temperature) {
		Temperature = temperature;
	}

	public String getWaterLevel() {
		return WaterLevel;
	}

	public void setWaterLevel(String waterLevel) {
		WaterLevel = waterLevel;
	}

	public String getVelocity() {
		return Velocity;
	}

	public void setVelocity(String velocity) {
		Velocity = velocity;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getID_MeasuringPoint() {
		return ID_MeasuringPoint;
	}

	public void setID_MeasuringPoint(String iD_MeasuringPoint) {
		ID_MeasuringPoint = iD_MeasuringPoint;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
