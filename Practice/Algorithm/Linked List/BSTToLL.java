package data_structure2;

class Node {
	int data;
	Node leftChild;
	Node rightChild;
	
	public Node(int x) {
		data = x;
	}
}

class LLNode {
	int data;
	Node headNode;
	LLNode tailNode;
	
	public LLNode(int x) {
		data = x;
	}
}

class ChangeToLL {
	Node root;
	LLNode lastNode;
	
	public ChangeToLL(Node root) {
		if (root == null) {
			System.out.println("null check");
		}
		this.root = root;
//		printNode(lastNode);
		
	}
	
	public void preorderTraversal(Node currentRoot) {
		if (currentRoot != null) {
			preorderTraversal(currentRoot.leftChild);
			changeToLL(currentRoot);
			preorderTraversal(currentRoot.rightChild);
			
		}
	}
	
	public void printNode(LLNode node) {
		if (node == null) {
			System.out.println("Node is null");
			
		} else {
			System.out.println("NOT NULL");
			
		}
		
	}
	
	public void changeToLL(Node node) {
		LLNode llNode = new LLNode(node.data);
		
		if (lastNode != null) {
			lastNode.tailNode = llNode;
		}
		lastNode = llNode;
	}
	
}

public class BSTToLL {

	public static void main(String[] args) {
		Node node = null;
		new ChangeToLL(node);
	}
}

