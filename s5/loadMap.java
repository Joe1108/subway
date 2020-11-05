package s5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class loadMap {//将数据读入，并可以用get方法获取站点和线路
	ArrayList<Station> station= new ArrayList<>();
	ArrayList<Route> route= new ArrayList<>();
	
	public ArrayList<Station> getStation() {
		return station;
	}

	public ArrayList<Route> getRoute() {
		return route;
	}
	
	public  loadMap(String path) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
		//读入txt文件
		try {
		
		String in=null;//读入字符串
		while((in = br.readLine())!=null) {
			Route r = new Route();
			String[] s=in.split(" ");//分隔每行的每个线路名和站点
			r.setName(s[0]);//每行第一个是线路名
			for(int i=1;i<s.length;i++) {
				r.addStation(s[i]);//除第一个都是站点名
				
			}
			
			route.add(r);
		}
		int i=0;
		int isin=0;
		for(i=0;i<route.size();i++) {
			String routeName=route.get(i).getName();
			ArrayList<String> st = route.get(i).getRoute();//返回线路的站点
			//需要判断环线，因为环线时首尾站一致，相邻站点情况不同
			if(st.get(0).equals(st.get(st.size()-1))){//首位站一致
				isin=0;
				for(int k=0;k<station.size();k++) {
					//已读入
					if(station.get(k).getName().equals(st.get(0))) {
						station.get(k).setSor(routeName);//更新所属线路
						//更新邻站
						station.get(k).setSb(st.get(1));
						station.get(k).setSb(st.get(st.size()-2));
						isin=1;
						break;
						}
					}
				
				//未读入
				if(isin==0) {
					Station s=new Station();
					s.setName(st.get(0));//站点
					s.setSor(routeName);//所在线路
					s.setSb(st.get(1));
					s.setSb(st.get(st.size()-2));
					station.add(s);
				}
			}
			else {
				isin=0;
				for(int k=0;k<station.size();k++) {

					//未有环线首站点仅有一个相邻站
					if(station.get(k).getName().equals(st.get(0))) {
						station.get(k).setSor(routeName);
						station.get(k).setSb(st.get(1));
						isin=1;
						break;
					}
				}
				//未读入
				if(isin==0) {
					Station s=new Station();
					s.setName(st.get(0));//站点
					s.setSor(routeName);//所在线路
					s.setSb(st.get(1));
					station.add(s);
				}
				isin=0;
				for(int k=0;k<station.size();k++) {

					//未有环线尾站点仅有一个相邻站
					if(station.get(k).getName().equals(st.get(st.size()-1))) {
						station.get(k).setSor(routeName);
						station.get(k).setSb(st.get(st.size()-2));
						isin=1;
						break;
					}
				}
				//未读入
				if(isin==0) {
					Station s=new Station();
					s.setName(st.get(st.size()-1));//站点
					s.setSor(routeName);//所在线路
					s.setSb(st.get(st.size()-2));
					station.add(s);
				}
			}
			//除去首尾站点的其余站点
			for(int m=1;m<st.size()-1;m++) {
				isin=0;
				for(int n=0;n<station.size();n++) {
					//遍历
					if(station.get(n).getName().equals(st.get(m))) {
						isin=1;
						station.get(n).setSor(routeName);
						station.get(n).setSb(st.get(m-1));
						station.get(n).setSb(st.get(m+1));
						break;
					}
				}
				if(isin==0) {
					Station s = new Station();
					s.setName(st.get(m));
					s.setSor(routeName);
				//前后站点邻站
					s.setSb(st.get(m-1));
					s.setSb(st.get(m+1));
					station.add(s);
				}
			}
			
		}
	}
	catch (IOException e) {  
        e.printStackTrace();  
    } finally {  
        if (br != null) {  
            try {  
            	br.close();  
            } catch (IOException e1) {  
            }  
        } 
    }
}
}
