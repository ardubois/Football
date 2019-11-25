package Football;

import java.util.*;



public class Bench {

	private ArrayList<Player> defence = new ArrayList<Player> ();
	private ArrayList<Player> middle = new ArrayList<Player> ();
	private ArrayList<Player> attack = new ArrayList<Player> ();
	private ArrayList<Player> gkeaper = new ArrayList<Player>();
	
	public ArrayList<Player> bench= new ArrayList<Player> ();
	
	
	public void add(Player player)
	{
		bench.add(player);
		if (player instanceof Defence)
		{
			addAtPosition(player,defence);
		}
		if (player instanceof Middle)
		{
			addAtPosition(player,middle);
		}
		if (player instanceof Attack)
		{
			addAtPosition(player,attack);
		}
		if (player instanceof GKeaper)
		{
			addAtPosition(player,gkeaper);
		}
	}

	public Player getBestPlayer(Class pclass)
	{System.out.println("get best plasdkjfçasdlkjfçsdkjfçasdfçajçsdjfk");
		Player result = null;
		if(pclass.getName().equals("Football.Attack"))
		{ result = attack.remove(0);}
		System.out.println(pclass.getName());
		if(pclass.getName().equals("Football.Middle"))
		{ result = middle.remove(0);}
		
		if(pclass.getName().equals("Football.Defence"))
		{ result = defence.remove(0);}
		if(result==null)
		{System.out.println("bosta!!!!!!!!!!!!!!!!!!!!!!!!!!!");}
		return result;
	}
    private void addAtPosition(Player player, ArrayList<Player> arr)
    {
    	
    	int pos = 0;
		boolean found = false;
		if (arr.size ()==0)
		{
			arr.add(player); found = true;
		}else
		{
			do {
				if(arr.get(pos).force < player.force)
				   {
					arr.add(pos,player);
					found = true;					
				   }else {pos ++;}
			
				
			}while (pos < arr.size() && !found);
		}
		
		if(!found)
			{arr.add(player);}
    }
	public int size()
	{ //return bench.size();
		return (attack.size()
				+middle.size()
				+defence.size()
				+gkeaper.size()); 
	}
	public Player get (int index)
	{
		return bench.get(index);
	}
	
	public void remove(Player player)
	{
		bench.remove(player);
	}
	public String toString()
	{
		return "";
	}
}
