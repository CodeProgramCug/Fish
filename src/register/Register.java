package register;

import db_tool.DbBean;
import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport{
	private static final long serialVersionUID = 1L; /*串行化版本号*/ 
	private String UserName;
	private String PassWord;
	private String UserGUID;
	private String GroupName;
	private String MonitUnitName;
	private String Telephone;
	private String Email;	
	private String Description;
	
	public static long getSerialVersionUID() { 
        return serialVersionUID; 
    }
	public String getUserGUID() {
		return UserGUID;
	}
	public void setUserGUID(String UserGUID) {
		this.UserGUID = UserGUID;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String GroupName) {
		this.GroupName = GroupName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String PassWord) {
		this.PassWord = PassWord;
	}
	public String getMonitUnitName() {
		return MonitUnitName;
	}
	public void setMonitUnitName(String MonitUnitName) {
		this.MonitUnitName = MonitUnitName;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String Telephone) {
		this.Telephone = Telephone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}	
	//执行插入操作，根据得到的改变的行数确定是否成功
	public String execute() throws Exception{ 
		DbBean db=new DbBean();		
		//String sql="insert into Sy_UserInfo values('"+ID+"','"+UserGUID+"','"+GroupName+"','"+MonitUnitName+"','"+UserName+"','"+PassWord+"','"+Telephone+"','"+Email+"','"+Description+"','锁定'"+")";
		String sql="insert into Sy_UserInfo values('"+UserGUID+"','"+GroupName+"','"+MonitUnitName+"','"+UserName+"','"+PassWord+"','"+Telephone+"','"+Email+"','"+Description+"','锁定'"+")";
		
		//System.out.println(sql);
		try{
			db.executeUpdate(sql);
			db.close();
		    return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			db.close();
			return ERROR;
		}
		
	}

}
