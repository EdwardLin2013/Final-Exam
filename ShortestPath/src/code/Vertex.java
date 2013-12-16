package code;

import java.util.ArrayList;

public class Vertex 
{
	int ID;
	
	ArrayList<Vertex> neighbors;
	ArrayList<Integer> schedule;
	
	Vertex(int ID)
	{
		this.ID = ID;
		
		neighbors = new ArrayList<Vertex>();
		schedule = new ArrayList<Integer>();
	}
}
