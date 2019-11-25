package Football;

class Player{

	public int force;
	public String nationality;
	public String name;
	
	Player (int force, String name, String nationality)
	{
		this.force = force;
		this.name = name;
		this.nationality = nationality;
	}
	
	public String toString()
	{
		return (name + "\t" + nationality + "\t" + force+"\n");
	}
}
