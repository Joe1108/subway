package s5;

import java.util.ArrayList;

public class Station {
	private String name;
	private ArrayList<String> sor= new ArrayList<>();//station of route站点所在线路名（可能存在多条线路有该站）
	private ArrayList<String> sb= new ArrayList<>();//station beside 接邻站
	
	private Station ps;//前一站点
	private int distance;//距离
	private int turnNum=0;//换乘
	private int isVisted=0;//是否已经访问
	
	public Station() {
		this.distance=65535;
		this.turnNum=0;
		this.isVisted=0;
	}
	
	public void setName(String name) {
		this.name=name;
		
	}
	
	public String getName() {
		return name;
	}
	public void setSor(String routename) {//该站点所属的线路
		this.sor.add(routename);
		
	}
	public ArrayList<String> getSor() {
		return sor;
	}
	public int getIsVisited() {
		return isVisted;
	}

	public void setIsVisited(int isVisted) {
		this.isVisted = isVisted;
	}

	
	public Station getPs() {
		return ps;
	}
	public void setPs(Station ps) {
		this.ps = ps;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getTurnNum() {
		return turnNum;
	}
	public void setTurnNum(int turnNum) {
		this.turnNum = turnNum;
	}
	//相邻站
		public void setSb(String name) {
			for(int i=0;i<this.sb.size();i++) {
				if(this.sb.get(i).equals(name)) {
					return;
				}
			}
			this.sb.add(name);
		}
	//相邻站点
	
	public ArrayList<Station> setSb(ArrayList<Station> station){
		ArrayList<Station> result = new ArrayList<>();
		for(int i=0;i<this.sb.size();i++) {
			String be = this.sb.get(i);
			for(int j=0;j<station.size();j++) {//若邻接站点存在目的站点，则查找成功
				if(station.get(j).getName().equals(be)) {
					result.add(station.get(j));
					break;
				}
			}
		}
		return result;
	}
}
