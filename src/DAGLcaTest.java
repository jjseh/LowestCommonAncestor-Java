
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
	 
}