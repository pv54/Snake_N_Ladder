import java.util.*;
public class Start_Game {
    
   public static void main(String args[]){
	int Rank=0;   
	Map<Integer,String> Result=new HashMap<>();
	System.out.println("====================================");
	System.out.println("            SNAKE N LADDER          ");
	System.out.println("====================================");
	
	Scanner sc= new Scanner(System.in);
	System.out.print("Enter the Size of Board(Eg: 25,50,100,etc..): ");
	int s=sc.nextInt();
	Board bo=new Board(s);
    
	System.out.print("Enter the number of players wanted to play: ");
	int n=sc.nextInt();
	for(int l=0;l<n;l++){
		System.out.println("Enter "+(l+1)+"st player Name: ");
		String p=sc.next();
		bo.Current_Player.offer(new Player(p));
		bo.Current_Position.put( p,0);
		
	}
	
	List<Snake_Ladder_Positions> snakes=new ArrayList<Snake_Ladder_Positions>();
	snakes.add(new Snake_Ladder_Positions(7,3));
	snakes.add(new Snake_Ladder_Positions(13,9));
	snakes.add(new Snake_Ladder_Positions(24,2));
	snakes.add(new Snake_Ladder_Positions(20,15));
	List<Snake_Ladder_Positions> ladder=new ArrayList<Snake_Ladder_Positions>();
	ladder.add(new Snake_Ladder_Positions(4,15));
	ladder.add(new Snake_Ladder_Positions(11,17));
	ladder.add(new Snake_Ladder_Positions(5,16));
	ladder.add(new Snake_Ladder_Positions(18,23));

   System.out.println("====================================");
   System.out.println("            GAME STARTED            ");
   System.out.println("====================================");
   
	while(bo.Current_Player.size()>1) {
		Player player=(bo.Current_Player.poll());
		Dice dice=new Dice();
		int currentPosition=bo.Current_Position.get(player.extract_pName());
		int diceValue=dice.DiceRandom();
		System.out.println("<<< "+player.extract_pName()+"'s Turn >>>");
		//System.out.println();
	    System.out.println("Dice Value: "+diceValue);
		int new_Block=currentPosition+diceValue;
		if (new_Block>bo.size) {bo.Current_Player.offer(player);System.out.println("-------------------------------------");}
		else if(new_Block==bo.size) { Rank++;
			System.out.println(player.extract_pName()+" Reached at Position "+bo.size);
			if (Rank==1){
			System.out.println("====================================");
             System.out.println(" Winner: "+player.extract_pName());
             System.out.println("====================================");
			 Result.put(1,player.extract_pName());
			}
			else {Result.put(Rank,player.extract_pName());}

		}
		else {
			int[] nextPosition=new int[1];
			boolean[]b=new boolean[1];
			nextPosition[0]=new_Block;
			snakes.forEach(v->{
				if(v.start==new_Block){
					nextPosition[0]=v.end;
					b[0]=true;
		}});
			int[] LnextPosition=new int[1];
			boolean[]a=new boolean[1];
			LnextPosition[0]=new_Block;
			ladder.forEach(v->{
				if(v.start==new_Block){
					LnextPosition[0]=v.end;
					a[0]=true;
		}});
		if(nextPosition[0]!=new_Block && b[0]) 
		{System.out.println(player.extract_pName() + " Bitten by snake present at block "+new_Block );}
		
		if (LnextPosition[0]!=new_Block&& a[0])
		{System.out.println(player.extract_pName() + " Climbed a ladder present at block "+new_Block );}
		int check=0;
		if(nextPosition[0]==bo.size) 
		{    Rank++;
			System.out.println(player.extract_pName()+" Reached at Position "+bo.size);
			if (Rank==1){
				System.out.println("====================================");
				 System.out.println(" Winner: "+player.extract_pName());
				 System.out.println("====================================");
				 Result.put(1,player.extract_pName());
				}
				else {Result.put(Rank,player.extract_pName());}

		}
		else {
			bo.Current_Position.put(player.extract_pName(),nextPosition[0]);
		    System.out.println(player.extract_pName()+" is at position "+nextPosition[0]);
			System.out.println("-------------------------------------");
  
		    bo.Current_Player.offer(player);
			check=1;
		
		}
		if(LnextPosition[0]==bo.size)
		{ Rank++;
			System.out.println(player.extract_pName()+" Reached at Position "+bo.size);
			if (Rank==1){
				System.out.println("====================================");
				 System.out.println(" Winner: "+player.extract_pName());
				 System.out.println("====================================");
				 Result.put(1,player.extract_pName());
				}
	
		}
		else if(check==0) {
			bo.Current_Position.put(player.extract_pName(),LnextPosition[0]);
		    System.out.println(player.extract_pName()+" is at position "+LnextPosition[0]);
			System.out.println("-------------------------------------");
		    bo.Current_Player.offer(player);
		
		}
		else {Result.put(Rank,player.extract_pName());}
		
		}
		
	}
sc.close();
	System.out.println("\n||============[LEADERBOARD]============||");
	System.out.println("  << Winner: "+Result.get(1)+" >>");
	for(int r=2;r<n;r++){System.out.println("  << "+r+"- "+Result.get(r)+" >>");}
	Player player=(bo.Current_Player.poll());
	System.out.println("  << "+n+"- "+player.extract_pName()+" >>");
	System.out.println("||=====================================||");


   }


}
