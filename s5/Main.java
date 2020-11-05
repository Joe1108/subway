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
		
		//读取文件

		loadMap lm=new loadMap("C:\\Users\\33236\\Desktop\\subway.txt");
		//提取所保存的站点和线路信息
		ArrayList<Station> station= lm.getStation();
		ArrayList<Route> route= lm.getRoute();
		
		//输出所有线路名称
		for(int i=0;i<route.size();i++) {
			System.out.print(route.get(i).getName()+" ");
		}
		System.out.println();
		//输出菜单
		System.out.println("请选择: \n1.查询线路 2.搜索最短路线");
		//输入
		int choose = input.nextInt();
		switch(choose) {
		case 1:{
			System.out.print("请输入查询的线路名: ");
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
				System.out.println("该线路名不存在");
			}else {
				System.out.println(route.get(index).getName()+":"+route.get(index).showRoute());
			}
		}
		case 2:{

			BFS2 bfs = new BFS2();
			System.out.print("请输入始发站: ");
			String start = input.next();
			System.out.print("请输入终点站: ");
			String end = input.next();
			bfs.FindPath(start,end, station);
		}
		}

	}

}
