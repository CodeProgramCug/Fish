package tree_util;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传照片
 * */
public class UpLoadPicture extends ActionSupport {
	
	private String start;				//结点前三个字母,用于定位表名
	//主键与外键一起定位某一条记录
	private String ID;					//主键
	private String foreign_ID;			//外键
	
	private File upload = null;					//封装文件内容
	private String uploadFileName = null;		//封装文件类型
	private String savePath = null;				//在struts.xml配置属性
	
	private String tableName;			//表名
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		return SUCCESS;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getForeign_ID() {
		return foreign_ID;
	}

	public void setForeign_ID(String foreign_ID) {
		this.foreign_ID = foreign_ID;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
}
