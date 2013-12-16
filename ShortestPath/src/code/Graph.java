package code;

import java.util.List;

public class Graph 
{
	List<Vertex> Vertices;
	int numOfVertices;
	int T;
	
	Graph(List<Vertex> Vertices, int numOfVertices)
	{
		this.Vertices = Vertices;
		this.numOfVertices = numOfVertices;
		
		T=200;
	}
	
	//------------------------------ Calculate Optimal Solution (Begin)--------------------------------------//
	int minimalTime(Vertex src, Vertex dst, int time)
	{		
		initVertices();
		
		/*
		 *  Now, DFS searching approach
		 * 
		 *  calculateMinimalTime()
		 *  For every vertex v, we check whether v's neighbor, u
		 *  
		 *    Base Case: If u is the src, we use T and the similar method in initVertices() to calculate
		 *    			 the waiting time from u to v, mark down this value in the v.pathWeight table
		 *    			 which contain the minimal waiting time from u to v and which time state is used in u
		 *    Recursive Case: If u is not the src, we then travel u's neighbors by calling itself again calculateMinimalTime()
		 *					After examining all u, all u.pathWeight table should be set, these table helps us to know that,
		 *					from the src to u, what is the minimal waiting time from s to u and which time state is used in u
		 *
		 *					Hence, based on u.pathWeight and v.edgesWeight, we can know the minimal waiting time from s to v and
		 *					which time state is used. Store these info in v.pathWeight.
		 */
		
		/*	Now, return the minimalTime from src to dst, or output the shortest path here
		 *  Given the dst, we simply return dst.pathWeight[src.ID]
		 */
		
		return 0;
	}
	
	
	/*
	 *  No matter which start time is given, if the Vertex v is on the path, we should know which of its time
	 *  state is chosen, and then calculate the waiting time to travel to its neighbor u
	 *  
	 *  This function try to calculate all possible waiting time from v to its neighbor
	 *  and store it in the vertex v.edgesWeight
	 */
	private void initVertices()
	{
		int i,j;
		
		for(Vertex v:Vertices)
		{
			// Get the Edge Info, i: time Stamp, j: neighbor
			v.edgesWeight = new int[v.schedule.size()][];
			for(i=0; i<v.schedule.size(); i++)
				v.edgesWeight[i] = new int[numOfVertices];
			
			for(i=0; i<v.schedule.size(); i++)
			{
				for(j=0; j<numOfVertices; j++)
				{
					for(Vertex u:v.neighbors)
					{
						if(u.ID == j)
						{
							//calculate all possible waiting time from v to its neighbor and store it in the vertex v.edgesWeight
//							int prev;
							for(int k=0; k<u.schedule.size(); k++)
							{
//								prev = k;
								if(v.schedule.get(i) > u.schedule.get(k) && v.schedule.get(i) > u.schedule.get(k+1))
								{
									
								}
								
//								temp = k;
							}
							
							
							
//							v.edgesWeight[i][j] =
									
									
									
						}
//						else	
//							v.edgesWeight[i][j] = Integer.NEGATIVE_INFINITY;
					}
				}
			}
		}
	}
	//------------------------------ Calculate Optimal Solution (End)--------------------------------------//
	
}
