import java.io.*;
import java.util.*;

public class SnakesLadder  extends AbstractSnakeLadders {
    //
	int N, M;
	int snakes[];
	int ladders[];
	int distance[];
	ArrayList<Integer> [] wor;
	int distance1[];
	ArrayList<Integer> [] wore;
	int ld1[];
	int ld2[];
	
	public SnakesLadder(String name)throws Exception{
		File file = new File(name);
		BufferedReader br = new BufferedReader(new FileReader(file));
		N = Integer.parseInt(br.readLine());
        
        M = Integer.parseInt(br.readLine());

	    snakes = new int[N];
		ladders = new int[N];
	    for (int i = 0; i < N; i++){
			snakes[i] = -1;
			ladders[i] = -1;
		}

		for(int i=0;i<M;i++){
            String e = br.readLine();
            StringTokenizer st = new StringTokenizer(e);
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

			if(source<destination){
				ladders[source] = destination;
			}
			else{
				snakes[source] = destination;
			}
        }





		distance=new int[N+1];
		wor=new ArrayList[N+1];
		for (int i=0;i<=N;i++) {
            wor[i]=new ArrayList<Integer>();
        }
		
		for(int i=0;i<N;i++){
			if(snakes[i]!=-1){
				wor[i].add(snakes[i]);
			}
			else if(ladders[i]!=-1){
				wor[i].add(ladders[i]);
			}
			else{
			int k=1;
			while(k<=6 && k+i<=N){
				wor[i].add(i+k);
				k++;
			}
			
		}
		}
		Bfsproxy(wor, 0, N, N+1);







		ld1=new int[M];
		ld2=new int[M];
		int j=0;
		for(int i=0;i<N;i++){
			if(ladders[i]!=-1){
				ld1[j]=i;
				ld2[j]=ladders[i];
				j++;
			}
			
		}







		distance1=new int[N+1];
		wore=new ArrayList[N+1];
		for (int i=0;i<=N;i++) {
            wore[i]=new ArrayList<Integer>();
        }
		int u=1;
		while(u<=6 && u<=N){
			if(snakes[N-u]!=-1){
				u++;
				continue;
			}
			wore[0].add(u);
			u++;
		}
		for(int i=1;i<N;i++){
			int k=1;
			if(snakes[N-i]!=-1){
				continue;
			}
			while(k<=6 && k+i<=N){
				if(snakes[N-i-k]!=-1){
					k++;
					continue;
				}
				wore[i].add(i+k);
				k++;
			}
		}
		for(int i=1;i<N;i++){
			if(ladders[i]!=-1){
				// wore[N-ladders[i]]=null;
				// wore[N-ladders[i]]=new ArrayList<Integer>();
				wore[N-ladders[i]].add(N-i);

			}
		}
		// for(int i=1;i<N;i++){
		// 	if(ladders[i]!=-1){
		// 		wore[N-ladders[i]]=null;
		// 		wore[N-ladders[i]]=new ArrayList<Integer>();
		// 		wore[N-ladders[i]].add(N-i);

		// 	}
		// }
		Bfsproxy2(wore, 0, N, N+1);
		//for(int i=0;i<N+1;i++){
		// 	System.out.println(distance1[i]+" "+ (N-i));
		// }
	}



	public boolean Bfsproxy2(ArrayList<Integer>[] wore,int str,int end,int size)
	{
		Queue<Integer> qu = new LinkedList<Integer>();
 		boolean visi[] = new boolean[size];
 		for (int i = 0; i < size; i++) {
			distance1[i] = 999999999;
            visi[i] = false;
        }
		visi[str]=true;
        distance1[str]=0;
        qu.add(str);
 		while (!qu.isEmpty()) {
            int u = qu.remove();
			
            for (int i = 0; i < wore[u].size(); i++) {
                if (visi[wore[u].get(i)] == false) {
                    visi[wore[u].get(i)] = true;
				    if(ladders[N-wore[u].get(i)]==N-u){
						distance1[wore[u].get(i)] = distance1[u];
					}
					else{
						distance1[wore[u].get(i)] = distance1[u]+1;
					}
					qu.add(wore[u].get(i));
 				}
            }
        }
		for(int i=0;i<N;i++){
			if(snakes[i]!=-1){
				distance1[N-i]=distance1[N-snakes[i]];
			}
		}
		for(int i=1;i<N;i++){
			if(snakes[i]!=-1){
				int g=1;
				while(g<=6 && N-i+g<=N){
					distance1[N-i+g]=min(distance1[N-i+g],distance1[N-i]+1);
					g++;
				}
			}
		}

		
		if(distance1[end]==999999999){
			return false;
		}
        return true;
	}




	


