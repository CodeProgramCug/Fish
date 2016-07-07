package fileload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import db_tool.DbBean;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String inputPath;
	
	public String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String execute() throws Exception {
		DbBean db=new DbBean();	
		//System.out.println("文件名："+filename);
		String filename2=filename.split("\\.")[0];
		System.out.println("文件名："+filename2);
    	String sql="select * from "+filename2+"";
    	
    	ResultSet f1 = null; 
    	try{
    		f1 = db.executeQuery(sql);
    		ResultSetMetaData num=f1.getMetaData();
    		int linenum=num.getColumnCount();
    		ArrayList<String> list=new ArrayList<String>();
    		//System.out.println("列数："+linenum);
    		for(int i=1;i<=linenum;i++){
				list.add(num.getColumnName(i));
				//System.out.println(list.get(i-1));
			}
    		LinkedHashMap map = new LinkedHashMap();
    		//System.out.print("表头:");
    		for(int i=1;i<=linenum;i++)
    		{
    			String s=String.valueOf(i);
    			//System.out.print(list.get(i-1)+" ");
    			map.put(s, list.get(i-1));
    		}
    		//System.out.println();
    		List exportData = new ArrayList<Map>();
    		Map row1 = new LinkedHashMap<String, String>();
    		//System.out.println("内容：");
    		while(f1.next())
    		{
    			for(int s=1;s<=linenum;s++)
    			{
    				String li=String.valueOf(s);
    				row1.put(li, f1.getString(s));
    			}
    			exportData.add(row1);
    			row1 = new LinkedHashMap<String, String>();
    		}
    		String path = ServletActionContext.getServletContext().getRealPath("/textdownload");
    		String filepath = path+"\\";
    		System.out.println("路径："+filepath);
            FileDownload.createCSVFile(exportData, map, filepath, filename2);
    	}catch(Exception e){
			e.printStackTrace();
			db.close();
		}
		db.close();
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public InputStream getInputStream() throws IOException{
		String path = ServletActionContext.getServletContext().getRealPath("/textdownload");
		String filepath = path+"\\"+filename;
		//System.out.println("2");
		File file=new File(filepath);
		//System.out.println("文件路径："+filepath);
		return FileUtils.openInputStream(file);
		//return  ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	public String getDownloadFileName(){
		//System.out.println("3");
		return filename;
	}
	/*public void DeleteFile()
	{
		String path = ServletActionContext.getServletContext().getRealPath("/text");
		System.out.println("删除文件路径"+path);
		File file = new File(path);
		file.delete();
	}*/
}
