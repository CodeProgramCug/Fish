package fileload;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
 
public class FileUpload {  
 
    public static void main(String[] args) {  
        /*try {  
            BufferedReader reader = new BufferedReader(new FileReader("D:\\Base_MonitPersonel2.csv"));//换成你的文件名 
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
            String line = null;  
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                  
                String last = item[item.length-1];//这就是你要的数据了 
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值 
                System.out.println(last);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  */
    	
    	/*try {
    		File d = new File("D:\\xxx\\animation.jpg");
    		BufferedImage image = ImageIO.read(d);
    		
			File file = new File("D:\\yyy");
			if(!file.exists()){
				file.mkdir();
			}
			
			ImageIO.write(image, "jpg", new File(file, "yyy.jpg"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	/*String path = "D:\\xxx\\add.png#D:\\xxx\\animation.jpg#D:\\xxx\\back.png#D:\\xxx\\bei.png#D:\\xxx\\danke.jpg#";
    	String server_path = "D:\\yyy";
    	String every_path = null;			
    	String type = null;					
    	int m = 0;
    	int i = 0;
    	File local = null;			//本地照片文件
    	File server = null;			//对应的存储在服务器的文件夹
    	
    	for(int j = 0;j < path.length();j ++){
    		if(path.substring(j, j+1).equals("#")){
    			every_path = path.substring(i,j);			//每一张图片的路径
    			m = every_path.indexOf(".");
    			type = every_path.substring(m+1);			//每一张图片的保存路径
    			
    			local = new File(every_path);
    			server = new File(server_path);
    			if(!server.exists()){
    				server.mkdir();
    			}
    			
    			try {
    				BufferedImage image = ImageIO.read(local);
					ImageIO.write(image, type, new File(server, j +"yyy." + type));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			System.out.println(every_path);
    			System.out.println(type);
    			i = j+1;
    			j++;
    		}
    	}*/
    	
    }  
    	
}