package fileload;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUploadAction extends ActionSupport {
	private String ctable;
	public String getCtable() {
		return ctable;
	}
	public void setCtable(String ctable) {
		this.ctable = ctable;
	}

	private File upload;
	private String uploadContentType;
	private String uploadFileName; 
	@SuppressWarnings("rawtypes")
	Map attribute = ActionContext.getContext().getSession();
	//private String result;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		System.out.println("文件名"+uploadFileName);
		System.out.println("文件后缀"+uploadContentType);
		String path = ServletActionContext.getServletContext().getRealPath("/textupload");
		System.out.println("文件路径"+path);
		System.out.println("导入表名称："+ctable);
		File file = new File(path);
		if(!file.exists())
		{
			file.mkdir();
		}
		if(uploadFileName==null)
		{
			attribute.put("end", "1");
			return ERROR;
		}
		else 
		{
			System.out.println("调用函数");
			FileUploadCsv rcsv=new FileUploadCsv();
			FileUploadTxt rTxt=new FileUploadTxt();
			FileUtils.copyFile(upload,new File(file,uploadFileName));
			//System.out.println("调用readcsv");
			int read;
			if((uploadFileName.split("\\.")[1]).equals("txt"))
			{
				System.out.println("导入文件为txt格式"+uploadFileName.split("\\.")[1]);
				read=rTxt.readTxt(path,uploadFileName,uploadContentType,ctable);
			}
			else
			{
				
				System.out.println("导入文件为csv格式"+uploadFileName.split("\\.")[1]);
				read=rcsv.readCsv(path,uploadFileName,uploadContentType,ctable);
			}
			System.out.println("read"+read);
			if(read==1)
			{
				attribute.put("end", "2");
				System.out.println("上传成功！");
				DeleteFile();
				//result="上传成功！";
				return SUCCESS;
			}
			else {
				System.out.println("上传失败");
				DeleteFile();
				attribute.put("end", "3");
				return ERROR;
			}
		}
	}
	public void DeleteFile()
	{
		String path = ServletActionContext.getServletContext().getRealPath("/textupload");
		String FilePath = path+"\\"+uploadFileName; 
		System.out.println("删除文件路径"+FilePath);
		File file = new File(FilePath);
		file.delete();
		System.out.println("已删除文件");
	}
}
