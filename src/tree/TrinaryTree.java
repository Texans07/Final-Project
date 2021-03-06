package tree;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class TrinaryTree {
	
	public static Node root;
	
	static class Node{
		//Structure of Tree Node
		int data;
		Node left;		//left node has data < parent
		Node center;	//center node has data == parent
		Node right;		//right node has data > parent
		
		//parameterized constructor
		Node(int data){
			this.data = data;
			this.left = null;
			this.center = null;
			this.right = null;
		}
		
		public void setLeft(Node left){
            this.left = left;
        }
		 
		public void setCenter(Node center){
		    this.center = center;
		}
        
		public void setRight(Node right){
            this.right = right;
        }
		
        public Node getLeft(){
            return this.left;
        } 
        
        public Node getCenter(){
        	return this.center;
        }
        
        public Node getRight(){
            return this.right;
        }
        
        public int getData(){
            return this.data;
        }
        
        public void setData(int value){
        	this.data = value;
        }
	}

	//Constructor
	public TrinaryTree() { 
		root = null;
	}
	
	//Method to insert a value to Trinary Tree	
	public void insert(int value){
		root = insert(root, value);
	}
	
	//Method to create a new node with the given value and insert it to the Trinary Tree 
	private Node insert(Node root, int value){
		if(root == null){
			root = new Node(value);
			root.setLeft(null);
			root.setRight(null);
			root.setCenter(null);
		}
		else {
			if(value < root.getData()){
				root.setLeft(insert(root.getLeft(), value));
			}
			if(value == root.getData()){
				root.setCenter(insert(root.getCenter(), value));
			}
			if(value > root.getData()) {
				root.setRight(insert(root.getRight(), value));
			}
		}
		return root;
	}
	
	//Delete a value from the Trinary Tree
	public void delete(int value){
		System.out.println("Delete " + value + " from the Tree...");
		root = delete(root, value);
		
	}
	
	//Method to delete the node with a give value from the Trinary Tree
	private Node delete(Node root, int value){
		if(isPresent(root, value)){
			if(root == null){
				return root;
			}
			if(value < root.getData()){
				root.setLeft(delete(root.getLeft(), value));
			}
			else if(value > root.getData()){
				root.setRight(delete(root.getRight(), value));
			}
			else  { //found element to delete
				if(root.getLeft()==null && root.getCenter()==null && root.getRight()==null){
					root = null;
					return root;
				}
				if(root.center != null){
					root.setCenter(delete(root.getCenter(), value));
				}
				else {
					if(root.getLeft()!=null && root.getRight()!=null){
						//Find Largest element in left subtree
						//replace current Node's data with the Largest Left subtree data
						Node temp = findMax(root.getLeft());
						root.setData(temp.getData());
						root.setLeft(delete(root.getLeft(), temp.getData()));;
					}
					else if(root.getLeft()==null){
						root = root.getRight();
						return root;
					}
					else if(root.getRight() == null){
						root = root.getLeft();
						return root;
					}
					
				}
			}		
		}
		else {
			System.out.println(value + " not present in the Trinary tree");
		}

		if(isPresent(root, value)){
			root=delete(root, value);
		}
		return root;
	}
	
	//Find the maximum element of the subtree starting from the given "root" node
	//Maximum element is the Right child of the right-most subtree of the given tree node
	private Node findMax(Node root){
		if(root == null){
			return root;
		}
		else {
			while(root.getRight()!=null){
				root = root.getRight();
			}
		}
		//System.out.println("Max element = " + root.getData());
		return root;
	}
	
	//Method to check if node with the given value is present in the tree or not
	private boolean isPresent(Node root, int value){
		if(root == null){
			return false;			
		}
		else if(value == root.getData()){
			return true;
		}
				
		else if(value < root.getData()){
			return isPresent(root.getLeft(), value);
		}
		else if(value > root.getData()){
			return isPresent(root.getRight(), value);
		}
		return false;
	}
	
	//Inorder Tree Traversal starting from the Root of the Trinary Tree
	public void traverseTree(Node root){
		if(root != null){
			traverseTree(root.getLeft());
			traverseTree(root.getCenter());
			System.out.print(root.getData()+ " ");
			traverseTree(root.getRight());
		}
		
	}
	
	//Method to traverse the Trinary tree 
	public void traverseTree(){
		System.out.println("---------------------------------------------");
		System.out.println("Inorder tree traversal starting from root : ");
		if(this.root == null){
			System.out.println("Empty Tree");
		}
		else traverseTree(root);
		System.out.println();
		System.out.println("---------------------------------------------");
	}
	
	public static void main(String[] args) {
		TrinaryTree TSTree = new TrinaryTree();
		TSTree.traverseTree();
		TSTree.insert(5);		//insert element 5 to the Tree -> this is the root of the tree
		TSTree.insert(4);		//insert element 4 to the Tree
		TSTree.insert(9);		//insert element 9 to the Tree
		TSTree.insert(5);		//insert element 5 to the Tree
		TSTree.insert(7);		//insert element 7 to the Tree
		TSTree.insert(2);		//insert element 2 to the Tree	
		TSTree.insert(2);		//insert element 2 to the Tree
		
		TSTree.traverseTree();	//Traverse the Tree -> Inorder traversal
		TSTree.isPresent(root, 7);
		TSTree.delete(2);		//Delete element 2 from the tree
		TSTree.traverseTree();	//Traverse the Tree -> Inorder traversal

	}

}