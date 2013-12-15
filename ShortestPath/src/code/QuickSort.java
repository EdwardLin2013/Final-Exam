package code;

import java.util.Collections;
import java.util.List;

public class QuickSort 
{

	public QuickSort() 
	{
		// TODO Auto-generated constructor stub
	}

	public List<Vertex> sort(List<Vertex> inputArray, int p, int r)
	{
		int q=0;
		if(p<r)
		{
//			q = partition(inputArray, p, r);
			q = randomizedPartition(inputArray, p, r);
			this.sort(inputArray, p, q-1);
			this.sort(inputArray, q+1, r);
		}
		return inputArray;
	}

	private int randomizedPartition(List<Vertex> inputArray, int p, int r)
	{
		//Math.random() generates a double value in the range [0,1)
		int i = p + (int)(Math.random() * ((r - p) + 1));
		Collections.swap(inputArray, i, r);
		return partition(inputArray, p, r);
	}
	
	private int partition(List<Vertex> inputArray, int p, int r) 
	{
		Vertex pivot = inputArray.get(r);
		int i = p-1;
		for(int j=p; j<r; j++)
		{
			if(inputArray.get(j).finishTime  > pivot.finishTime )
			{
				i++;
				Collections.swap(inputArray, i, j);
			}
		}
		Collections.swap(inputArray, i+1, r);
		
		return i+1;
	}
}
