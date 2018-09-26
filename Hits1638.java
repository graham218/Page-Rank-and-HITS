
import java.io.*;
import java.math.*;
public class Hits1638 
{
	public static void main(String args[]) throws IOException
	{
		int noofiterations,firstvalue,counter=1,v=0,e=0,a=0,b=0,i=0,j=0,m=0;
		double errorratedefault=0.0,value=0.0,hubaddition=0.0, authorityaddition=0.0;
		String s,p,samplefile,vertices=null,edges=null,first=null,second=null;
		String diffdigit[];
		String updatelinks[];
		int adjacencymatrix[][];
		double hubvalue[][];
		double authorityvalue[][];
		double authvalueaddition[];
		double hubvalueaddition[];
		int incominglinks[][];
		int outgoinglinks[];
		noofiterations = Integer.parseInt(args[0]);
		firstvalue = Integer.parseInt(args[1]);
		samplefile = args[2];
		
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
									
		hubvalue = new double[1000][v];	// it stores the hub values of the current iteration. 2D array as it is storing hub
										// values of that particular vertices for that particular iteration. 
										// eg:- hubvalue[2][1] indicates hub value of node 1 at Iteration 2
		authorityvalue = new double[1000][v];// it stores the hub values of the current iteration. 2D array as it is storing 
											// authority values of that particular vertices for that particular iteration. 
										// eg:- authorityvalue[2][1] indicates authority value of node 1 at Iteration 2
		
		incominglinks = new int[v][v];	// it stores the incoming edges/links from one node to another
										// eg:- incominglinks[2][1] indicates a link is present from node 1 to node 2 i.e 
										// node 2 has a incoming link from node 1
		
		outgoinglinks = new int[v];	// it stores the count of outgoing links
									// eg:- outgoinglinks[2] = 1 indicates their is 1 outgoing link from node 2
		
		authvalueaddition = new double[v];
		hubvalueaddition = new double[v];
		
		for(i=0;i<v;i++)
		{
			for(j=0;j<v;j++)
			{
				adjacencymatrix[i][j] = 0;
				authvalueaddition[i] = 0;
				hubvalueaddition[i] = 0;
			}
		}
		
		FileReader freader1 = new FileReader(samplefile);
		BufferedReader br1 = new BufferedReader(freader1);
		counter=1;
		//System.out.println("Present links");
		while((p = br1.readLine()) != null) 
		{
			if(counter>1)
			{
			//	System.out.println(p);
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
					hubvalue[0][i]=1;
					authorityvalue[0][i]=1;
				}
			}
			if (firstvalue == -1)
			{
				for(i=0;i<v;i++)
				{
					hubvalue[0][i]=(1.0/v);
					authorityvalue[0][i]=(1.0/v);
				}
			}
			if(firstvalue == -2)
			{
				for(i=0;i<v;i++)
				{
					hubvalue[0][i]=(1.0/Math.sqrt(v));
					authorityvalue[0][i]=(1.0/Math.sqrt(v));
				}
			}
			if(firstvalue == 0)
			{
				for(i=0;i<v;i++)
				{
					hubvalue[0][i]=0;
					authorityvalue[0][i]=0;
				}
			}
		// initialization ends
			
		// counting outlinks for each vertices
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
						incominglinks[j][i]=1;
					}
				}
			}
		//storing ends
			
		
//----------------------------------------------------------------------------------------------------
		if(noofiterations>0)
		{
			if(v<10)   //// to check if no of vertices is less than 10. If yes, then only display Base Values
			{
				//displaying initial hub and authority values
					System.out.println();
					System.out.print("Base 0 : ");
					for(i=0;i<v;i++)
					{
						double hv = hubvalue[0][i];
						double av = authorityvalue[0][i];
						System.out.print("A/H["+i+"] = ");
						System.out.printf("%.8f", av);
						System.out.print("/");
						System.out.printf("%.8f", hv);
						System.out.print("     ");
					}
				System.out.println();
				System.out.println();
				// display ends	
			}
			
			m=1;
			do
			{
				authorityaddition = 0;
				hubaddition = 0;
				
				for(i=0;i<v;i++)
				{
					authvalueaddition[i]=0;
					hubvalueaddition[i]=0;
				}
				
				for(j=0;j<v;j++)
				{
					for(i=0;i<v;i++)
					{
						if(incominglinks[j][i]==1)
						{
							authvalueaddition[j] = authvalueaddition[j] + hubvalue[m-1][i];
						}
					}
					authorityaddition = authorityaddition + Math.pow(authvalueaddition[j], 2);
				}
				
				for(i=0;i<v;i++)
				{
					authorityvalue[m][i] = authvalueaddition[i]/Math.sqrt(authorityaddition);
				}
				
				for(i=0;i<v;i++)
				{
					for(j=0;j<v;j++)
					{
						if(incominglinks[i][j]==1)
						{
							hubvalueaddition[j] = hubvalueaddition[j] + authorityvalue[m][i];
						}
					}
				}
				
				for(i=0;i<v;i++)
				{
					hubaddition = hubaddition + Math.pow(hubvalueaddition[i], 2);
				}
				
				for(i=0;i<v;i++)
				{
					hubvalue[m][i] = hubvalueaddition[i]/Math.sqrt(hubaddition);
				}
				
				if(v>=10)	//// to check if no of vertices is less than 10. If no, then display only values of last iterations
				{
					if(m==noofiterations)	
					{
						System.out.print("Iteration "+m+" : ");
						System.out.println();
						for(i=0;i<v;i++)
						{
							double hv = hubvalue[m][i];
							double av = authorityvalue[m][i];
							System.out.print("A/H["+i+"] = ");
							System.out.printf("%.8f", av);
							System.out.print("/");
							System.out.printf("%.8f", hv);
							System.out.print("     ");
							System.out.println();
						}
					}	
				}
				else
				{
					System.out.print("Iter "+m+" : ");
					for(i=0;i<v;i++)
					{
						double hv = hubvalue[m][i];
						double av = authorityvalue[m][i];
						System.out.print("A/H["+i+"] = ");
						System.out.printf("%.8f", av);
						System.out.print("/");
						System.out.printf("%.8f", hv);
						System.out.print("     ");
					}
					System.out.println();
				}
				m++;
			}
			while(m<=noofiterations);
		}
		// no of iterations is greater than 0

