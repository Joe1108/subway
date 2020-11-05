package s5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS2 {
	public void FindPath(String firstStation,String endStation,ArrayList<Station> station){
		ArrayList<Station> result = new ArrayList<>();//�����
		ArrayList<Station>	sta= new ArrayList<>();;	
		
		int fsIndex=-1;
		for(int i=0;i<station.size();i++) {
			Station st = station.get(i);
			if(st.getName().equals(firstStation)) {//�ҵ�������ͬ
				fsIndex=i;
				break;
			}
		}
		//��δ�ҵ��򱨴����
		if(fsIndex==-1) {
			System.out.println("δ�ҵ���ʼվ");
			System.exit(0);
			sta= station;
		}
		
		//ִ���㷨
		Queue<Station> queue = new LinkedList<>();//����ģ�����
		station.get(fsIndex).setIsVisited(1);//��Ƿ���
		queue.add(station.get(fsIndex));//��ʼվ�������
		
		int distance=0;//���沽��
		while(!queue.isEmpty()) {
			Station topS = queue.remove();//�Ƴ�����ͷ��
			
			if(distance==0) {//�ж��ǲ��Ƕ�ͷ
				topS.setDistance(distance);//���벽��
				distance++;
			}else {
				//�ж��Ƿ񻻳�
				distance=topS.getPs().getDistance();
				topS.setDistance(distance+1);
				distance++;
			}
			result.add(topS);//���������
			
			ArrayList<Station> tmpSb = topS.setSb(station);
			for(int i=0;i<tmpSb.size();i++) {
				if(tmpSb.get(i).getIsVisited()==0) {//�ж��Ƿ���ʹ�
					tmpSb.get(i).setPs(topS);//����ǰ��վ��Ϊ��ǰվ��
					tmpSb.get(i).setIsVisited(1);//��Ƿ���
					queue.offer(tmpSb.get(i));//��δ���ʹ���ֱ�Ӵ������
				}
			}
			
		}
		sta= result;
		//���յ�վ
				int endIndex=-1;
				int i=0;
				while(i<sta.size()) {
					Station tmp = sta.get(i);
					if(tmp.getName().equals(endStation)) {
						endIndex=i;
						break;
					}i++;
				}
				//��δ�ҵ��򱨴����
				if(endIndex==-1) {
					System.out.println("δ�ҵ����յ�վ");
					System.exit(0);

				}
				
				Stack<Station> stack = new Stack<>();
				//����ջ��ʵ���������
				Station tmp = sta.get(endIndex);//ջ��Ϊ�յ�վ
				if(tmp.getDistance()==0) {
					System.out.println("��վΪʼ��վ");

				}
				distance = tmp.getDistance();//���ڱ���;��վ����
				int transNum = 0;//���ڱ��滻����
				//����ջ
				while(tmp.getPs()!=null) {
					stack.push(tmp);
					tmp=tmp.getPs();//����Ϊǰ��վ����ջ
				}
				
				//�жϻ���
				ArrayList<String> r1 =tmp.getSor();
				ArrayList<String> r2 = stack.peek().getSor();
				String now="";//���ڱ��浱ǰ��·
				int flag=0;
				//Ѱ�ҵ�ǰ��·
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
				System.out.println("��ʼΪ��"+now);
				System.out.print(tmp.getName());
				//�𲽳�ջ
				while(!stack.isEmpty()) {
					//�ж��Ƿ񻻳�
					r1 = tmp.getSor();
					r2 = stack.peek().getSor();
					flag=0;
					for(i=0;i<r1.size();i++) {
						for(int j=0;j<r2.size();j++) {
							//������վ�������е���·�뵱ǰ��·��ͬ����Ϊ������·
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
						System.out.println("��������"+now);
						System.out.print(stack.pop().getName());
						transNum++;
					}else {
						tmp=stack.peek();
						System.out.print("-->"+stack.pop().getName());
					}
					
				}
				System.out.println();
				distance--;
				System.out.println("��վ��Ϊ"+distance);
				System.out.println("������Ϊ"+transNum);
			
		
	}
}
