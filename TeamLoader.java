package Football;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class TeamLoader {
	
	BufferedReader file;

	TeamLoader(String fileName)
	{
		try {
		FileInputStream teams = new FileInputStream(fileName);
	    file = new BufferedReader (new InputStreamReader(teams));
		} catch (Exception e) {System.out.println("Exception :"+e );}
	}


public void getLeagues (ArrayList<Team> league1, ArrayList<Team> league2, ArrayList<Team> league3, ArrayList<Team> league4)
{
	 getLeague(league1,8);
	 getLeague(league2,8);
	 getLeague(league3,8);
	 getLeague(league4,8);
		
}
	
public void getLeague(ArrayList<Team> league, int numberTeams)
{
	
	for (int i=0; i< numberTeams;i++)
	{
		league.add(getTeam());
	}
	
}
	
public Team getTeam ()
{
		Team team=null;
		Player player;
	    String line;
		StringTokenizer tokens;
	
		try{

			
			do{
				   
				   line = file.readLine();
		     	   System.out.println("Ok5" +line+"!");
			}while(line.equals(""));
				   				
        if(line != null)
        {  
		  System.out.println ("OK4");

		  tokens = new StringTokenizer(line);
		  team = createTeam(tokens);
		  System.out.println ("OK1");
		  setColors(team);
   		  System.out.println ("OK3");
		  //temp2 = getTeamName (tokens);
         // System.out.println("Team name: " + temp2);
		  for (int i=0;i<16;i++)
		  {
		  	player = getPlayer();
			team.addPlayer(player);
		  }
        }	  
		}catch (Exception e)
		{System.out.println("Exception!" + e);}
		return team;
	}

public  Player getPlayer() throws IOException
	{
	  Player player = null;
	  String name,type, nationality;
	  StringTokenizer tokens= new StringTokenizer(file.readLine());
	  	//System.out.println("tokens:"+ tokens);
	  int i, nt = tokens.countTokens();
	  name = tokens.nextToken();
	  nt = nt -3;
	  for(i=0;i<nt;i++)
	  {
	  	name = " " + tokens.nextToken();
	  }
	  type = tokens.nextToken();
	  nationality = tokens.nextToken();

		// for the force, pick up a number between 9 and 13

          Random rand = new Random();
          int force = rand.nextInt(5)+9;
          



	  if (type.equals("gr"))
	  	player = new GKeaper(force,name,nationality);
	  else if ( type.equals("md"))
	    	player = new Middle(force,name,nationality);
	   else if ( type.equals("df"))
	   	     player = new Defence(force,name,nationality);
	    else if (type.equals("av"))
	       	 player = new Attack(force,name,nationality);
	      else
	       System.out.println("AYBABTU: Strange player:" + type + "!");

	  return player;
	  }


	public String getTeamName(StringTokenizer tokens)
	{
		String teamName;
		int i,noftokens = tokens.countTokens();
		teamName = tokens.nextToken();
		for(i = 2; i<noftokens; i++)
		{
		   teamName = teamName + " " + tokens.nextToken();
		}
		return teamName;
	}

   public void setColors (Team team) throws IOException
	{
	  int color1, color2;
	  color1 = Integer.parseInt(file.readLine().trim());
	  color2 = Integer.parseInt(file.readLine().trim());
	  team.setColor1(color1);
	  team.setColor2(color2);
	}
	public Team createTeam (StringTokenizer tokens)
	{
	 String teamname;
	 String nationality;

	 teamname = getTeamName(tokens);
	 nationality = tokens.nextToken();

	 return (new Team(teamname, nationality));
	}
	
}
