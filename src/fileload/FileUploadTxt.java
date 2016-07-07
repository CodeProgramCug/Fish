package fileload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import db_tool.DbBean;

public class FileUploadTxt {
   
    public int readTxt(String inputPath,String inputName,String inputContentType,String tableName){
    	DbBean db=new DbBean();
    	ArrayList<String[]> txtList = new ArrayList<String[]>();
    	String txtFilePath = inputPath+"\\"+inputName;  
	    System.out.println("路径"+txtFilePath);
        try {
        		int[] txtlinenum = new int[500];
                String encoding="GBK";
                File file=new File(txtFilePath);
                System.out.println("path ="+txtFilePath);
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String list=null;
                while((list=bufferedReader.readLine()) != null)
                {
            	   txtList.add(list.split("\t"));
                }
                read.close();
                /*for(int t=0;t<7;t++)
                {
                	for(int i=0;i<3;i++)
                	{
                		System.out.println(txtList.get(t)[i]);	
                	}
                }*/
                String[] cell = txtList.get(0);
                int line=cell.length;
                System.out.println("列数："+line);          
     	        String sql="select * from "+tableName+"";
     	        ResultSet f1 = null;
     	        f1 = db.executeQuery(sql);
     	        ResultSetMetaData num=f1.getMetaData();
     	    	int linenum=num.getColumnCount();//数据库表列数
     	    	ArrayList<String> List=new ArrayList<String>();
     	    	for(int i=1;i<=linenum;i++){//取表的列名
   					List.add(num.getColumnName(i));
   					System.out.print(List.get(i-1)+" ");
   				}
   	           for(int i=1,a=0;i<linenum;i++)//排序
   	           {
   	        	   //System.out.println("进行排序");
   	        	   for(int t=0;t<line;t++)
   	        	   {
   	        		   if(List.get(i).equals(cell[t]))
   	        		   {
   	        			   System.out.println("t:"+t);
   	        			   txtlinenum[a]=t;//列号
   	        			   a++;
   	        			   break;
   	        		   }
   	        	   }	        	   
   	           }
   	           for(int r=1;r<txtList.size();r++)
	           {
	        	   sql="insert into "+tableName+"(";
	        	   for(int t=0;t<line;t++)
	        	   {
	        		   if(t!=line-1)
	        		   {
	        			   sql=sql+txtList.get(0)[txtlinenum[t]]+",";
	        		   }
	        		   else 
	        		   {
	        			   sql=sql+txtList.get(0)[txtlinenum[t]]+") values (";
	        		   }
	        	   }
	        	   System.out.println("sql:"+sql);
	        	   for(int i=0;i<line;i++)
	        	   {
	        		   if(i!=line-1)
	        		   {
		        		   sql=sql+"'"+txtList.get(r)[txtlinenum[i]]+"'"+",";
	        		   }
	        		   else 
	        		   {
	        			   sql=sql+"'"+txtList.get(r)[txtlinenum[i]]+"'"+")";
	        		   }
	        	   }
	        	   System.out.println(sql);
	        	   db.executeUpdate(sql);
	           }
   	           db.close();
	           return 1;
        	} catch (Exception e) {
            e.printStackTrace();
            db.close();
 	          System.out.println(e);
 	          return -1;
        }
    }
}