	public int min(int x,int y){
		if(x>y){
			return y;
		}
		return x;
	}
	public boolean Bfsproxy(ArrayList<Integer>[] wor,int str,int end,int size)
	{
		Queue<Integer> qu = new LinkedList<Integer>();
 		boolean visi[] = new boolean[size];
		boolean visi2[] = new boolean[size];
 		for (int i = 0; i < size; i++) {
			distance[i] = 999999999;
            visi[i] = false;
			visi2[i] = false;
        }
		visi[str]=true;
		visi2[str]=true;
        distance[str]=0;
        qu.add(str);
 		while (!qu.isEmpty()) {
            int u = qu.remove();
			int y=u;
			while(y!=N && (ladders[y]!=-1 || snakes[y]!=-1 )){
				if(visi2[wor[y].get(0)]==false){
					visi2[wor[y].get(0)]=true;
					distance[wor[y].get(0)] = min(distance[y],distance[wor[y].get(0)]);	

				y=wor[y].get(0);
				if(y==u){
					break;
				}
			}
			else{
				break;
			}
			}


            for (int i = 0; i < wor[u].size(); i++) {
                if (visi[wor[u].get(i)] == false) {
                	visi[wor[u].get(i)] = true;
					//int y=u;
					// while(ladders[y]!=-1 || snakes[y]!=-1){
					// 	distance[wor[y].get(i)] = distance[y];	

					// 	y=wor[y].get(0);
					// 	if(y==u){
					// 		break;
					// 	}
					// }
					if(ladders[u]==wor[u].get(i) || snakes[u]==wor[u].get(i)){
						distance[wor[u].get(i)] = min(distance[u],distance[wor[u].get(i)] );

					}
					else{
                    distance[wor[u].get(i)] = min(distance[u] + 1,distance[wor[u].get(i)]);
					}
					// if(ladders[u]==wor[u].get(i) || snakes[u]==wor[u].get(i)){
					// 	qu.addFirst(wor[u].get(i));
					// }
					// else{
					qu.add(wor[u].get(i));
					//}
 				}
            }
        }

		if(distance[end]==999999999){
			return false;
		}
        return true;
	}



	







    
	public int OptimalMoves()
	{
		/* Complete this function and return the minimum number of moves required to win the game. */
		if(Bfsproxy(wor, 0, N, N+1)==false){
			return -1;
		}
		// for(int i=0;i<N+1;i++){
		// System.out.println(distance[i]+" "+i);
		// }

		return distance[N];
	}






	public int Query(int x, int y)
	{
		/* Complete this function and 
			return +1 if adding a snake/ladder from x to y improves the optimal solution, 
			else return -1. */
			// if(distance[x]+1+distance1[y]<distance[N]){
			// 	return 1;
			// }
            if(distance[x]+distance1[N-y]<distance[N]){
				return 1;
			}

			return -1;
	}




	

	public int[] FindBestNewSnake()
	{
		int result[] = {-1, -1};
		/* Complete this function and 
			return (x, y) i.e the position of snake if adding it increases the optimal solution by largest value,
			if no such snake exists, return (-1, -1) */
		int best=distance[N];

		for(int i=0;i<M;i++){
			for(int j=0;j<M;j++){
				if(best>distance[ld2[i]]+distance1[N-ld2[j]] && j!=i && ld2[i]>ld1[j]){
					best=distance[ld2[i]]+distance1[N-ld2[j]];
					result[0]=ld2[i];
					result[1]=ld1[j];
				}
			}
		}
		System.out.println(result[0]+" "+ result[1]);

		return result;
	}





	public static void main(String[] args) {
        try {
            SnakesLadder sl = new SnakesLadder("input.txt");
            int optimalMoves = sl.OptimalMoves();
            System.out.println("Optimal Moves: " + optimalMoves);
			System.out.println(sl.Query(54,50));
			System.out.println(sl.FindBestNewSnake());
			
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
}