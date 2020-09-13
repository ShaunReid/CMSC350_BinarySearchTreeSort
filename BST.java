/*
CMSC350 
 23 Feb 2020
 Shaun Reid
 
 The BST class is a Generic class used to build out the BST*/

public class BST<T extends Comparable<T>> {

	StringBuilder orderedStr = new StringBuilder();
	String returnedStr = new String();
	
	//Creates a new Node
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Node createNewNode(T obj) {
		Node newNode = new Node();
		newNode.data = obj;
		newNode.left = null;
		newNode.right = null;
		
		return newNode;
	}
	
	//Inserts new nodes into the BST
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Node insert(@SuppressWarnings("rawtypes") Node node, T val) {
		
		if(node == null) {
			return createNewNode(val);
		}
		
		if(val.compareTo((T) node.data) < 0) {
			node.left = insert(node.left, val);
			
		} else if (val.compareTo((T) node.data) > 0) {
			node.right = insert(node.right, val);
			
		} else if (val.compareTo((T) node.data) == 0) {
			node.left = insert(node.left, val);
		}
		
		return node;
		
	}
	
	//Executes an inorder walk through the BST to give the sorted order
	public void inorderWalk(@SuppressWarnings("rawtypes") Node nde) {
		
		if(nde == null) {
			return;
		}
		
		inorderWalk(nde.left);
		orderedStr.append(nde.data.toString() + " ");
		
		inorderWalk(nde.right);
		
		returnedStr = orderedStr.toString();
	}

}
