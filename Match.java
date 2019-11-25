package Football;

import java.util.Random;

class Match {
	Team home;
	Team away;
	
	Team attack;
	Team defence;
       
     
	int currentTime;

	Match (Team t1, Team t2){
	home = t1;
	away = t2;	
	
	}


public void initGame()
{
	home.calculateForces();
  	away.calculateForces();
}

public void initFirstHalf()
{
	attack = home;
	defence = away;
}


public void initSecondtHalf()
{
	attack = away;
	defence = home;
}


public RoundResult gameRound()
{
	RoundResult r = teamAttack(attack,defence);

	if(r instanceof Score)
	    System.out.println(attack.name + ": " + ((Score) r).player.name);
	
	Team temp = defence;
	defence = attack;
	attack = temp;
	
	return r;
}


	
	
public void gameOn()
{
  	home.calculateForces();
  	away.calculateForces();
  	
	System.out.println("GAME\nFirst Half\n");
	attack = home;
	defence = away;
	Team temp;
	RoundResult r;
	for(int i = 0; i < 45; i++)
	{
		//System.out.println("Min: "+i);
	    r = teamAttack(attack,defence);

		if(r instanceof Score)
		    System.out.println(attack.name + ": " + ((Score)r).player.name);
		
		temp = defence;
		defence = attack;
		attack = temp;
	}

	System.out.println("Second Half\n");
    
	attack = away;
	defence = home;
	
	for(int i = 0; i < 45; i++)
	{
		//System.out.println("Min: "+i);
		r = teamAttack(attack,defence);

		if(r instanceof Score)
		    System.out.println(attack.name + ": " + ((Score)r).player.name);
		
		temp = defence;
		defence = attack;
		attack = temp;
	}



}


public int genLuckyNumber(int force1, int force2)
{

	int number = Math.abs(force1 - force2);
	if (number == 0)
		return 2;

	return 2*number;

}

public RoundResult teamAttack(Team t1, Team t2)
{

	Random rand = new Random();
	
	int middleLucky = genLuckyNumber(t1.teamMiddle, t2.teamMiddle);

	int dice1 = rand.nextInt(middleLucky)+1;
	int dice2 = rand.nextInt(middleLucky)+1;

	if ( (t1.teamMiddle + dice1) > (t2.teamMiddle + dice2) )
	{
		int attackLucky = genLuckyNumber(t1.teamAttack,t2.teamDefence);
		
		dice1 = rand.nextInt(attackLucky)+1;
		dice2 = rand.nextInt(attackLucky)+1;
		if ( (t1.teamAttack + dice1) > (t2.teamDefence + dice2) )
		{
		  Player p = choosePlayerToKick(t1);
		  int gkLucky = genLuckyNumber(p.force ,t2.teamGK);
			
		  dice1 = rand.nextInt(gkLucky)+1;
		  dice2 = rand.nextInt(gkLucky)+1;
		  if ( (p.force + dice1) > (t2.teamGK + dice2))
 		     return new Score (t1,p);
		  else return null;	
                }
	 	else return null;
	
	}
	
	return null;
}

public Player choosePlayerToKick(Team t)
{
	
	Random rand = new Random();
	int chance = rand.nextInt(10)+1;
	Player result = null;
	if (chance <=8)
	{
		int cPlayer = rand.nextInt(t.attack.size());
                result = t.attack.get(cPlayer);
	}
	if (chance == 9)
	{
		int cPlayer = rand.nextInt(t.middle.size());
                result = t.middle.get(cPlayer);
	}
	if (chance == 10)
	{
		int cPlayer = rand.nextInt(t.defence.size());
                result = t.defence.get(cPlayer);
	}
 return result;
}

}
