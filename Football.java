package Football;

import java.io.*;
import java.util.*;
import java.util.Random;
class Football{
	final static int MAXTEAM = 16;
	public static void main (String[] args)
	{
//	   	ArrayList<Team> allteams = new ArrayList<Team>();


		//getTeams (allteams,"teams.foot.txt");
	//	getTeams (allteams,"/home/andre/eclipse-workspace/Football/bin/Football/EQUIPAS.EF2");
		//Match mtest = new Match (allteams.get(0),allteams.get(1));
		 
		//System.out.println(allteams.get(0));
		//System.out.println(allteams.get(1));
		
		ArrayList<Team> league1 = new ArrayList<Team>();
		ArrayList<Team> league2 = new ArrayList<Team>();
		ArrayList<Team> league3 = new ArrayList<Team>();
		ArrayList<Team> league4 = new ArrayList<Team>();
	     
		
	   TeamLoader loader = new TeamLoader("/home/andre/eclipse-workspace/Football/bin/Football/EQUIPAS.EF2");
		
	   
	   loader.getLeague(league1,8);
	   Tournament t = new Tournament(league1);
	   t.genGames();
	   System.out.println(t);
	   int i = 0;
	   
	   league1.get(0).setTactics(5 ,1, 3);
	   
	  
	   System.out.println(league1.get(i) 
	    		 + "\n size attack:"+ league1.get(i).attack.size()
	    		 + "\n size middle:"+ league1.get(i).middle.size()
	    		 + "\n size defence:"+ league1.get(i).defence.size()
	    		 + "\n size gk:"+ league1.get(i).gkeaper.size()
	    		 + "\n size bench:"+ league1.get(i).bench.size()
	    		 );
	   
	   /*
		loader.getLeagues(league1, league2, league3, league4);
		for (int i =0;i< league1.size();i++) {
			System.out.println(league1.get(i).name);
		    for(int j=0;j<league1.get(i).players.size();j++)
		    	 System.out.println("pos: " + j + league1.get(i).players.get(j).nationality);
		}
		
		int i=0;
	     System.out.println(league1.get(i) 
	    		 + "\n size attack:"+ league1.get(i).attack.size()
	    		 + "\n size middle:"+ league1.get(i).middle.size()
	    		 + "\n size defence:"+ league1.get(i).defence.size()
	    		 + "\n size gk:"+ league1.get(i).gkeaper.size()
	    		 + "\n size bench:"+ league1.get(i).bench.size()
	    		 );
*/
		// mtest.gameOn();

	}


public static void getTeams (ArrayList<Team> allteams, String playersFile)
{
		boolean notend = false;
		int numberofteams =0;
		Team team;
		Player player;
	    String temp1;
		int i;
		StringTokenizer tokens;
		try{
		FileInputStream teams = new FileInputStream(playersFile);
	    BufferedReader f = new BufferedReader (new InputStreamReader(teams));



          temp1 = f.readLine();
		do{
		  System.out.println ("OK4");

		  tokens = new StringTokenizer(temp1);
		  team = createTeam(tokens);
		  System.out.println ("OK1");
		  setColors(team, f);
   		  System.out.println ("OK3");
		  //temp2 = getTeamName (tokens);
         // System.out.println("Team name: " + temp2);
		  for (i=0;i<16;i++)
		  {
		  	player = getPlayer(f);
			team.addPlayer(player);
		  }
		  do
		  {
		   notend=false;
		   temp1 = f.readLine();
     	   System.out.println("Ok5" +temp1+"!");
		   if(temp1==null)
		   	 notend = false;
		   else if (temp1.equals(""))
		           notend = true;
		 }while (notend);
		  allteams.add(team);
		  numberofteams ++;
		}while (temp1!=null);
		System.out.println("Number of teams " + numberofteams);
		//System.out.println ("Team1:\n" + allteams[0]); //+ "Team2:\n" + allteams[1]);
		//System.out.println(allteams);
		}catch (Exception e)
		{System.out.println("Exception!" + e);}
	}

	static Player getPlayer(BufferedReader buf) throws IOException
	{
	  Player player = null;
	  String name,type, nationality;
	  StringTokenizer tokens= new StringTokenizer(buf.readLine());
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


	static String getTeamName(StringTokenizer tokens)
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

	static void setColors (Team team, BufferedReader buf) throws IOException
	{
	  int color1, color2;
	  color1 = Integer.parseInt(buf.readLine().trim());
	  color2 = Integer.parseInt(buf.readLine().trim());
	  team.setColor1(color1);
	  team.setColor2(color2);
	}
	static Team createTeam (StringTokenizer tokens)
	{
	 String teamname;
	 String nationality;

	 teamname = getTeamName(tokens);
	 nationality = tokens.nextToken();

	 return (new Team(teamname, nationality));
	}
}
