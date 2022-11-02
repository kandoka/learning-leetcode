package exam;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
        	int n = sc.nextInt();
        	int r = sc.nextInt();
        	int avg = sc.nextInt();
            List<int[]> arr = new ArrayList<int[]>();
            for(int i = 0;i < n;i++){
            	int[] subject = new int[2];
                subject[0] = sc.nextInt();
                subject[1] = sc.nextInt();
                arr.add(subject);
            }
            arr.sort(new Comparator<int[]>(){
                @Override
                public int compare(int[] is1, int[] is2){
                    return is1[1] - is2[1];
                }
            });
            int totalScore = n * avg;
            int cost = 0;
            for(int i = 0; i < arr.size(); i++) {
            	int[] cur = arr.get(i);
                if(totalScore - r < 0) {
                    cost += (totalScore - cur[0]) * cur[1];
                    break;
                }
                else {
                	cost += (r - cur[0]) * cur[1];
                    totalScore -= r;
                }               
            }
            
            System.out.printf("%d", cost);
            System.out.println();
        }
	}

}
