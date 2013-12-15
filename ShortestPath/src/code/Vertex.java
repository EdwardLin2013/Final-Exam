package code;

public class Vertex 
{
	int ID;
	String Name;
	
	double key;			// Based on the algorithm for comparison, e.g. DFS the search time
	Vertex parent;		// Previous Vertex
	Vertex next;		// Next Vertex
	
	double finishTime;	// For DFS 
	Color color;		// For DFS
	
	Vertex()
	{}
	
	Vertex(int ID, boolean isPositiveInfinity)
	{
		this.ID = ID;
		
		if(isPositiveInfinity)
			key = Double.POSITIVE_INFINITY;
		else
			key = Double.NEGATIVE_INFINITY;
		
		parent = null;
	}
	
	boolean isEqual(int ID)
	{
		if(this.ID == ID)
			return true;
		else
			return false;
	}
	boolean isEqual(Vertex v)
	{
		if(this.ID == v.ID)
			return true;
		else
			return false;		
	}
	
	void copy(Vertex from)
	{
		this.ID = from.ID;
		this.key = from.key;
		this.parent = from.parent;
	}
}
