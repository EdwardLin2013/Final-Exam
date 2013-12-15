package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue
{
	int heapSize;
	List<Vertex> Vertices;
	
	@SuppressWarnings("unchecked")
	PriorityQueue(ArrayList<Vertex> copyVertices)
	{
		heapSize = copyVertices.size();		
		this.Vertices = (List<Vertex>) copyVertices.clone();

		buildMinHeap();
	}
	
	public boolean isEmpty()
	{
		if(heapSize == 0)
			return true;
		else
			return false;
	}
	
	public void insert(Vertex newVertex, Double newKey)
	{
		heapSize++;
		newVertex.key = Double.NEGATIVE_INFINITY;
		Vertices.add(newVertex);
		decreaseKey(heapSize-1, newKey);
	}
	
	public Vertex minimum()
	{
		return Vertices.get(0);
	}
	
	public Vertex extractMin()
	{
		if(heapSize < 1)
		{
			System.out.println("heapSize is " + heapSize + " and is less than 1!");
			return null;
		}
		else
		{
			Vertex min = Vertices.remove(0);
			heapSize--;
			minHeapify(Vertices, 0);
			
			return min;
		}
	}
	
	public void decreaseKey(int i, Double newKey)
	{
		if(newKey > Vertices.get(i).key)
			System.out.println("New key is " + newKey + " and is larger than current key " + Vertices.get(i).key);
		else
		{
			Vertices.get(i).key = newKey; 
			
			while(i>0 && Vertices.get(parent(i)).key<Vertices.get(i).key)
			{
				Collections.swap(Vertices, i, parent(i));
				i = parent(i);
			}		
		}
	}
	
	private void buildMinHeap() 
	{
		for(int i=Vertices.size()/2; i>-1; i--)
			minHeapify(Vertices, i);
	}
	
	private void minHeapify(List<Vertex> Vertices, int i) 
	{
		int l = left(i);
		int r = right(i);
		int smallest = 0;
		
		if( l<heapSize )
		{
			if( Vertices.get(l).key < Vertices.get(i).key )
				smallest = l;
			else
				smallest = i;
		}
		else
			smallest = i;
		
		if ( r<heapSize )
		{
			if( Vertices.get(r).key < Vertices.get(smallest).key)
				smallest = r;
		}
		
		if( smallest != i )
		{
			Collections.swap(Vertices, i, smallest);
			minHeapify(Vertices, smallest);
		}
	}
	private int left(int i) 
	{
		return i*2+1;
	}
	
	private int right(int i) 
	{
		return i*2+2;
	}
	private int parent(int i)
	{
		return i/2;
	}
}
