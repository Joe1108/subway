package s5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class loadMap {//�����ݶ��룬��������get������ȡվ�����·
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
		//����txt�ļ�
		try {
		
		String in=null;//�����ַ���
		while((in = br.readLine())!=null) {
			Route r = new Route();
			String[] s=in.split(" ");//�ָ�ÿ�е�ÿ����·����վ��
			r.setName(s[0]);//ÿ�е�һ������·��
			for(int i=1;i<s.length;i++) {
				r.addStation(s[i]);//����һ������վ����
				
			}
			
			route.add(r);
		}
		int i=0;
		int isin=0;
		for(i=0;i<route.size();i++) {
			String routeName=route.get(i).getName();
			ArrayList<String> st = route.get(i).getRoute();//������·��վ��
			//��Ҫ�жϻ��ߣ���Ϊ����ʱ��βվһ�£�����վ�������ͬ
			if(st.get(0).equals(st.get(st.size()-1))){//��λվһ��
				isin=0;
				for(int k=0;k<station.size();k++) {
					//�Ѷ���
					if(station.get(k).getName().equals(st.get(0))) {
						station.get(k).setSor(routeName);//����������·
						//������վ
						station.get(k).setSb(st.get(1));
						station.get(k).setSb(st.get(st.size()-2));
						isin=1;
						break;
						}
					}
				
				//δ����
				if(isin==0) {
					Station s=new Station();
					s.setName(st.get(0));//վ��
					s.setSor(routeName);//������·
					s.setSb(st.get(1));
					s.setSb(st.get(st.size()-2));
					station.add(s);
				}
			}
			else {
				isin=0;
				for(int k=0;k<station.size();k++) {

					//δ�л�����վ�����һ������վ
					if(station.get(k).getName().equals(st.get(0))) {
						station.get(k).setSor(routeName);
						station.get(k).setSb(st.get(1));
						isin=1;
						break;
					}
				}
				//δ����
				if(isin==0) {
					Station s=new Station();
					s.setName(st.get(0));//վ��
					s.setSor(routeName);//������·
					s.setSb(st.get(1));
					station.add(s);
				}
				isin=0;
				for(int k=0;k<station.size();k++) {

					//δ�л���βվ�����һ������վ
					if(station.get(k).getName().equals(st.get(st.size()-1))) {
						station.get(k).setSor(routeName);
						station.get(k).setSb(st.get(st.size()-2));
						isin=1;
						break;
					}
				}
				//δ����
				if(isin==0) {
					Station s=new Station();
					s.setName(st.get(st.size()-1));//վ��
					s.setSor(routeName);//������·
					s.setSb(st.get(st.size()-2));
					station.add(s);
				}
			}
			//��ȥ��βվ�������վ��
			for(int m=1;m<st.size()-1;m++) {
				isin=0;
				for(int n=0;n<station.size();n++) {
					//����
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
				//ǰ��վ����վ
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
