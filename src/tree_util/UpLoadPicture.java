package tree_util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.TimeFormat;

/**
 * 上传照片
 * */
public class UpLoadPicture{
	
	/**
	 * @param Photo 文件引用
	 * @param PhotoFileName 文件名
	 * @param PhotoContentType 文件类型
	 * @param new_name_start   开头的三个字母
	 * 
	 * @return	所有的照片路径   例：  MON20160501102201_1.jpg|MON20160501102201_2.png
	 * */
	public static String upload(List<File> Photo,List<String> PhotoFileName,
			String new_name_start){
		String photo_name = "";				//返回的路径
		
		File local = null;			//本地照片文件
    	File server = null;			//对应的存储在服务器的文件夹
    	
		String server_path = ServletActionContext.getServletContext().getRealPath("/picture");
		String time = TimeFormat.getNowTime();
		
		server = new File(server_path);
		if(!server.exists()){
			server.mkdir();
		}else{
			//如果检测到该表已经有照片存在,删除
			File[] file = server.listFiles();
			for(int t = 0;t < file.length; t++){
				if(file[t].getName().startsWith(new_name_start)){
					file[t].delete();
				}
			}
			
		}
		
		File file = null;
		for(int i=0;i<Photo.size();i++){
			int m = PhotoFileName.get(i).indexOf(".");
			String type = PhotoFileName.get(i).substring(m+1);
			
			String child = new_name_start + time + "_" + (i+1) + "." + type;
			
			file = new File(server_path, child);
			
			try {
				FileUtils.copyFile(Photo.get(i), file);
				
				photo_name += (child + "|");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("照片复制错误");
				e.printStackTrace();
			}
		}
		
		photo_name = photo_name.substring(0,photo_name.length()-1);
		
		System.out.println("@@@@-- " + photo_name);
		return photo_name;
	}
	
}
