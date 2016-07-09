package db_tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接类
 * */
public class DBConnection {
	
	private static DBConnection DB = null;
	public static synchronized DBConnection  getInstance(){
		if(DB == null){
			DB = new DBConnection();
		}
		return DB;
	}
	
	//数据库: SQL Server
	//端口: 1433
	//数据库名: POS_DB
	private final static String url = "jdbc:sqlserver://localhost:1433;databaseName=POS_DB"; 
	
	private final static String user = "sa"; 			//用户名
	private final static String password = "123456"; 		//密码
		
	private Connection connection = null;		
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	
	private  DBConnection(){
		//加载SQL Server的JDBC的驱动 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库驱动加载失败");
			e.printStackTrace();
		}
		
		//创建连接
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * @return Statement
	 * */
	public Statement getStatement() throws SQLException{
		statement = connection.createStatement();
		return statement;
	}
	
	/**
	 * @param sql 要执行的SQL语句
	 * @return PreparedStatement
	 * */
	public PreparedStatement getPreparedStatement(String sql)
			throws SQLException{
		preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}
	
	/**
	 * 关闭Statement
	 * @param statement	自定义的Statement
	 * */
	public void close(Statement statement) throws SQLException{
		if(statement != null){
			statement.close();
		}
	}
	
	/**
	 * 关闭 PreparedStatement
	 * @param preparedStatement  自定义的PreparedStatement
	 * */
	public void close(PreparedStatement preparedStatement) throws SQLException{
		if(preparedStatement != null){
			preparedStatement.close();
		}
	}
	
	/**
	 * 关闭ResultSet、Statement
	 * @param resultSet 自定义的ResultSet
	 * @param statement 自定义的Statement
	 * */
	public void close(ResultSet resultSet,Statement statement) throws SQLException{
		if(resultSet != null){
			resultSet.close();
		}
		if(statement != null){
			statement.close();
		}
	}
	
	/**
	 * 关闭 PreparedStatement、ResultSet
	 * @param resultSet 自定义的ResultSet
	 * @param preparedStatement 自定义的PreparedStatement
	 * */
	public void close(ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException{
		if(resultSet != null){
			resultSet.close();
		}
		if(preparedStatement != null){
			preparedStatement.close();
		}
	}
}
