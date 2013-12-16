package code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DemoShortestPath 
{
	public static void main(String[] args) throws IOException
	{
		if(args.length != 2 || args[0].equalsIgnoreCase("help"))
		{
			System.out.println("Argument is empty! Please provide the path of the input.txt");
			System.out.println("DemoShortestPath Path/to/AdjList.txt Path/to/Schedule.txt");
			return;
		}

		// temp and loop variable
		int i, j, numOfNeighbor, numOfState;
		String line;
		String[] strVertices = null;
		String[] strSchedule = null;
		
		// Input Parameters
		Scanner getAdjListNode = new Scanner(new File(args[0]));
		Scanner getAdjListInfo = new Scanner(new File(args[0]));
		Scanner getSchedule = new Scanner(new File(args[1]));
				
		// Graph Parameters
		ArrayList<Vertex> inputVertices = new ArrayList<Vertex>();
		int numOfVertices;
		
		//------------------------------ Import Graph Info From AdjList (Begin)--------------------------------------//
		// Get the Vertices ID first, store as the first element in each line
		while (getAdjListNode.hasNextLine())
		{
			line = getAdjListNode.nextLine();
			
			// <V-ID> <Adj-V-ID> <Weight?> <Adj-V-ID> <Weight?> .... 
			strVertices = line.split("\\s+");

			Vertex newVertex = new Vertex(Integer.parseInt(strVertices[0]));
			
			inputVertices.add(newVertex);
		}
		getAdjListNode.close();
		numOfVertices = inputVertices.size();
		System.out.println("numOfVertices: " + numOfVertices);		

		// Get the Vertices's neighbors
		i=0;
		while (getAdjListInfo.hasNextLine())
		{
			line = getAdjListInfo.nextLine();
			
			// <V-ID> <Numer of Neighbor> <Adj-V-ID-1> <Adj-V-ID-2> ...
			strVertices = line.split("\\s+");

			numOfNeighbor = Integer.parseInt(strVertices[1]);
			System.out.println("ID:" + inputVertices.get(i).ID + "; numOfNeighbor: " + numOfNeighbor);
			for(j=0; j<numOfNeighbor; j++)
			{
				inputVertices.get(i).neighbors.add(inputVertices.get(Integer.parseInt(strVertices[j+2])));
			}

			System.out.print("its neighbor: ");
			for(Vertex v:inputVertices.get(i).neighbors)
				System.out.print(v.ID + " ");
			System.out.print("\n");
			
			i++;
		}
		getAdjListInfo.close();


		// Get the Vertex's Schedule
		i=0;
		while (getSchedule.hasNextLine())
		{
			line = getSchedule.nextLine();
			
			// <V-ID> <Numer of States> <time-1> <time-2> ...
			strSchedule = line.split("\\s+");

			numOfState = Integer.parseInt(strSchedule[1]);
			System.out.println("ID:" + inputVertices.get(i).ID + "; numOfState: " + numOfState);
			for(j=0; j<numOfState; j++)
			{
				inputVertices.get(i).schedule.add(Integer.parseInt(strSchedule[j+2]));
			}

			System.out.print("its schedule: ");
			for(int v:inputVertices.get(i).schedule)
				System.out.print(v + " ");
			System.out.print("\n");
			
			i++;
		}
		getSchedule.close();		
		//------------------------------ Import Graph Info From AdjList (End)--------------------------------------//
	
		//------------------------------ Calculate Optimal Solution (Begin)--------------------------------------//
		
		
		//------------------------------ Calculate Optimal Solution (End)--------------------------------------//		
	}
}
