package Football;

import java.util.ArrayList;
import java.util.Hashtable;

public class Tournament {
	
	ArrayList<Team> league;
	Match [] games;
	
	Tournament(ArrayList<Team> league)
	{
		this.league = league;
		this.games = new Match[(int) Math.pow(league.size(),2)];
	}
	
	
	public void genGames()
	{
		ArrayList<Team> teamsqueue = league.clone();
		Hashtable<Team, ArrayList<Team>> teamsQ;
		teamsQ = genHash();
	}
	
	public Hashtable<Team, ArrayList<Team>> genHash()
	{
		
		Hashtable<Team, ArrayList<Team>> hash
	     = new Hashtable<Team, ArrayList<Team>>();
		
		for (int i =0;i<league.size();i++)
		{
			hash.put(league.get(i), (league.clone()).remove(league.get(i)));
		}
		return hash;
	}
	
	/*
	public void genGames()
	{
		int step = league.size();
		int loc;
		for (int i=0; i< league.size(); i++)
		{
			
			loc = 0;
			for (int j=i; j < league.size(); j++)
			{
				
				games[loc+i]= new Match(league.get(i),league.get(j));
				loc += step;
				
				
			}
			
				
		}
	}
	*/
	public String toString()
	{  String result = "";
		for(int i=0; i< games.length-20;i++)
		{
			result = result + "("+ games[i].home+ ","+games[i].away + ")" +"\n";
		}
		return result;
	}

}
