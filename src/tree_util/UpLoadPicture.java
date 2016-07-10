package tree_util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DBConnection;
import db_tool.TimeFormat;

/**
 * 上传照片
 * */
public class UpLoadPicture{
	
	/**
	 * @param path 照片绝对路径   (格式: 路径#路径#)
	 * @param new_name_start  照片开头字母
	 * @return	所有的照片路径   例：  MON20160501102201_1.jpg|MON20160501102201_2.png
	 * */
	public static String upload(String path,String new_name_start){
		String photo = "";				//返回的路径
		
		String server_path = "";
    	String every_path = null;			
    	String type = null;					
    	String new_name = null;
    	int m = 0;
    	int i = 0;
    	File local = null;			//本地照片文件
    	File server = null;			//对应的存储在服务器的文件夹
    	String time = TimeFormat.getNowTime();
    	
		server_path = ServletActionContext.getServletContext().getRealPath("/picture");
		
		int k = 1;
    	for(int j = 0;j < path.length();j ++){
    		if(path.substring(j, j+1).equals("#")){
    			every_path = path.substring(i,j);			//每一张图片的路径
    			m = every_path.indexOf(".");
    			type = every_path.substring(m+1);			//每一张图片的保存路径
    			
    			local = new File(every_path);
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
    			
    			try {
    				BufferedImage image = ImageIO.read(local);
    				//新路径
    				new_name = new_name_start + time + "_" + k + "." + type;
    				System.out.println("new_name---" + new_name);
    				
					ImageIO.write(image, type, new File(server, new_name));
					
					photo += (new_name + "|");
					k++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			System.out.println(every_path);
    			System.out.println(type);
    			i = j+1;
    			j++;
    		}
    	}

    	photo = photo.substring(0, photo.length()-1);
    	System.out.println(photo);
		return photo;
	}
	
}
