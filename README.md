Page-Rank and HITS
===

* Implemented Page Ranking algorithm to Rank websites in search Engines for the given Input.
* Also, Implemented Link Analysis Algorithm that rates Web Pages by calculating the Hubs and Authority values.

Page-Rank Algorithm
===

* Define no of Vertices and Edges and Store them in List.
* Define Initial Page Rank Values for Iteration 0.
* Calculate Incoming and Outgoing Links for Each Vertices and later on Calculate Page Rank for Each Iteration until the condition for convergence is met

CALCULATIONS
===

1. Formula :- 
pageRank(A) = ((1-dampingFactor)/(no of Vertices)) + (dampingFactor) * ((pageRank of incoming vertices to A)/(no of Outgoing links from A))

INPUT
===
```
0 2
0 3
1 0
2 1
```

OUTPUT
===
```
Existing edge from 0 to 2
Existing edge from 0 to 3
Existing edge from 1 to 0
Existing edge from 2 to 1
```

```
Vertices present = 0 2 3 1
```

```
For Vertex 0, their are 2 outgoing Links and 1 incoming Links
For Vertex 1, their are 1 outgoing Links and 1 incoming Links
For Vertex 2, their are 1 outgoing Links and 1 incoming Links
For Vertex 3, their are 0 outgoing Links and 1 incoming Links
```

```
Iteration 0 : PageRank[0] - 0.2500000     PageRank[1] - 0.2500000     PageRank[2] - 0.2500000     PageRank[3] - 0.2500000     
Iteration 1 : PageRank[0] - 0.2500000     PageRank[1] - 0.2500000     PageRank[2] - 0.1437500     PageRank[3] - 0.1437500     
Iteration 2 : PageRank[0] - 0.2500000     PageRank[1] - 0.1596875     PageRank[2] - 0.1437500     PageRank[3] - 0.1437500     
Iteration 3 : PageRank[0] - 0.1732344     PageRank[1] - 0.1596875     PageRank[2] - 0.1437500     PageRank[3] - 0.1437500     
Iteration 4 : PageRank[0] - 0.1732344     PageRank[1] - 0.1596875     PageRank[2] - 0.1111246     PageRank[3] - 0.1111246     
Iteration 5 : PageRank[0] - 0.1732344     PageRank[1] - 0.1319559     PageRank[2] - 0.1111246     PageRank[3] - 0.1111246     
Iteration 6 : PageRank[0] - 0.1496625     PageRank[1] - 0.1319559     PageRank[2] - 0.1111246     PageRank[3] - 0.1111246     
Iteration 7 : PageRank[0] - 0.1496625     PageRank[1] - 0.1319559     PageRank[2] - 0.1011066     PageRank[3] - 0.1011066     
Iteration 8 : PageRank[0] - 0.1496625     PageRank[1] - 0.1234406     PageRank[2] - 0.1011066     PageRank[3] - 0.1011066     
Iteration 9 : PageRank[0] - 0.1424245     PageRank[1] - 0.1234406     PageRank[2] - 0.1011066     PageRank[3] - 0.1011066     
Iteration 10 : PageRank[0] - 0.1424245     PageRank[1] - 0.1234406     PageRank[2] - 0.0980304     PageRank[3] - 0.0980304     
Iteration 11 : PageRank[0] - 0.1424245     PageRank[1] - 0.1208259     PageRank[2] - 0.0980304     PageRank[3] - 0.0980304     
Iteration 12 : PageRank[0] - 0.1402020     PageRank[1] - 0.1208259     PageRank[2] - 0.0980304     PageRank[3] - 0.0980304     
Iteration 13 : PageRank[0] - 0.1402020     PageRank[1] - 0.1208259     PageRank[2] - 0.0970858     PageRank[3] - 0.0970858     
Iteration 14 : PageRank[0] - 0.1402020     PageRank[1] - 0.1200230     PageRank[2] - 0.0970858     PageRank[3] - 0.0970858     
Iteration 15 : PageRank[0] - 0.1395195     PageRank[1] - 0.1200230     PageRank[2] - 0.0970858     PageRank[3] - 0.0970858     
Iteration 16 : PageRank[0] - 0.1395195     PageRank[1] - 0.1200230     PageRank[2] - 0.0967958     PageRank[3] - 0.0967958     
Iteration 17 : PageRank[0] - 0.1395195     PageRank[1] - 0.1197764     PageRank[2] - 0.0967958     PageRank[3] - 0.0967958     
Iteration 18 : PageRank[0] - 0.1393100     PageRank[1] - 0.1197764     PageRank[2] - 0.0967958     PageRank[3] - 0.0967958     
Iteration 19 : PageRank[0] - 0.1393100     PageRank[1] - 0.1197764     PageRank[2] - 0.0967067     PageRank[3] - 0.0967067     
Iteration 20 : PageRank[0] - 0.1393100     PageRank[1] - 0.1197007     PageRank[2] - 0.0967067     PageRank[3] - 0.0967067     
Iteration 21 : PageRank[0] - 0.1392456     PageRank[1] - 0.1197007     PageRank[2] - 0.0967067     PageRank[3] - 0.0967067     
Iteration 22 : PageRank[0] - 0.1392456     PageRank[1] - 0.1197007     PageRank[2] - 0.0966794     PageRank[3] - 0.0966794     
Iteration 23 : PageRank[0] - 0.1392456     PageRank[1] - 0.1196775     PageRank[2] - 0.0966794     PageRank[3] - 0.0966794     
Iteration 24 : PageRank[0] - 0.1392259     PageRank[1] - 0.1196775     PageRank[2] - 0.0966794     PageRank[3] - 0.0966794     
Iteration 25 : PageRank[0] - 0.1392259     PageRank[1] - 0.1196775     PageRank[2] - 0.0966710     PageRank[3] - 0.0966710     
```
