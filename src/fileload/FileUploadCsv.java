package fileload;

import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
//import com.csvreader.CsvReader;

import db_tool.DbBean;
 
public class FileUploadCsv {  
	 /** 
	 * 读取CSV文件 
	 * @return 
	 */  
	 public  int  readCsv(String inputPath,String inputName,String inputContentType,String tableName){  
	 //public void  readCsv(){ 
		 DbBean db=new DbBean();
	     try {   
	    	 ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据 
	    	 int[] csvlinenum = new int[500];
	         String csvFilePath = inputPath+"\\"+inputName;  
	         System.out.println("路径"+csvFilePath);
	         //String csvFilePath = "D://Base_MonitPersonel2.csv"; 
//	          CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("gb2312"));    //一般用这编码读就可以了                   
//	          while(reader.readRecord()){ //逐行读入数据      
//	              csvList.add(reader.getValues());  
//	          }       
//	          reader.close();  
	          //csvchange.add(csvList.get(0));
	          String[] cell = csvList.get(0);
	          int line=cell.length;//导入表列数
	          System.out.println("列数："+line);          
	          String sql="select * from "+tableName+"";
	          ResultSet f1 = null;
	          f1 = db.executeQuery(sql);
	          ResultSetMetaData num=f1.getMetaData();
	    	  int linenum=num.getColumnCount();//数据库表列数
	    	  ArrayList<String> list=new ArrayList<String>();
	          for(int i=1;i<=linenum;i++){//取表的列名
					list.add(num.getColumnName(i));
					System.out.print(list.get(i-1)+" ");
				}
	           for(int i=1,a=0;i<linenum;i++)//排序
	           {
	        	   System.out.println("进行排序");
	        	   for(int t=0;t<line;t++)
	        	   {
	        		   if(list.get(i).equals(cell[t]))
	        		   {
	        			   System.out.println("t:"+t);
	        			   csvlinenum[a]=t;//列号
	        			   a++;
	        			   break;
	        		   }
	        	   }	        	   
	           } 
	          // System.out.print("输出排序后列名：");
	           for(int r=1;r<csvList.size();r++)
	           {
	        	   sql="insert into "+tableName+"(";
	        	   for(int t=0;t<line;t++)
	        	   {
	        		   if(t!=line-1)
	        		   {
	        			   sql=sql+csvList.get(0)[csvlinenum[t]]+",";
	        		   }
	        		   else 
	        		   {
	        			   sql=sql+csvList.get(0)[csvlinenum[t]]+") values (";
	        		   }
	        	   }
	        	   //System.out.println("sql:"+sql);
	        	   for(int i=0;i<line;i++)
	        	   {
	        		   if(i!=line-1)
	        		   {
		        		   sql=sql+"'"+csvList.get(r)[csvlinenum[i]]+"'"+",";
	        		   }
	        		   else 
	        		   {
	        			   sql=sql+"'"+csvList.get(r)[csvlinenum[i]]+"'"+")";
	        		   }
	        	   }
	        	   System.out.println(sql);
	        	   db.executeUpdate(sql);
	           }   
	           db.close();
	           return 1;
	     }catch(Exception e){  
	          db.close();
	          System.out.println(e);
	          return -1;
	     }  
	 } 
}