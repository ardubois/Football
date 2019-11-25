package Football;

import java.util.*;

class Team {

	int attackTotal =0;
	int middleTotal =0;
	int defenceTotal = 0;
	int gkTotal =0;
	
	int nAttack = 2;
	int nMiddle = 4;
	int nDefence = 4;
	
	
	int teamAttack=0;
	int teamMiddle=0;
	int teamDefence=0;
	int teamGK=0;

	public String name;
	public String nationality;
	public int color1, color2;

	public ArrayList<Player> attack = new ArrayList<Player>();
	public ArrayList<Player> defence = new ArrayList<Player>();
	public ArrayList<Player> gkeaper = new ArrayList<Player>();
	public ArrayList<Player> middle = new ArrayList<Player>();
	public ArrayList<Player> selling = new ArrayList<Player>();
	//public ArrayList<Player> bench = new ArrayList<Player>();
	
	public Bench bench = new Bench();
	
	public ArrayList<Player> players = new ArrayList<Player>();
	
	Team(String name, String nationality) {
		this.name = name;
		this.nationality = nationality;
	}

	Team(String name, String nationality, int deffence, int middle, int nAttack) {
		this.name = name;
		this.nationality = nationality;
		nDefence = deffence; nMiddle = middle; this.nAttack = nAttack;
	}
	
	public void setColor1(int color1) {
		this.color1 = color1;
	}

	public void setColor2(int color2) {
		this.color2 = color2;
	}
	
	public boolean checkTactics (int defence, int middle, int attack)
	{ return (defence <= defenceTotal && middle <= middleTotal && attack <= attackTotal);}
		

	
	// setTatics must be called only after a checkTactics
	
	public void setTactics (int nDefence, int nMiddle, int nAttack)
	{
		this.nDefence = nDefence;
		this.nMiddle = nMiddle;
		this.nAttack = nAttack;

		fixTeamSize();
		
	}
	
	private void fixTeamSize ()
	{
	   fixGroupSize(attack, Attack.class, nAttack);
	   fixGroupSize(middle, Middle.class, nMiddle);
	   fixGroupSize(defence, Defence.class, nDefence);
	}
	
 
	private void fixGroupSize(ArrayList<Player> arr, Class<?> pclass, int desiredSize)
	{
		if(arr.size() != desiredSize)
		{
			if (arr.size() < desiredSize)
			{
				do {
					arr.add(bench.getBestPlayer(pclass));
				}while(arr.size() < desiredSize);
			}else
			{
				do {
					bench.add(arr.remove(arr.size()-1));
				}while(arr.size()> desiredSize);
			}
		}
	}

	public void removePlayer(Player player) {

		
		if (player instanceof GKeaper)
			gkeaper.remove(player);
		else if (player instanceof Attack)
			attack.remove(player);
		else if (player instanceof Defence)
			defence.remove(player);
		else if (player instanceof Middle)
			middle.remove(player);
	}

	public void addPlayer(Player player) {
         players.add(player);
		
		if (player instanceof GKeaper)
			{ addToPosition(gkeaper,player,1); gkTotal ++;}
		if (player instanceof Attack)
			{ addToPosition(attack,player,nAttack); attackTotal ++;}
		if (player instanceof Defence)
			{ addToPosition(defence,player,nDefence); defenceTotal ++;}
		if (player instanceof Middle)
			{ addToPosition(middle,player,nMiddle); middleTotal ++;}
	
	}
	
	private void addToPosition(ArrayList<Player> arr, Player player, int tactics)
	{
		int pos = 0;
		boolean found = false;
		if (arr.size ()==0)
		{
			arr.add(player);
		}else
		{
			do {
				if(arr.get(pos).force < player.force)
				{	
					if (arr.size() == tactics)
					{
						bench.add(arr.remove(pos));
						arr.add(pos,player);
					}else {arr.add(pos,player);}
					found = true;
				}else {pos ++;}
			
				
			}while (pos < arr.size() && !found);
		
			if(!found && arr.size()< tactics)
			{arr.add(player);}
			else {
				if(!found && arr.size()== tactics)
					{bench.add(player);}
			}
			
		}
		
	}	
public void calculateForces()
	{
		teamAttack=0;
		teamMiddle=0;
		teamDefence=0;
		teamGK=0;

			 	
		teamGK = gkeaper.get(0).force;
	    for(int i=0;i< attack.size();i++)
	          teamAttack += attack.get(i).force;

		for(int i=0;i< middle.size();i++)
	          teamMiddle += middle.get(i).force;
	 	
		for(int i=0;i< defence.size();i++)
	          teamDefence += defence.get(i).force;

	}

	public String toString() {
		String result = "";
		result = this.name;
//		result += name + "\t" + nationality + "\n";
//		result += "DEFENSE\n";
//		for (int i = 0; i < defence.size(); i++)
//			result += defence.get(i);
//
//		result += "MIDDLE\n";
//		for (int i = 0; i < middle.size(); i++)
//			result += middle.get(i);
//
//		result += "ATTACK\n";
//		for (int i = 0; i < attack.size(); i++)
//			result += attack.get(i);
//		
//		result += "BENCH\n";
//		for (int i = 0; i < bench.size(); i++)
//			result += bench.get(i);
		return result;
	}
}
