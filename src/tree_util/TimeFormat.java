package tree_util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
	
	//获取当前时间
	public static String getNowTime(){
		String nowtime;
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		nowtime=df.format(d);
		nowtime=nowtime.substring(0, 12);
		System.out.println("time:"+nowtime);
		return nowtime;
	}
}
