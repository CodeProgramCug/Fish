package org.fishDataInput;

import java.util.Map;

import org.util.SQL_Server_DbBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FishRecord extends ActionSupport {
	
	private String recordType;
	
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
	private String   OperateTime;
	private String   DataState;
	
	private String   PhyName;
	private String   WaterDep;
	private String   ZooplanName;
	private String   BenName;
	
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
	
	public String execute()throws Exception{
		Map attribute=ActionContext.getContext().getSession();
		int isSuccess;
		SQL_Server_DbBean ssd=new SQL_Server_DbBean();
		String sql="";
		System.out.println("ready===");
		if(recordType.equals("1")){
			sql+="INSERT INTO Monit_Phy VALUES (";
			sql+="'"+MonitPerson+"','"+PhyName+"','"+SectRive+"','"+MonitSiteName+"','"+EastLo+"','"+NorthLa+"','"+MonitDate+"','"+MonitTime+"','"+Locat+"','"+WaterDep+"',";
			sql+="'"+Quantity+"','"+Biomass+"','"+Transparen+"','"+OwnerUnit+"','"+OwnerID+"','"+OperateTime+"','"+DataState+"')";
			System.out.println("sql:"+sql);
			ssd.executeUpdate(sql);
		}else if(recordType.equals("2")){
			sql+="INSERT INTO Monit_Zooplan VALUES (";
			sql+="'"+MonitPerson+"','"+ZooplanName+"','"+SectRive+"','"+MonitSiteName+"','"+EastLo+"','"+NorthLa+"','"+MonitDate+"','"+MonitTime+"','"+Locat+"','"+WaterDep+"',";
			sql+="'"+Quantity+"','"+Biomass+"','"+Transparen+"','"+OwnerUnit+"','"+OwnerID+"','"+OperateTime+"','"+DataState+"')";
			System.out.println("sql:"+sql);
			ssd.executeUpdate(sql);
		}else if(recordType.equals("3")){
			sql+="INSERT INTO Monit_Ben VALUES (";
			sql+="'"+MonitPerson+"','"+BenName+"','"+SectRive+"','"+EastLo+"','"+NorthLa+"','"+MonitSiteName+"','"+MonitDate+"','"+MonitTime+"','"+Locat+"','"+Quantity+"',";
			sql+="'"+Biomass+"','"+Transparen+"','"+OwnerUnit+"','"+OwnerID+"','"+OperateTime+"','"+DataState+"')";
			System.out.println("sql:"+sql);
			ssd.executeUpdate(sql);
		}
		isSuccess=1;
		attribute.put("isSuccess", isSuccess);
		return SUCCESS;
	}
	
}