//----------------------------------------------------------------------------------------------------

		// no of iterations is less than or equal to 0
		else
		{
			//displaying initial hub and authority values
			System.out.println();
			if(v<=10)
			{
				System.out.print("Base 0 : ");
				for(i=0;i<v;i++)
				{
					double hv = hubvalue[0][i];
					double av = authorityvalue[0][i];
					System.out.print("A/H["+i+"] = ");
					System.out.printf("%.8f", av);
					System.out.print("/");
					System.out.printf("%.8f", hv);
					System.out.print("     ");
				
				}
			}
		// display ends	
		
		System.out.println();
		m = 1;
		do
		{
			authorityaddition = 0;
			hubaddition = 0;
			
			for(i=0;i<v;i++)
			{
				authvalueaddition[i]=0;
				hubvalueaddition[i]=0;
			}
			
			for(j=0;j<v;j++)
			{
				for(i=0;i<v;i++)
				{
					if(incominglinks[j][i]==1)
					{
						authvalueaddition[j] = authvalueaddition[j] + hubvalue[m-1][i];
					}
				}
				authorityaddition = authorityaddition + Math.pow(authvalueaddition[j], 2);
			}
			
			for(i=0;i<v;i++)
			{
				authorityvalue[m][i] = authvalueaddition[i]/Math.sqrt(authorityaddition);
			}
			
			for(i=0;i<v;i++)
			{
				for(j=0;j<v;j++)
				{
					if(incominglinks[i][j]==1)
					{
						hubvalueaddition[j] = hubvalueaddition[j] + authorityvalue[m][i];
						//System.out.println("Outgoing link from "+j+" is to "+i);
					}
				}
			}
			
			for(i=0;i<v;i++)
			{
				hubaddition = hubaddition + Math.pow(hubvalueaddition[i], 2);
			}
			
			for(i=0;i<v;i++)
			{
				hubvalue[m][i] = hubvalueaddition[i]/Math.sqrt(hubaddition);
			}
			
			if(v<=10)
			{
				System.out.print("Iter "+m+" : ");
				for(i=0;i<v;i++)
				{
					double hv = hubvalue[m][i];
					double av = authorityvalue[m][i];
					System.out.print("A/H["+i+"] = ");
					System.out.printf("%.8f", av);
					System.out.print("/");
					System.out.printf("%.8f", hv);
					System.out.print("     ");
				}
				System.out.println();
			}
			m++;
		}
		while(convergence(m,v,hubvalue, authorityvalue,errorratedefault));
		
		if(v>10)
		{
			System.out.print("Iter "+(m-1)+" : ");
			System.out.println();
			for(i=0;i<v;i++)
			{
				double hv = hubvalue[m-1][i];
				double av = authorityvalue[m-1][i];
				System.out.print("A/H["+i+"] = ");
				System.out.printf("%.8f", av);
				System.out.print("/");
				System.out.printf("%.8f", hv);
				System.out.print("     ");
				System.out.println();
			}
			
		}
	}
		
		// no of iterations less than or equal to 0
//----------------------------------------------------------------------------------------------------

	}
	
	public static boolean convergence(int a, int vertices, double hubvalue[][], double authorityvalue[][], double errorrate)
	{
		int count=0;
		for(int j=0;j<vertices;j++)
		{
			double hubdifference = hubvalue[(a-2)][j]-hubvalue[(a-1)][j];
			double authoritydifference = authorityvalue[(a-2)][j]-authorityvalue[(a-1)][j];
			if((hubdifference<errorrate) && (authoritydifference<errorrate))
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