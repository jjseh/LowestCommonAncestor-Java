import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class testLowestCommonAncestor {
//  Lowest Common Ancestor 
//
//
//	           Binary Tree 
//
//	                  4
//			  |
//	          +-------+-------+
//	          |	          |
//		 10               7
//	          |               |
//	      +---+---+       +---+---+
//	      |       |       |       |
//	      2       3       5       1
//          +-+-+   +-+-+   +-+-+   +-+-+
//          |   |   |   |   |   |   |   |
//          8   9   6   14  12  15  11  16
//       +--+
//       |
//     -17
//
//
	LowestCommonAncestor tree;

	
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
	@Test
	public void testNodes(){
		// If either first or second node is not on tree
		// Create Binary Tree - Diagram at the top of file
		Nodes tree = new Nodes(4);
        tree.left = new Nodes(10);
        tree.right = new Nodes(7);
        tree.left.left = new Nodes(2);
        tree.left.right = new Nodes(3);
        tree.right.left = new Nodes(5);
        tree.right.right = new Nodes(1);
        tree.left.left.left = new Nodes(8);
        //Negative Node
        tree.left.left.left.left = new Nodes(-17);
        tree.left.left.right = new Nodes(9);
        tree.left.right.left = new Nodes(6);
        tree.left.right.right = new Nodes(14);
        tree.right.left.left = new Nodes(12);
        tree.right.left.right = new Nodes(15);
        tree.right.right.left = new Nodes(11);
        tree.right.right.right = new Nodes(16);
        LowestCommonAncestor LCA = new LowestCommonAncestor();
		LCA.root = tree;
		assertEquals(-1,LCA.findLowestCommonAncestor(20, 15));
		assertEquals(-1,LCA.findLowestCommonAncestor(12, 20));
		assertEquals(-1,LCA.findLowestCommonAncestor(20, 1));
		assertEquals(4,LCA.findLowestCommonAncestor(14, 12));
		// Node 1 and node 2 not in tree
		assertEquals(-1,LCA.findLowestCommonAncestor(20, 20));
		//Same nodes
		assertEquals(7,LCA.findLowestCommonAncestor(7, 7));
		//Negative nodes
		assertEquals(-1,LCA.findLowestCommonAncestor(-1, 7));
		assertEquals(-1,LCA.findLowestCommonAncestor(7, -1));
		assertEquals(4,LCA.findLowestCommonAncestor(-17, 12));
	}
//	
//Binary Tree used for tesLCAContent underneath
//
//       	   0
//	           |
//         +-------+-------+
//         |	     	   |
//         1               2
//         |               |
//     +---+---+       +---+---+
//     |       |       |       |
//     3       4       5       6
//
//	
	
	@Test
	//Tests the function findLCA 
	public void testLCAContent(){
		Nodes tree = new Nodes(0);
		tree.left = new Nodes(1);
		tree.right = new Nodes(2);
		tree.left.left = new Nodes(3);
		tree.left.right = new Nodes(4);
		tree.right.left= new Nodes(5);
		tree.right.right = new Nodes(6);
		List<Integer> path1 = new ArrayList<>();
		List<Integer> path2 = new ArrayList<>();
		LowestCommonAncestor root = new LowestCommonAncestor();
		assertEquals("", -1,root.findLCA(tree, 10, 14));
		assertEquals(0, root.findLCA(tree, 1, 2));
		
	}

	
	
	
	
	
}
