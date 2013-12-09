package code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
		
		FileOutputStream fos = new FileOutputStream(args[1]);
		OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");		
		
		// Input Graph Info? Hard code or come from a file?
		
		
		
		out.close();	
	}
}
