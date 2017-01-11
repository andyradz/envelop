package com.codigo.aplios.envelop.system.core;

class Tree<T> {
	private ITreeNode<T> root; // referencja do korzenia

	public Tree() { // konstruktor domyślny
		root = null;
	}

	public Tree(ITreeNode<T> root) { // konstruktor jednoparametrowy
		this.root = root;
	}

	public ITreeNode<T> getRoot() {

		return root;
	}

	public void preorderTraverseTree(ITreeNode<T> n) {

		System.out.print(n + " ");
		ITreeNode<T> temp = n.getLeftMostChild();
		while (temp != null) {
			preorderTraverseTree(temp);
			temp = temp.getRightSibling();
		}
	}

	public void inOrderTraverseTree(ITreeNode<T> n) {

		if (n.isLeaf())
			System.out.print(n + " ");
		else {
			ITreeNode<T> temp = n.getLeftMostChild();
			inOrderTraverseTree(temp);
			System.out.print(n + " ");
			temp = temp.getRightSibling();
			while (temp != null) {
				inOrderTraverseTree(temp);
				temp = temp.getRightSibling();
			}
		}
	}

	public void accept(ITreeVisitor<T> visitor)
	{
		visitor.visit(this.getRoot());
	}
	
	/*
	public Node findNode(int key) {

		// Start at the top of the tree

		Node focusNode = root;

		// While we haven't found the Node
		// keep looking

		while (focusNode.key != key) {

			// If we should search to the left

			if (key < focusNode.key) {

				// Shift the focus Node to the left child

				focusNode = focusNode.leftChild;

			} else {

				// Shift the focus Node to the right child

				focusNode = focusNode.rightChild;

			}

			// The node wasn't found

			if (focusNode == null)
				return null;

		}

		return focusNode;

	}*/

}
