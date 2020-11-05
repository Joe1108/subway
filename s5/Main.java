package s5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		
		//��ȡ�ļ�

		loadMap lm=new loadMap("C:\\Users\\33236\\Desktop\\subway.txt");
		//��ȡ�������վ�����·��Ϣ
		ArrayList<Station> station= lm.getStation();
		ArrayList<Route> route= lm.getRoute();
		
		//���������·����
		for(int i=0;i<route.size();i++) {
			System.out.print(route.get(i).getName()+" ");
		}
		System.out.println();
		//����˵�
		System.out.println("��ѡ��: \n1.��ѯ��· 2.�������·��");
		//����
		int choose = input.nextInt();
		switch(choose) {
		case 1:{
			System.out.print("�������ѯ����·��: ");
			String name = input.next();
			int index=-1;
			int i=0;
			while(i<route.size()) {
				if(route.get(i).getName().equals(name)) {
					index=i;
					break;
				}
				i++;
			}
			if(index==-1) {
				System.out.println("����·��������");
			}else {
				System.out.println(route.get(index).getName()+":"+route.get(index).showRoute());
			}
		}
		case 2:{

			BFS2 bfs = new BFS2();
			System.out.print("������ʼ��վ: ");
			String start = input.next();
			System.out.print("�������յ�վ: ");
			String end = input.next();
			bfs.FindPath(start,end, station);
		}
		}

	}

}
