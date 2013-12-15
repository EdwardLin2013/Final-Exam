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
			System.out.println("DemoShortestPath Path/to/input.txt Path/to/output.txt");
			return;
		}
		
		// Output File Setting
		FileOutputStream fos = new FileOutputStream(args[1]);
		OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");		
		
		/*
		 *  Input File - Graph in Adjacency List
		 *  Assume vertex's ID is int, if not, change it to int!
		 *  NOTE: If ID is not int, then may need to implement the mapping (not likely, may ask to alter the input file!)
		 *  
		 *  So Far Assume the ID is {0, 1, 2, 3, .....}
		 */
		int i, j, numOfVertices;
		String line;
		String[] strVertices = null;
		Scanner scVertices = new Scanner(new File(args[0]));
		Scanner scEdges = new Scanner(new File(args[0]));
		
		// Graph Parameters
		int srcID, destID;
		ArrayList<Vertex> inputVertices = new ArrayList<Vertex>();
		
		double adjacencyMatrix[][];
		Graph myGraph;
		
		//------------------------------ Import Graph Info From Text File (Begin)--------------------------------------//
		// Get the Vertices Info first, store as the first element in each line
		while (scVertices.hasNextLine())
		{
			line = scVertices.nextLine();
			
			// <V-ID> <Adj-V-ID> <Weight?> <Adj-V-ID> <Weight?> .... 
			strVertices = line.split("\\s+");

			Vertex newVertex = new Vertex(Integer.parseInt(strVertices[0]), false);
			
			inputVertices.add(newVertex);
		}
		scVertices.close();
		numOfVertices = inputVertices.size();
		System.out.println("numOfVertices: " + numOfVertices);
		
		// Get the Edge Info, Create the Adjacency Matrix
		adjacencyMatrix = new double[numOfVertices][];
		for(i=0; i<numOfVertices; i++)
			adjacencyMatrix[i] = new double[numOfVertices];
		// Initialize the Adjacency Matrix, if i==j, set it to zero, other set it to Non-A-Number
		for(i=0; i<numOfVertices; i++)
		{
			for(j=0; j<numOfVertices; j++)
			{
				if(i==j)
					adjacencyMatrix[i][j] = 0;
				else
					adjacencyMatrix[i][j] = Double.NaN;
			}
		}
						
		j=0;
		while (scEdges.hasNextLine())
		{
			line = scEdges.nextLine();
			
			// <V-ID> <Adj-V-ID> <Weight?> <Adj-V-ID> <Weight?> .... 
			strVertices = line.split("\\s+");
			
			srcID = inputVertices.get(j).ID;
			for(i=1; i<strVertices.length; i=i+2)
			{
				// Vertex ID - Note May need to use name to find ID!
				destID = Integer.parseInt(strVertices[i]);
				
				// Edge Weight
				double weight = Double.parseDouble(strVertices[i+1]);
				
				adjacencyMatrix[srcID][destID] = weight;
			}
			
			j++;
		}
		scEdges.close();
		myGraph = new Graph(inputVertices, adjacencyMatrix);
		//------------------------------ Import Graph Info From Text File (End)--------------------------------------//
		
		//------------------------------ Print Adjacency Matrix (Begin)--------------------------------------//
		myGraph.printAdjacencyMatrix();
		//------------------------------ Print Adjacency Matrix (End)--------------------------------------//
		
		//------------------------------ Print Single Source Shortest Path (Begin)--------------------------------------//
		srcID = 4;	
		ArrayList<Vertex> resultShortPath = (ArrayList<Vertex>) myGraph.Dijkstra(myGraph.getVertex(srcID));
		
		System.out.println("From Source ID: " + srcID);
		for(i=0; i<resultShortPath.size(); i++)
		{
			if(resultShortPath.get(i).parent != null)
				System.out.println(resultShortPath.get(i).ID + " " + resultShortPath.get(i).key + " " + resultShortPath.get(i).parent.ID);
			else
				System.out.println(resultShortPath.get(i).ID + " " + resultShortPath.get(i).key);
		}
		//------------------------------ Print Single Source Shortest Path (End)--------------------------------------//
		
		//------------------------------ Print All-Pairs Shortest Paths (Begin)--------------------------------------//
		double APSP[][] = myGraph.slowAPSP();
		for(i=0; i<numOfVertices; i++)
		{
			for(j=0; j<numOfVertices; j++)
				System.out.print(APSP[i][j] + " ");
			System.out.print("\n");
		}
		//------------------------------ Print All-Pairs Shortest Paths (End)--------------------------------------//
		
		//------------------------------ Print Single Source Destination Longest Path (Begin)--------------------------------------//
//		myGraph.Longest_Path_Main(myGraph.getVertex(1), myGraph.getVertex(6));
//		myGraph.printLongest_Path(myGraph.getVertex(1), myGraph.getVertex(6));
		//------------------------------ Print Single Source Destination Longest Path (End)--------------------------------------//
		
		//------------------------------ Print Single Source Destination Longest Path (Begin)--------------------------------------//
		ArrayList<Vertex> sortedList = (ArrayList<Vertex>) myGraph.Topological_Sort_DAT();
		for(Vertex v: sortedList)
			System.out.println(v.ID + " " + v.key + " " + v.finishTime);
		System.out.print("\n");
		//------------------------------ Print Single Source Destination Longest Path (Begin)--------------------------------------//
		
		out.close();
	}
}
