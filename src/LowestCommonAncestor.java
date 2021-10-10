
import java.util.ArrayList;
import java.util.List;


// A Binary Tree node
class Nodes {
    int data;
    Nodes left, right;
 
    Nodes(int value) {
        data = value;
        left = right = null;
    }
}

public class LowestCommonAncestor {
	public static void main(String[] args) {
		
	}
	
	Nodes root;
    private List<Integer> node1Path = new ArrayList<>();
    private List<Integer> node2Path = new ArrayList<>();
 
    // Finds the path from root node to given root of the tree.
    int findLowestCommonAncestor(int node1, int node2) {
    	node1Path.clear();
    	node2Path.clear();
        return findLCA(root, node1, node2); 
    }
  
    public int findLCA (Nodes root, int node1, int node2) {
 
        if (!findNodePath(root, node1, node1Path) || !findNodePath(root, node2, node2Path)) {
        	
        	if(node1Path.size() > 0) {
        		System.out.println("node1 is in the tree");
        	}
        	else {
        		System.out.println("node1 not found");
        		//return -1;
        	}
        	if(node2Path.size() > 0) {
        		System.out.println("node2 is in the tree");
        	}
        	else {
        		System.out.println("node2 not found");
        		//return -2;
        	}
            return -1;
        }
        
        int i;
        for (i = 0; i < node1Path.size() && i < node2Path.size(); i++) {
            if (!node1Path.get(i).equals(node2Path.get(i))) {
                break;
            }
        }
        return node1Path.get(i-1);
    }

    private boolean findNodePath(Nodes root, int n, List<Integer> path) {
    	
        // base case
        if (root == null) {
            return false;
        }
        
        path.add(root.data);
 
        if (root.data == n) {
            return true;
        }
        if (root.left != null && findNodePath(root.left, n, path)) {
            return true;
        }
        if (root.right != null && findNodePath(root.right, n, path)) {
            return true;
        }

        // Remove root from path and return false
        path.remove(path.size()-1);
 
        return false;
    }
	
	
	
}

