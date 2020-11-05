package s5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS2 {
	public void FindPath(String firstStation,String endStation,ArrayList<Station> station){
		ArrayList<Station> result = new ArrayList<>();//结果集
		ArrayList<Station>	sta= new ArrayList<>();;	
		
		int fsIndex=-1;
		for(int i=0;i<station.size();i++) {
			Station st = station.get(i);
			if(st.getName().equals(firstStation)) {//找到名称相同
				fsIndex=i;
				break;
			}
		}
		//若未找到则报错结束
		if(fsIndex==-1) {
			System.out.println("未找到起始站");
			System.exit(0);
			sta= station;
		}
		
		//执行算法
		Queue<Station> queue = new LinkedList<>();//链表模拟队列
		station.get(fsIndex).setIsVisited(1);//标记访问
		queue.add(station.get(fsIndex));//初始站点入队列
		
		int distance=0;//保存步数
		while(!queue.isEmpty()) {
			Station topS = queue.remove();//移出队列头部
			
			if(distance==0) {//判断是不是队头
				topS.setDistance(distance);//存入步数
				distance++;
			}else {
				//判断是否换乘
				distance=topS.getPs().getDistance();
				topS.setDistance(distance+1);
				distance++;
			}
			result.add(topS);//结果集增加
			
			ArrayList<Station> tmpSb = topS.setSb(station);
			for(int i=0;i<tmpSb.size();i++) {
				if(tmpSb.get(i).getIsVisited()==0) {//判断是否访问过
					tmpSb.get(i).setPs(topS);//保存前置站点为当前站点
					tmpSb.get(i).setIsVisited(1);//标记访问
					queue.offer(tmpSb.get(i));//若未访问过则直接存入队列
				}
			}
			
		}
		sta= result;
		//找终点站
				int endIndex=-1;
				int i=0;
				while(i<sta.size()) {
					Station tmp = sta.get(i);
					if(tmp.getName().equals(endStation)) {
						endIndex=i;
						break;
					}i++;
				}
				//若未找到则报错结束
				if(endIndex==-1) {
					System.out.println("未找到该终点站");
					System.exit(0);

				}
				
				Stack<Station> stack = new Stack<>();
				//建立栈以实现逆序输出
				Station tmp = sta.get(endIndex);//栈底为终点站
				if(tmp.getDistance()==0) {
					System.out.println("该站为始发站");

				}
				distance = tmp.getDistance();//用于保存途经站点数
				int transNum = 0;//用于保存换乘数
				//逐步入栈
				while(tmp.getPs()!=null) {
					stack.push(tmp);
					tmp=tmp.getPs();//更新为前置站点入栈
				}
				
				//判断换乘
				ArrayList<String> r1 =tmp.getSor();
				ArrayList<String> r2 = stack.peek().getSor();
				String now="";//用于保存当前线路
				int flag=0;
				//寻找当前线路
				i=0;
				while(i<r1.size()) {
					for(int j=0;j<r2.size();j++) {
						if(r1.get(i).equals(r2.get(j))) {
							now=r1.get(i);
							flag=1;
							break;
						}
					}
					if(flag==1) {
						break;
					}i++;
				}
				System.out.println("起始为："+now);
				System.out.print(tmp.getName());
				//逐步出栈
				while(!stack.isEmpty()) {
					//判断是否换乘
					r1 = tmp.getSor();
					r2 = stack.peek().getSor();
					flag=0;
					for(i=0;i<r1.size();i++) {
						for(int j=0;j<r2.size();j++) {
							//若两个站点所共有的线路与当前线路不同，则为换乘线路
							if(r1.get(i).equals(r2.get(j))&&(!now.equals(r1.get(i)))) {
								now=r1.get(i);
								flag=1;
								break;
							}
						}
						if(flag==1) {
							break;
						}
					}
					if(flag==1) {
						tmp=stack.peek();
						System.out.println();
						System.out.println("换乘至："+now);
						System.out.print(stack.pop().getName());
						transNum++;
					}else {
						tmp=stack.peek();
						System.out.print("-->"+stack.pop().getName());
					}
					
				}
				System.out.println();
				distance--;
				System.out.println("总站数为"+distance);
				System.out.println("换乘数为"+transNum);
			
		
	}
}
