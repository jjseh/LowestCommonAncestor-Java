
import java.util.ArrayList;
import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class DAGLcaTest {


 // Tests constructor 
	@Test
    public void testConstructor() 
    {
	 new DAGLcaTest();
    
    }
 	 // Tests graph correctly constructed as binary tree

	 //tests to check if returns correct number of vertices in the DAG
	 @Test
	 public void testNumberVertex()
	 {
		 	DAGLca dag = new DAGLca(2);
			assertEquals("Test failed for testNumberVertex() ", 2, dag.numberOfVertx());
	 }
	 
	 //tests to check if returns correct vertices pointing from v
	 @Test
	 public void testAdjVertex()
	 {
		 	DAGLca DAG = new DAGLca(6);
			assertEquals("Test failed for testNumberVertex() ", 6, DAG.numberOfVertx());
			ArrayList<Integer> answer = new ArrayList<Integer>();

			// Test empty DAG
			// Check array list sizes are same
			assertTrue(DAG.adjVertex(0).size() == answer.size()); 		
			assertEquals("Empty DAG test ", true, DAG.adjVertex(0).isEmpty());
			
			// add edges to dag
			DAG.addEdge(0,1);  
			DAG.addEdge(1,2);
			// test one AdjVertex()
			answer.add(2);
			assertEquals("Test Adjecent node ", answer, DAG.adjVertex(1));
			
			// Test Multiple Adjacent Nodes 
			DAG.addEdge(1,3);
			DAG.addEdge(1,4);
			DAG.addEdge(1,5);
			answer.add(3);
			answer.add(4);
			answer.add(5);
			// Check if array list have same size
			assertTrue(DAG.adjVertex(1).size() == answer.size());  							
			assertEquals("Test Multiple Adjecent node", answer, DAG.adjVertex(1));
	 }
	 
	 	@Test
		public void testDirectedAcyclicGraphLCA(){
			DAGLca DAG = new DAGLca(7);
			DAG.addEdge(0, 1);
			DAG.addEdge(0, 2);
			DAG.addEdge(2, 3);
			DAG.addEdge(3, 4);
			DAG.addEdge(4, 5);
			DAG.addEdge(5, 6);
			ArrayList<Integer> answer = new ArrayList<Integer>();
			answer.add(0);
			assertTrue("LCA size test", DAG.LCA(6,1).size() == answer.size());
			for(int i : answer) {
				assertTrue("Testing single lca return", DAG.LCA(6,1).contains(i));
			}
					
	 	}	
	 	
	 	@Test
		public void testMultipleDAG(){
	 		// 
			DAGLca DAG = new DAGLca(7);
			ArrayList<Integer> answer = new ArrayList<Integer>();

			DAG.addEdge(0, 4);			
			DAG.addEdge(2, 6);
			DAG.addEdge(2, 4);
			DAG.addEdge(2, 5);
			DAG.addEdge(1, 3);
			DAG.addEdge(3, 4);
			DAG.addEdge(3, 6);
			DAG.addEdge(2, 3);

			answer.add(2);
			answer.add(3);
								
			assertTrue("lcas size different from expected results size", DAG.LCA(4,6).size() == answer.size());
			for(int i : answer) {
				assertTrue("Testing mutliple lca return", DAG.LCA(4,6).contains(i));
			}
	 	}
	 	
	 	@Test
		public void testDAGEdgeCases(){
			DAGLca DAG = new DAGLca(7);
			ArrayList<Integer> answer = new ArrayList<Integer>();

			DAG.addEdge(0, 4);			
			DAG.addEdge(2, 6);
			DAG.addEdge(2, 4);
			DAG.addEdge(2, 5);
			DAG.addEdge(1, 3);
			DAG.addEdge(3, 4);
			DAG.addEdge(3, 6);
			DAG.addEdge(2, 3);
			answer.add(2); 
			answer.add(3);
			
			//Test vertices outside range of DAG
			assertTrue("Testing out of range inputs", DAG.LCA(400, 835).isEmpty());
			
			//Test negative vertices not present
			assertTrue("Testing negative inputs", DAG.LCA(-10, -4).isEmpty());	 	
	 		
	 	} 
	 
	 }