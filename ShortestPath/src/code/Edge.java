package code;

public class Edge 
{
	Vertex src;
	Vertex dest;
	Double weight;
	
	Edge(Vertex src, Vertex dest, Double weight)
	{
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
}
