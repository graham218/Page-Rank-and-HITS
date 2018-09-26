
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
public class pgrk1638 
{
	public static void main(String args[]) throws IOException
	{
		int noofiterations,firstvalue,counter=1,i=0,j=0,v=0,a=0,b=0,e=0;
		String s,p,samplefile,vertices=null,first=null,second=null,edges=null;
		String diffdigit[];
		String updatelinks[];
		int adjacencymatrix[][];
		double pgrk[][];
		int incominglinks[][];
		int outgoinglinks[];
		double dampingfactor = 0.85, errorratedefault = 0.0, value=0.0;
		noofiterations = Integer.parseInt(args[0]);
		firstvalue = Integer.parseInt(args[1]);
		samplefile = args[2];
		
		//FileReader freader = new FileReader("/Users/Shreerang/Documents/File.txt");
		FileReader freader = new FileReader(samplefile);
		BufferedReader br = new BufferedReader(freader);
		while((s = br.readLine()) != null) 
		{
			//System.out.println(s);
			if(counter==1)
			{
				diffdigit = s.split(" ");
				vertices = diffdigit[0];
				edges = diffdigit[1];
				v = Integer.parseInt(vertices);
				e = Integer.parseInt(edges);
			}
			counter++;
		}
		
		freader.close();
		
		adjacencymatrix = new int[v][v];	// it stores the links present between nodes. 
											// if link exists between node 2 to node 1, then adjacencymatrix[2][1] = 1
		
			
		pgrk = new double[1000][v];		// it stores the page rank of the current iteration. 2D array as it is storing
														// page rank of that particular vertices for that particular iteration. 
														// eg:- pgrk[2][1] indicates page rank of node 1 at Iteration 2
		
		incominglinks = new int[v][v];	// it stores the incoming edges/links from one node to another
										// eg:- incominglinks[2][1] indicates a link is present from node 1 to node 2 i.e 
										// node 2 has a incoming link from node 1
		
		for(i=0;i<v;i++)
		{
			for(j=0;j<v;j++)
			{
				adjacencymatrix[i][j] = 0; 		// initializing the adjacency matrix to 0 
			}
		}
		
		FileReader freader1 = new FileReader(samplefile);
		BufferedReader br1 = new BufferedReader(freader1);
		counter=1;
		while((p = br1.readLine()) != null) 
		{
			if(counter>1)
			{
				updatelinks = p.split(" ");
				first = updatelinks[0];
				second = updatelinks[1];
				a = Integer.parseInt(first);
				b = Integer.parseInt(second);
				adjacencymatrix[a][b]=1;
			}
			counter++;
		}
		
		freader1.close();
		
		if(v>10)
		{
			noofiterations = 0;
			firstvalue = -1;
		}
		
		// to initialize page ranks of Iteration 0
		if(firstvalue == 1)
		{
			for(i=0;i<v;i++)
			{
				pgrk[0][i]=1;
			}
		}
		if (firstvalue == -1)
		{
			for(i=0;i<v;i++)
			{
				pgrk[0][i]=(1.0/v);
			}
		}
		if(firstvalue == -2)
		{
			for(i=0;i<v;i++)
			{
				pgrk[0][i]=(1.0/Math.sqrt(v));
			}
		}
		if(firstvalue == 0)
		{
			for(i=0;i<v;i++)
			{
				pgrk[0][i]=0;
			}
		}
		// initialization ends
		
		// counting outlinks for each vertices
			outgoinglinks = new int[v];
		
			for(i=0;i<v;i++)
			{
				int outlinkscounter=0;
				for(j=0;j<v;j++)
				{
					if(adjacencymatrix[i][j]==1)
					{
						outlinkscounter++;
					}
				}
				outgoinglinks[i]=outlinkscounter;
				outlinkscounter=0;
			}
		// counting ends
		
		//initializing errorrate
			if(noofiterations >= 0)
			{
				errorratedefault = 0.00001;
			}
			else
			{
				errorratedefault = Math.pow(10, noofiterations);
			}
		//initializing ends
		
		//storing incoming links of each vertices in an array
			for(j=0;j<v;j++)
			{
				for(i=0;i<v;i++)
				{
					if(adjacencymatrix[i][j]==1)
					{
						incominglinks[j][i]=1;	// it stores the incoming edges/links from one node to another
												// eg:- incominglinks[2][1] indicates a link is present from node 1 to node 2 i.e 
												// node 2 has a incoming link from node 1
					}
				}
			}
		//storing ends
		
		System.out.println();
		
		// no of iterations greater than 0
		if(noofiterations>0)
		{
			if(v<=10)	// if nodes less than or equal to 10 then display Base values
			{
				System.out.println();
				System.out.print("Base 0 : ");
				for(i=0;i<v;i++)
				{
					double pr = pgrk[0][i];
					System.out.print("PageRank["+i+"] - ");
					System.out.printf("%.8f", pr);
					System.out.print("     ");
				}
				System.out.println();
				System.out.println();
			}
			
			int k = 1; // iteration number
			do
			{
				if(v<=10)	// if nodes less than or equal to 10 then display values of each iteration
				{
					System.out.print("Iter " + k + " : ");
				}
				else
				{
					if(k==noofiterations)	// if nodes > 10, then display only values of last iteration.
					{
						System.out.println("Iter " + k + " : ");
					}
				}
				for(j=0;j<v;j++)
				{
					value=0.0;
					for(i=0;i<v;i++)
					{
						if(incominglinks[j][i]==1)
						{
							value = value + (pgrk[(k-1)][i]/outgoinglinks[i]);
							//System.out.println("Incoming Link of "+j+" is "+i);
						}
					}
					double pagerank = ((1 - dampingfactor)/v)+(dampingfactor)*(value);
					pgrk[k][j]=pagerank;
					double pr = pgrk[k][j];
					if(v<=10)
					{
						System.out.print("PageRank["+j+"] - ");
						System.out.printf("%.8f", pr);
						System.out.print("     ");
					}
					else
					{
						if(k==noofiterations)
						{
							System.out.print("PageRank["+j+"] - ");
							System.out.printf("%.8f", pr);
							System.out.println();
						}
					}
				}
				k++;
				System.out.println();
			}
			while(k<=noofiterations);
		}
		// no of iterations greater than 0
		
		// no of iterations less than or equal to 0
		else
		{
			if(v<=10)	// if nodes less than or equal to 10 then display Base values
			{
				System.out.println();
				System.out.print("Base 0 : ");
				for(i=0;i<v;i++)
				{
					double pr = pgrk[0][i];
					System.out.print("PageRank["+i+"] - ");
					System.out.printf("%.8f", pr);
					System.out.print("     ");
				}
				System.out.println();
				System.out.println();
			}
			
			int m = 1;  // iteration number
			do
			{
					if(v<=10)
					{
						System.out.print("Iter " + m + " : ");
					}
					for(j=0;j<v;j++)
					{
						value=0.0;
						for(i=0;i<v;i++)
						{
							if(incominglinks[j][i]==1)
							{
								value = value + (pgrk[(m-1)][i]/outgoinglinks[i]);
							}
						}
						double pagerank = ((1 - dampingfactor)/v)+(dampingfactor)*(value);
						pgrk[m][j]=pagerank;
						double pr = pgrk[m][j];
						if(v<=10)
						{
							System.out.print("PageRank["+j+"] - ");
							System.out.printf("%.8f", pr);
							System.out.print("     ");
						}
					}
					System.out.println();
				m++;
			}
			while(convergence(m,v,pgrk,errorratedefault));
			
			if(v>10)
			{
				System.out.print("Iteration " + (m-1) + " : ");
				System.out.println();
				for(j=0;j<v;j++)
				{
					double pr = pgrk[m-1][j];
					System.out.print("PageRank["+j+"] - ");
					System.out.printf("%.8f", pr);
					System.out.print("     ");
					System.out.println();
				}
				
			}
		}
		// no of iterations less than or equal to 0
	}
	
	public static boolean convergence(int a, int vertices, double pg[][], double errorrate)
	{
		int count=0;
		for(int j=0;j<vertices;j++)
		{
			double difference = pg[(a-2)][j]-pg[(a-1)][j];
			if(difference<errorrate)
			{
				count++;
			}
		}
		if(count==vertices)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}