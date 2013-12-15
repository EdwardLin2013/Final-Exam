package code;

//import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Graph 
{
//	List<String> matchingID;
	List<Vertex> Vertices;
	double Edges[][];
	int numOfVertices;
	
	int time;					// For DFS
	
	Graph(List<Vertex> Vertices, double[][] Edges)
	{
		this.Vertices = Vertices;
		this.Edges = Edges;
		
		numOfVertices = Vertices.size();
	}

	/*
	Graph(List<Vertex> Vertices, double[][] Edges, List<String> matchingID)
	{
		this.Vertices = Vertices;
		this.Edges = Edges;
		this.matchingID = matchingID;
		
		numOfVertices = Vertices.size();
	}
	*/
	
	public Vertex getVertex(int ID)
	{
		int i;
		
		for(i=0; i<numOfVertices; i++)
		{
			if(Vertices.get(i).isEqual(ID))
				return Vertices.get(i);
		}
		
		return null;
	}
	
	//----------------Longest Simple Path in Directed Acyclic Graph (Begin)--------------------------------//
	public void Longest_Path_Main(Vertex s, Vertex t)
	{
		initVertices();
		Longest_Path_Aux(s, t);
	}
	private void initVertices()
	{
		for(Vertex v:Vertices)
		{
			v.key = Double.NEGATIVE_INFINITY;
			v.next = null;
		}
	}
	public void Longest_Path_Aux(Vertex u, Vertex t)
	{
		if(u.isEqual(t))
			u.key = 0;
		else if (u.next!= null)
			return;
		else
		{
			for(Vertex v:AdjVertices(u))
			{
				Longest_Path_Aux(v, t);
				if( u.key < (v.key + Edges[u.ID][v.ID]) )
				{
					u.key = v.key + Edges[u.ID][v.ID];
					u.next = v;
				}
			}
		}
	}
//	public void printLongest_Path(Vertex s, Vertex t, OutputStreamWriter out)
	public void printLongest_Path(Vertex s, Vertex t)
	{
		Vertex u = s;
		System.out.print(u.ID + " ");
		
		while(!u.isEqual(t))
		{
			System.out.print(u.next.ID + " ");
			u = u.next;
		}
	}
	//----------------Longest Simple Path in Directed Acyclic Graph (End)--------------------------------//	

	
	//------------------------------All-Pairs Shortest Paths (Begin)--------------------------------------//
	public double[][] slowAPSP()
	{
		int i, j, m;
		double nxtL[][];
		double prevL[][] = new double[numOfVertices][];
		for(i=0; i<numOfVertices; i++)
			prevL[i] = new double[numOfVertices];
		
		for(i=0; i<numOfVertices; i++)
			for(j=0; j<numOfVertices; j++)
				prevL[i][j] = Edges[i][j];
			
		for(m=1; m<numOfVertices-1; m++)
		{
			nxtL = extend(prevL);
			
			for(i=0; i<numOfVertices; i++)
				for(j=0; j<numOfVertices; j++)
					prevL[i][j] = nxtL[i][j];
		}
					
		return prevL;
	}
	private double[][] extend(double[][] L)
	{
		int i, j, k;
		double nxtL[][] = new double[numOfVertices][];
		for(i=0; i<numOfVertices; i++)
			nxtL[i] = new double[numOfVertices];
		
		for(i=0; i<numOfVertices; i++)
		{
			for(j=0; j<numOfVertices; j++)
			{
				nxtL[i][j] = Double.POSITIVE_INFINITY;
				for(k=0; k<numOfVertices; k++)
				{
					if(nxtL[i][j] > L[i][k] + Edges[k][j])
						nxtL[i][j] = L[i][k] + Edges[k][j];
				}
			}
		}

		return nxtL;		
	}
	//------------------------------All-Pairs Shortest Paths (END)--------------------------------------//
	
	//-------------------------------------Dijkstra (Begin)--------------------------------------//
	public List<Vertex> Dijkstra(Vertex src)
	{
		initSingleSource(src);
	
		ArrayList<Vertex> S = new ArrayList<Vertex>();
		PriorityQueue Q = new PriorityQueue((ArrayList<Vertex>) Vertices);
		
		while(!Q.isEmpty())
		{
			Vertex u = Q.extractMin();
			S.add(u);	
			for(Vertex v:AdjVertices(u))
				relax(u,v);
		}

		return S;
	}
	// Edge Must have positive weight and less than positive infinite
	private List<Vertex> AdjVertices(Vertex u)
	{
		ArrayList<Vertex> ret = new ArrayList<Vertex>();
		int i;
		
		System.out.print("u: " + u.ID + "'s adj are:");
		for(i=0; i<numOfVertices; i++)
		{
			if(Edges[u.ID][i]>0 && Edges[u.ID][i]<Double.POSITIVE_INFINITY)
			{
				System.out.print(Vertices.get(i).ID + " ");
				ret.add(Vertices.get(i));
			}
		}
		System.out.print("\n");
		
		return ret;
	}
	private void initSingleSource(Vertex src)
	{
		for(Vertex v:Vertices)
		{
			v.key = Double.POSITIVE_INFINITY;
			v.parent = null;
		}
		src.key = 0;
	}
	
	private void relax(Vertex u, Vertex v)
	{
		if(v.key > u.key + Edges[u.ID][v.ID])
		{
			v.key = u.key + Edges[u.ID][v.ID];
			v.parent = u;
		}
	}
	//-------------------------------------Dijkstra (End)--------------------------------------//

	//-------------------------------------Topological_Sort_DAT (Begin)--------------------------------------//
	@SuppressWarnings("unchecked")
	public List<Vertex> Topological_Sort_DAT()
	{
		QuickSort engine = new QuickSort();
		DFS();
		
		ArrayList<Vertex> SortedVectices = (ArrayList<Vertex>) ((ArrayList<Vertex>)Vertices).clone();
		
		return engine.sort(SortedVectices, 0, SortedVectices.size()-1);
	}
	//-------------------------------------Topological_Sort_DAT (END)--------------------------------------//
	
	//-------------------------------------DFS (Begin)--------------------------------------//
	public void DFS()
	{
		for(Vertex v:Vertices)
		{
			v.color = Color.WHITE;
			v.parent = null;
		}
		
		time = 0;
		
		for(Vertex u:Vertices)
		{
			// If Graph is connected, DFS_Visit(u) just executed once!
			if(u.color == Color.WHITE)
				DFS_Visit(u);
		}
	}
	private void DFS_Visit(Vertex u)
	{
		time++;
		u.key = time;
		u.color = Color.GREY;				// discover u
		
		// Explore (u,v)
		for(Vertex v:AdjVertices(u))
		{
			if(v.color == Color.WHITE)
			{
				v.parent = u;
				DFS_Visit(v);
			}
			//
			/*
			else if(v.ID != u.parent.ID && 
			{
			
			}
			*/
		}
		
		u.color = Color.BLACK;
		time++;
		u.finishTime = time;				// finish u
		
	}
	//-------------------------------------DFS (END)--------------------------------------//
	
	public void printAdjacencyMatrix()
	{
		int i, j;
		
		for(i=0; i<numOfVertices; i++)
		{
			for(j=0; j<numOfVertices; j++)
				System.out.print(Edges[i][j] + " ");
			System.out.print("\n");
		}
	}
}
