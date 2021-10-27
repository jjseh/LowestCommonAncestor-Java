
import java.util.*;

// Directed Acyclic Graph implementation of Lowest Common Ancestor

public class DAGLca {
	

	private final int vertex;
	private final ArrayList<Integer>[] adjVertex;
	private final ArrayList<Integer>[] reverseAdjVertex;			

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

	
	private class DFSDirectedGraph {
		private boolean[] visited;
		private boolean[] revVisited;
		
		public DFSDirectedGraph(DAGLca G, int s)
		{
			visited = new boolean[G.numberOfVertx()];
			revVisited = new boolean[G.numberOfVertx()];
			depthFirstSearch(G, s);
		}

		private void depthFirstSearch(DAGLca G, int v)
		{
			visited[v] = true;
			for (int w : G.adjVertex(v)) {
				if (!visited[w]) {
					depthFirstSearch(G, w);
				}
			}
		}
		
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

	
	public boolean addEdge(int u, int v) {

		if(u >= this.vertex || v >= this.vertex || u < 0 || v < 0) {
			return false;
		}
		
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

	public ArrayList<Integer> adjVertex(int v) { 
		return adjVertex[v];
	}

	public ArrayList<Integer> reverseAdj(int v) {
		return reverseAdjVertex[v];
	}

	public boolean containsNodePath(int node1, int node2) {
		DFSDirectedGraph dfs = new DFSDirectedGraph(this, node1);
		return dfs.marked(node2);
	}

	public ArrayList<Integer> LCA(int node1, int node2) {
		ArrayList<Integer> lowestCommonAncestor = new ArrayList<Integer>();
		int maxDistance = Integer.MAX_VALUE;
		
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