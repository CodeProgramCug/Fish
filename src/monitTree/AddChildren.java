package monitTree;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db_tool.DbBean;

public class AddChildren extends ActionSupport {
		private String addType;						//标记增加结点的类型
		private String fatherText;
		private String Position;
		private String Distance2Bank;
		
		private String StartLongitude;
		private String StartLatitude;
		private String EndLongitude;
		private String EndLatitude;
		
		private String Institution;
		private String Investigator;
		private String InvestigationDate;
		private String Site;
		private String River;
		private String Photo;
		private String StartTime;
		private String EndTime;
		private String Weather;
		private String Temperature;
		
		//采样水层属性
		//private String ID;
		private String Layer;
		private String Depth;
		private String Lay_Temperature;
		private String WaterLevel;
		private String Velocity;
		private String ID_MeasuringPoint;			//外键
		
		public String getNowTime(){
			String nowtime;
			Date d=new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
			nowtime=df.format(d);
			nowtime=nowtime.substring(0, 12);
			System.out.println("time:"+nowtime);
			return nowtime;
		}
		
		public String execute() throws IOException{
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			String childID="";
			String result="";
			DbBean ssd=new DbBean();
			String sql="";
		
			try{
				if(addType.equals("1")){				//添加监测点
					childID+="MON"+getNowTime();
					sql+="insert into MonitoringSite values('"+childID+"','"+Institution+"','"+Investigator+"','"+InvestigationDate+"','"+Site+"','";
					sql+=River+"','"+Photo+"','"+StartTime+"','"+EndTime+"','"+StartLongitude+"','"+StartLatitude+"','"+EndLongitude+"','"+EndLatitude+"','"+Weather+"','"+Temperature+"')";
					System.out.println("MON-sql:"+sql);
					ssd.executeUpdate(sql);
					writer.write(childID);
				}else if(addType.equals("2")){			//添加断面
					childID+="SEC"+getNowTime();
					sql+="insert into FractureSurface values('"+childID+"','"+Position+"','"+Distance2Bank+"','"+fatherText+"')";
					System.out.println("SEC-sql:"+sql);
					ssd.executeUpdate(sql);
					writer.write(childID);
				}else if(addType.equals("3")){			//添加测线
					childID+="LIN"+getNowTime();
					sql+="insert into MeasuringLine values('"+childID+"','"+StartLongitude+"','"+StartLatitude+"','"+EndLongitude+"','"+EndLatitude+"','"+fatherText+"')";
					System.out.println("Lin-sql:"+sql);
					ssd.executeUpdate(sql);
					writer.write(childID);
				}else if(addType.equals("4")){			//添加采样水层
					childID += "WLE" + getNowTime();
					sql = "insert into WaterLayer values('" + childID + "','" + Layer + "','" + Depth
							+ "','" + Lay_Temperature + "','" + WaterLevel + "','" + Velocity + "','"
							+ ID_MeasuringPoint + "')";
					
				}
			}catch(Exception e){
				System.out.println(e);
				writer.write("error");
			}
			return null;
		}
		
		public String UpdateData() throws IOException{
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			String childID="";
			String result="";
			DbBean ssd=new DbBean();
			String sql="";
			try{
				if(addType.equals("1")){
					sql+="update MonitoringSite set Institution='"+Institution+"',Investigator='"+Investigator+"',InvestigationDate='"+InvestigationDate+"',Site='";
					sql+=Site+"',River='"+River+"',Photo='"+Photo+"',StartTime='"+StartTime+"',EndTime='"+EndTime+"',StartLongitude='"+StartLongitude+"',StartLatitude='";
					sql+=StartLatitude+"',EndLongitude='"+EndLongitude+"',EndLatitude='"+EndLatitude+"',Weather='"+Weather+"',Temperature='"+Temperature+"'";
					sql+=" where InverstigationID='"+fatherText+"'";
				}else if(addType.equals("2")){
					sql+="update FractureSurface set Position='"+Position+"',Distance2Bank='"+Distance2Bank+"' where ID='"+fatherText+"'";
				}else if(addType.equals("3")){
					sql+="update MeasuringLine set StartLongitude='"+StartLongitude+"',StartLatitude='"+StartLatitude+"',EndLongitude='"+EndLongitude+"',EndLatitude='"+EndLatitude+"' where ID='"+fatherText+"'";
				}
				ssd.executeUpdate(sql);
				writer.write("success");
			}catch(Exception e){
				writer.write("error");
				System.out.println(e);
			}
			return null;
		}
		
