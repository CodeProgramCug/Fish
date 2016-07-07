package fishDataInput;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import db_tool.DbBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FishRecord extends ActionSupport {
	
	private String recordType;
	private String ID;
	
	private String   MonitPerson;
	private String   SectRive;
	private String   MonitSiteName;
	private String   EastLo;
	private String   NorthLa;
	
	private String   MonitDate;
	private String   MonitTime;
	private String   Locat;
	private String   Quantity;
	private String   Biomass;
	
	private String   Transparen;
	private String   OwnerUnit;
	private String   OwnerID;
	private String   OperateDate;
	private String   OperateTime;
	private String   DataState;
	
	private String   PhyName;
	private String   WaterDep;
	private String   ZooplanName;
	private String   BenName;
	
	public String getOperateDate() {
		return OperateDate;
	}
	public void setOperateDate(String operateDate) {
		OperateDate = operateDate;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getMonitPerson() {
		return MonitPerson;
	}
	public void setMonitPerson(String monitPerson) {
		MonitPerson = monitPerson;
	}
	public String getSectRive() {
		return SectRive;
	}
	public void setSectRive(String sectRive) {
		SectRive = sectRive;
	}
	public String getMonitSiteName() {
		return MonitSiteName;
	}
	public void setMonitSiteName(String monitSiteName) {
		MonitSiteName = monitSiteName;
	}
	public String getEastLo() {
		return EastLo;
	}
	public void setEastLo(String eastLo) {
		EastLo = eastLo;
	}
	public String getNorthLa() {
		return NorthLa;
	}
	public void setNorthLa(String northLa) {
		NorthLa = northLa;
	}
	public String getMonitDate() {
		return MonitDate;
	}
	public void setMonitDate(String monitDate) {
		MonitDate = monitDate;
	}
	public String getMonitTime() {
		return MonitTime;
	}
	public void setMonitTime(String monitTime) {
		MonitTime = monitTime;
	}
	public String getLocat() {
		return Locat;
	}
	public void setLocat(String locat) {
		Locat = locat;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getBiomass() {
		return Biomass;
	}
	public void setBiomass(String biomass) {
		Biomass = biomass;
	}
	public String getTransparen() {
		return Transparen;
	}
	public void setTransparen(String transparen) {
		Transparen = transparen;
	}
	public String getOwnerUnit() {
		return OwnerUnit;
	}
	public void setOwnerUnit(String ownerUnit) {
		OwnerUnit = ownerUnit;
	}
	public String getOwnerID() {
		return OwnerID;
	}
	public void setOwnerID(String ownerID) {
		OwnerID = ownerID;
	}
	public String getOperateTime() {
		return OperateTime;
	}
	public void setOperateTime(String operateTime) {
		OperateTime = operateTime;
	}
	public String getDataState() {
		return DataState;
	}
	public void setDataState(String dataState) {
		DataState = dataState;
	}
	public String getPhyName() {
		return PhyName;
	}
	public void setPhyName(String phyName) {
		PhyName = phyName;
	}
	public String getWaterDep() {
		return WaterDep;
	}
	public void setWaterDep(String waterDep) {
		WaterDep = waterDep;
	}
	public String getZooplanName() {
		return ZooplanName;
	}
	public void setZooplanName(String zooplanName) {
		ZooplanName = zooplanName;
	}
	public String getBenName() {
		return BenName;
	}
	public void setBenName(String benName) {
		BenName = benName;
	}
	
	public String execute(){
		Map attribute=ActionContext.getContext().getSession();
		MonitPerson=(String) attribute.get("userName");
		int isSuccess=1;
		DbBean ssd=new DbBean();
		String sql="";
		System.out.println("ready==="+recordType);
		
	try{
		if(recordType.equals("1")){
			sql+="INSERT INTO Monit_Phy VALUES (";
			sql+="'"+MonitPerson+"','"+PhyName+"','"+SectRive+"','"+MonitSiteName+"','"+EastLo+"','"+NorthLa+"','"+MonitDate+"','"+MonitTime+"','"+Locat+"','"+WaterDep+"',";
			sql+="'"+Quantity+"','"+Biomass+"','"+Transparen+"','"+OwnerUnit+"','"+OwnerID+"','"+OperateDate+"','"+OperateTime+"','"+DataState+"')";
			System.out.println("sql:"+sql);
			ssd.executeUpdate(sql);
		}else if(recordType.equals("2")){
			sql+="INSERT INTO Monit_Zooplan VALUES (";
			sql+="'"+MonitPerson+"','"+ZooplanName+"','"+SectRive+"','"+MonitSiteName+"','"+EastLo+"','"+NorthLa+"','"+MonitDate+"','"+MonitTime+"','"+Locat+"','"+WaterDep+"',";
			sql+="'"+Quantity+"','"+Biomass+"','"+Transparen+"','"+OwnerUnit+"','"+OwnerID+"','"+OperateDate+"','"+OperateTime+"','"+DataState+"')";
			System.out.println("sql:"+sql);
			ssd.executeUpdate(sql);
		}else if(recordType.equals("3")){
			sql+="INSERT INTO Monit_Ben VALUES (";
			sql+="'"+MonitPerson+"','"+BenName+"','"+SectRive+"','"+EastLo+"','"+NorthLa+"','"+MonitSiteName+"','"+MonitDate+"','"+MonitTime+"','"+Locat+"','"+Quantity+"',";
			sql+="'"+Biomass+"','"+Transparen+"','"+OwnerUnit+"','"+OwnerID+"','"+OperateDate+"','"+OperateTime+"','"+DataState+"')";
			System.out.println("sql:"+sql);
			ssd.executeUpdate(sql);
		}
	}catch(Exception e){
		System.out.println("catched:"+e);
		isSuccess=2;
		
	}
		ssd.close();
		System.out.println("No catched,isSuccess:"+isSuccess);
		attribute.put("isSuccess", isSuccess);
		return SUCCESS;
	}
	public String revise(){
		Map attribute=ActionContext.getContext().getSession();
		//int isSuccess=1;
		DbBean ssd=new DbBean();
		String sql="";
		
		try{
			if(recordType.equals("1")){
				sql+="update Monit_Phy set ";
				sql+="MonitPerson='"+MonitPerson+"',PhyName='"+PhyName+"',SectRive='"+SectRive+"',MonitSiteName='"+MonitSiteName+"',EastLo='"+EastLo+"',NorthLa='"+NorthLa+"',MonitDate='"+MonitDate+"',MonitTime='"+MonitTime+"',Locat='"+Locat+"',WaterDep='"+WaterDep+"',";
				sql+="Quantity='"+Quantity+"',Biomass='"+Biomass+"',Transparen='"+Transparen+"',OwnerUnit='"+OwnerUnit+"',OwnerID='"+OwnerID+"',OperateDate='"+OperateDate+"',OperateTime='"+OperateTime+"',DataState='"+DataState+"' ";
				sql+="where ID='"+ID+"'";
				System.out.println("sql:"+sql);
				ssd.executeUpdate(sql);
			}else if(recordType.equals("2")){
				sql+="update Monit_Zooplan set ";
				sql+="MonitPerson='"+MonitPerson+"',ZooplanName='"+ZooplanName+"',SectRive='"+SectRive+"',MonitSiteName='"+MonitSiteName+"',EastLo='"+EastLo+"',NorthLa='"+NorthLa+"',MonitDate='"+MonitDate+"',MonitTime='"+MonitTime+"',Locat='"+Locat+"',WaterDep='"+WaterDep+"',";
				sql+="Quantity='"+Quantity+"',Biomass='"+Biomass+"',Transparen='"+Transparen+"',OwnerUnit='"+OwnerUnit+"',OwnerID='"+OwnerID+"',OperateDate='"+OperateDate+"',OperateTime='"+OperateTime+"',DataState='"+DataState+"' ";
				sql+="where ID='"+ID+"'";
				System.out.println("sql:"+sql);
				ssd.executeUpdate(sql);
			}else if(recordType.equals("3")){
				sql+="update Monit_Ben set ";
				sql+="MonitPerson='"+MonitPerson+"',BenName='"+BenName+"',SectRive='"+SectRive+"',EastLo='"+EastLo+"',NorthLa='"+NorthLa+"',MonitSiteName='"+MonitSiteName+"',MonitDate='"+MonitDate+"',MonitTime='"+MonitTime+"',Locat='"+Locat+"',Quantity='"+Quantity+"',";
				sql+="Biomass='"+Biomass+"',Transparen='"+Transparen+"',OwnerUnit='"+OwnerUnit+"',OwnerID='"+OwnerID+"',OperateDate='"+OperateDate+"',OperateTime='"+OperateTime+"',DataState='"+DataState+"' ";
				sql+="where ID='"+ID+"'";
				System.out.println("sql:"+sql);
				ssd.executeUpdate(sql);
			}		
		}catch(Exception e){
			//isSuccess=2;
			System.out.println(e);
		}
		ssd.close();
		return SUCCESS;
	}
	
	public String deleteFish() throws IOException{
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		System.out.println("deleteFish-- ID:"+ID+"--QType:"+recordType);
		DbBean ssd=new DbBean();
		String sql="";
		try{
			if(recordType.equals("1")){
				sql+="delete from Monit_Phy where ID='"+ID+"'";
			}else if(recordType.equals("2")){
				sql+="delete from Monit_Zooplan where ID='"+ID+"'";
			}else if(recordType.equals("3")){
				sql+="delete from Monit_Ben where ID='"+ID+"'";
			}
			System.out.println("delete sql:"+sql);
			ssd.executeUpdate(sql);
			ssd.close();
			writer.write("success");
		}catch(Exception e){
			writer.write("DeleteFail");
			System.out.println(e);
		}
		return null;
	}
	
}
