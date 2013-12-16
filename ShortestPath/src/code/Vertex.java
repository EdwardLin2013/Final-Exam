package code;

import java.util.ArrayList;

public class Vertex 
{
	int ID;
	
	ArrayList<Vertex> neighbors;
	ArrayList<Integer> schedule;
	
	int edgesWeight[][];				// // Get the Edge Info, i: time Stamp, j: neighbor
	int pathWeight[][];
	
	Vertex(int ID)
	{
		this.ID = ID;
		
		neighbors = new ArrayList<Vertex>();
		schedule = new ArrayList<Integer>();
	}
}
