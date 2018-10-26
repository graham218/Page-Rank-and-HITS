import java.io.*;
import java.util.*;
import java.math.*;
public class PageRank 
{
	int outLinks=0,inLinks=0;
	HashMap<Integer,Integer> incomingLinks = new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> outgoingLinks = new HashMap<Integer,Integer>();
	HashMap<String,Double> pageRank = new HashMap<String,Double>();
	String newLine = System.getProperty("line.separator");
	
	public static void main(String args[]) throws IOException
	{
		int initialValue,counter=0;
		List<Integer> vertices = new ArrayList<Integer>();
		List<String> edges = new ArrayList<String>();
		
		initialValue = Integer.parseInt(args[0]);
		String sampleFile = args[1];
		
		String newLine = System.getProperty("line.separator");
		
		File file = new File(sampleFile);
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine())
		{
			String str = sc.nextLine();
			if(counter>0)
			{
				edges.add(str);
			}
			counter++;
		}
		
		for(int i=0;i<edges.size();i++)
		{
			String str = edges.get(i);
			String[] words = str.split(" ");
			int count = words.length;
			for(int j=0;j<count;j++)
			{
				if(i==0)
				{
					int w = Integer.parseInt(words[j]);
					vertices.add(w);
				}
				else
				{
					int w = Integer.parseInt(words[j]);
					if(!(vertices.contains(w)))
					{
						vertices.add(w);
					}
				}
			}
		}
		
		for(int i=0;i<edges.size();i++)
		{
			String str = edges.get(i);
			String[] words = str.split(" ");
			System.out.println("Existing edge from "+words[0]+" to "+words[1]);
		}
		
		System.out.print(newLine+"Vertices present =");
		for(int i : vertices)
		{
			System.out.print(" "+i);
		}
		
		PageRank pr = new PageRank();
		pr.initializeValues(initialValue,vertices);
		pr.calculateIncomingOutgoingLinks(vertices,edges);
		pr.calculatePageRank(vertices,edges);
	}
	
	public void initializeValues(int initialValue, List<Integer> vertices)
	{
		switch(initialValue)
		{
			case 1 : for(int i=0;i<vertices.size();i++)
					 {
							int k = vertices.get(i);
							String str="0->"+ k;
							pageRank.put(str, 1.0);
					 }
					 break;
					
			case -1 : for(int i=0;i<vertices.size();i++)
					 {
						int k = (int)vertices.get(i);
						String str="0->"+ k;
						pageRank.put(str, 1.0/vertices.size());
					 }
				 	 break;
			 
			case -2 : for(int i=0;i<vertices.size();i++)
					 {
						int k = (int)vertices.get(i);
						String str="0->"+ k;
						pageRank.put(str, 1.0/Math.sqrt(vertices.size()));
					 }
				 	 break;
				 	 
			case 0 : for(int i=0;i<vertices.size();i++)
					 {
						int k = (int)vertices.get(i);
						String str="0->"+ k;
						pageRank.put(str, 0.0);
					 }
				 	 break;
		}
	}
	
	public void calculateIncomingOutgoingLinks(List<Integer> vertices,List<String> edges)
	{
		System.out.println(newLine);
		for(int i=0;i<vertices.size();i++)
		{
			outLinks=0;inLinks=0;
			for(int j=0;j<edges.size();j++)
			{
				String str = edges.get(j);
				String[] words = str.split(" ");
				if(Integer.parseInt(words[0])==i)
				{
					outLinks++;
				}
				if(Integer.parseInt(words[1])==i)
				{
					inLinks++;
				}
			}
			incomingLinks.put(i, inLinks);
			outgoingLinks.put(i, outLinks);
			System.out.println("For Vertex "+i+", their are "+outLinks+ " outgoing Links and "+inLinks+" incoming Links");
		}
	}
	
	public void calculatePageRank(List<Integer> vertices,List<String> edges)
	{
		int k=1;
		double value=0.0,dampingFactor=0.85;
		
		System.out.print(newLine+"Iteration 0 : ");
		for(int i=0;i<vertices.size();i++)
		{
			String str = 0+"->"+i;
			double pg = pageRank.get(str);
			System.out.print("PageRank["+i+"] - ");
			System.out.printf("%.7f", pg);
			System.out.print("     ");
		}
		System.out.println();
		do
		{
			System.out.print("Iteration "+k+" : ");
			for(int i=0;i<vertices.size();i++)
			{
				value=0.0;
				for(int j=0;j<edges.size();j++)
				{
					String str = edges.get(j);
					String key="";									
					String[] words = str.split(" ");
					if(Integer.parseInt(words[1])==i)
					{	
						int previousIteration = (k-1);
						key = previousIteration+"->"+words[0];
						//System.out.println("Incoming Link to "+i+" is from "+words[0]+"--- "+key);
						double previousPageRank = pageRank.get(key);
						int out = outgoingLinks.get(Integer.parseInt(words[0]));
						//System.out.println(previousPageRank+ "  "+out);
						value = value + previousPageRank / (double)out;
					}
				}
				double currentPageRank = ((1 - dampingFactor)/(vertices.size())) + (dampingFactor)*value;
				String nk = k+"->"+i;
				pageRank.put(nk, currentPageRank);
				//System.out.print("PageRank["+i+"]="+currentPageRank+" ");
				System.out.print("PageRank["+i+"] - ");
				System.out.printf("%.7f", currentPageRank);
				System.out.print("     ");
			}
			System.out.println();
			k++;
		}
		while(convergence(k,vertices,0.00001,pageRank));
	}
	
	public static boolean convergence(int k,List<Integer> vertices,double errorRate,HashMap<String,Double> pageRank)
	{
		int count = 0;
		for(int i=0;i<vertices.size();i++)
		{
			String current = (k-1)+"->"+i;
			double pgrkCurrent = pageRank.get(current);
			String previous = (k-2)+"->"+i;
			double pgrkPrevious = pageRank.get(previous);
			double difference = pgrkPrevious - pgrkCurrent;
			if(difference<errorRate)
			{
				count++;
			}
		}
		if(count==vertices.size())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
}