		public String getInstitution() {
			return Institution;
		}
		public void setInstitution(String institution) {
			Institution = institution;
		}
		public String getInvestigator() {
			return Investigator;
		}
		public void setInvestigator(String investigator) {
			Investigator = investigator;
		}
		public String getInvestigationDate() {
			return InvestigationDate;
		}
		public void setInvestigationDate(String investigationDate) {
			InvestigationDate = investigationDate;
		}
		public String getSite() {
			return Site;
		}
		public void setSite(String site) {
			Site = site;
		}
		public String getRiver() {
			return River;
		}
		public void setRiver(String river) {
			River = river;
		}
		public String getPhoto() {
			return Photo;
		}
		public void setPhoto(String photo) {
			Photo = photo;
		}
		public String getStartTime() {
			return StartTime;
		}
		public void setStartTime(String startTime) {
			StartTime = startTime;
		}
		public String getEndTime() {
			return EndTime;
		}
		public void setEndTime(String endTime) {
			EndTime = endTime;
		}
		public String getWeather() {
			return Weather;
		}
		public void setWeather(String weather) {
			Weather = weather;
		}
		public String getTemperature() {
			return Temperature;
		}
		public void setTemperature(String temperature) {
			Temperature = temperature;
		}
		public String getStartLongitude() {
			return StartLongitude;
		}
		public void setStartLongitude(String startLongitude) {
			StartLongitude = startLongitude;
		}
		public String getStartLatitude() {
			return StartLatitude;
		}
		public void setStartLatitude(String startLatitude) {
			StartLatitude = startLatitude;
		}
		public String getEndLongitude() {
			return EndLongitude;
		}
		public void setEndLongitude(String endLongitude) {
			EndLongitude = endLongitude;
		}
		public String getEndLatitude() {
			return EndLatitude;
		}
		public void setEndLatitude(String endLatitude) {
			EndLatitude = endLatitude;
		}
		public String getAddType() {
			return addType;
		}
		public void setAddType(String addType) {
			this.addType = addType;
		}
		public String getFatherText() {
			return fatherText;
		}
		public void setFatherText(String fatherText) {
			this.fatherText = fatherText;
		}
		public String getPosition() {
			return Position;
		}
		public void setPosition(String position) {
			Position = position;
		}
		public String getDistance2Bank() {
			return Distance2Bank;
		}
		public void setDistance2Bank(String distance2Bank) {
			Distance2Bank = distance2Bank;
		}

		public String getLayer() {
			return Layer;
		}

		public void setLayer(String layer) {
			Layer = layer;
		}

		public String getDepth() {
			return Depth;
		}

		public void setDepth(String depth) {
			Depth = depth;
		}

		public String getLay_Temperature() {
			return Lay_Temperature;
		}

		public void setLay_Temperature(String lay_Temperature) {
			Lay_Temperature = lay_Temperature;
		}

		public String getWaterLevel() {
			return WaterLevel;
		}

		public void setWaterLevel(String waterLevel) {
			WaterLevel = waterLevel;
		}

		public String getVelocity() {
			return Velocity;
		}

		public void setVelocity(String velocity) {
			Velocity = velocity;
		}

		public String getID_MeasuringPoint() {
			return ID_MeasuringPoint;
		}

		public void setID_MeasuringPoint(String iD_MeasuringPoint) {
			ID_MeasuringPoint = iD_MeasuringPoint;
		}
		
}
