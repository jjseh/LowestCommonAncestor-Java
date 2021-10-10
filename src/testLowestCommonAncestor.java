import static org.junit.Assert.*;

import org.junit.Test;

public class testLowestCommonAncestor {
	LowestCommonAncestor tree;

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testNode() 
	{
		// Basic Node Class Test
		Nodes tree = new Nodes(1); 
		tree.left = new Nodes(2);
		tree.right = new Nodes(3);
		
		assertEquals(1,tree.data);
		assertEquals(2,tree.left.data);
		assertEquals(3,tree.right.data);	
	}
	
	@Test
	public void testEmptyTree()
	{
		LowestCommonAncestor tree = new LowestCommonAncestor();
		tree.root = null;
		assertEquals("null", -1, tree.findLowestCommonAncestor(3,2));
	}
	
	@Test
	public void testLCA(){
        Nodes tree = new Nodes(4);
        tree.left = new Nodes(10);
        tree.right = new Nodes(7);
        tree.left.left = new Nodes(2);
        tree.left.right = new Nodes(3);
        tree.right.left = new Nodes(5);
        tree.right.right = new Nodes(1);
        tree.left.left.left = new Nodes(8);
        tree.left.left.right = new Nodes(9);
        tree.left.right.left = new Nodes(6);
        tree.left.right.right = new Nodes(14);
        tree.right.left.left = new Nodes(12);	
        tree.right.left.right = new Nodes(15);
        tree.right.right.left = new Nodes(11);
        tree.right.right.right = new Nodes(16);
		LowestCommonAncestor LCA = new LowestCommonAncestor();
		LCA.root = tree;
		assertEquals(2,LCA.findLowestCommonAncestor(8,9));
		assertEquals(7,LCA.findLowestCommonAncestor(7, 5));
		assertEquals(1,LCA.findLowestCommonAncestor(11, 16));
		assertEquals(4,LCA.findLowestCommonAncestor(10, 7));
		assertEquals(5,LCA.findLowestCommonAncestor(12, 15));
		
	}
	
	
	
}
