
import java.util.*;

// Directed Acyclic Graph implementation of Lowest Common Ancestor

public class DAGLca {
	

	private final int vertex;
	private final ArrayList<Integer>[] adjVertex;
	private final ArrayList<Integer>[] reverseAdjVertex;			//Need for finding the LCA

	//DAG constructor
	public DAGLca(int vertex) {
		this.vertex = vertex;
		adjVertex = (ArrayList<Integer>[]) new ArrayList[vertex];
		reverseAdjVertex = (ArrayList<Integer>[]) new ArrayList[vertex];
		
		//Create an array list for each of the vertex
		for (int v = 0; v < vertex; v++) {
			adjVertex[v] = new ArrayList<Integer>();	
			//For LCA
			reverseAdjVertex[v] = new ArrayList<Integer>();
		}
	}

	// On directed graph, create depth first search
	private class DFSDirectedGraph {
		private boolean[] visited;
		private boolean[] revVisited;
		
		public DFSDirectedGraph(DAGLca G, int s)
		{
			visited = new boolean[G.numberOfVertx()];
			revVisited = new boolean[G.numberOfVertx()];
			depthFirstSearch(G, s);
		}
		
		// Depth first search on graph
		private void depthFirstSearch(DAGLca G, int v)
		{
			visited[v] = true;
			for (int w : G.adjVertex(v)) {
				if (!visited[w]) {
					depthFirstSearch(G, w);
				}
			}
		}
		
		// Reversed depth first search to find all parent
		private void reverseDfs(DAGLca Graph, int vertex) {
			revVisited[vertex] = true; 
			for (int w : Graph.reverseAdj(vertex)) {
				if (!revVisited[w]) {
					reverseDfs(Graph, w);
				}
			}
		}			 
		public boolean marked(int v) { 
			return visited[v]; 
		}
		
		public boolean revMarked(int v) { 
			return revVisited[v];
		}
	}

	// Edge added from vertex u to vertex v if conditions match
	// Return true if edge is added
	// acyclic - doesn't create a cycle by adding edges
	public boolean addEdge(int u, int v) {

		// Check vertices are not less than 0 or greater than the number of vertices in DAG
		if(u >= this.vertex || v >= this.vertex || u < 0 || v < 0) {
			return false;
		}
		
		// Check no self loops - keep vertices different
		// Check no paths exist between v and u
		// Check u does not have already an edge pointing to v.
		if(u != v && !containsNodePath(v, u) && !adjVertex[u].contains(v)) {
			adjVertex[u].add(v);
			reverseAdjVertex[v].add(u);
			return true;
		}	
		else {
			return false;
		}
	}

	//Return number of Vertices
	public int numberOfVertx() {
		return vertex;
	}

	// Return vertices pointing away from vertex v
	public ArrayList<Integer> adjVertex(int v) { 
		return adjVertex[v];
	}

	// Return reversed vertices pointing away from vertex v
	public ArrayList<Integer> reverseAdj(int v) {
		return reverseAdjVertex[v];
	}

	// Check path exists between two nodes
	public boolean containsNodePath(int node1, int node2) {
		DFSDirectedGraph dfs = new DFSDirectedGraph(this, node1);
		return dfs.marked(node2);
	}

	// Lowest Common Ancestor 
	public ArrayList<Integer> LCA(int node1, int node2) {
		ArrayList<Integer> lowestCommonAncestor = new ArrayList<Integer>();
		int maxDistance = Integer.MAX_VALUE;
		
		//return empty if invalid input
		if(node1 == node2 || node1 >= this.vertex || node2 >= this.vertex || node1 < 0 || node2 < 0) {
			return lowestCommonAncestor; 
		} 
		
		DFSDirectedGraph dfs = new DFSDirectedGraph(this, node1);
		dfs.reverseDfs(this, node1); 
		int node1Dist, node2Dist;
		
		for(int v = 0; v < this.vertex; v++) {
			if(dfs.revMarked(v) && containsNodePath(v, node2)) {
				node1Dist = getDistance(v, node1);
				node2Dist = getDistance(v, node2);
				
				if(Integer.max(node1Dist, node2Dist) < maxDistance) {		
					lowestCommonAncestor = new ArrayList<Integer>();
					lowestCommonAncestor.add(v);
					maxDistance = Integer.max(node1Dist, node2Dist);
				}
				else if(Integer.max(node1Dist, node2Dist) == maxDistance) {
					lowestCommonAncestor.add(v);
					maxDistance = Integer.max(node1Dist, node2Dist);
				}
			}
		}
		return lowestCommonAncestor;
	}

	private int getDistance(int node, int searchedNode) {
			if( node == searchedNode) { 
				return 0;
			}
			else {
		        Queue<Integer> queue = new LinkedList<Integer>();
		        int[] distance = new int[this.vertex];
		        boolean[] visited = new boolean[this.vertex];
		        
		        for (int i = 0; i < this.numberOfVertx(); i++) {
		        	distance[i] = Integer.MAX_VALUE;
		        }
		        distance[node] = 0;
		        visited[node] = true;
		        queue.add(node);
		
		        while (!queue.isEmpty()) {
		            int u = queue.remove();
		            for (int v : this.adjVertex(u)) {
		                if (!visited[v]) {
		                	distance[v] = distance[u] + 1;
		                	visited[v] = true;
		                    queue.add(v);
		                }
		            }
		        }
		        return distance[searchedNode];
			}
	}

	
}