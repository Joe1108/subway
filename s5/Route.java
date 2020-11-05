package s5;

import java.util.ArrayList;

public class Route {
	private String name;
	private ArrayList<String> route=new ArrayList<>();//存放线路的站点
	public void setName(String n) {
		this.name=n;
	}
	public void addStation(String sn) {
		this.route.add(sn);
	}
	public String showRoute() {
		String result="";
		
		for(int i=0;i<route.size();i++) {
			result+=route.get(i)+" ";
		}
		
		return result;
	}
	public String getName() {
		return name;
	}
	public ArrayList<String> getRoute() {
		return this.route;
	}

}
