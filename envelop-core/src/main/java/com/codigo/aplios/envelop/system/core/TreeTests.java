package com.codigo.aplios.envelop.system.core;

import org.junit.Test;

public class TreeTests {

	@Test
	public void test() {
		ITreeNode<String> korzen = new TreeNode<String>(null, "G");

		ITreeNode<String> n1 = korzen.addChild("E");
		ITreeNode<String> n2 = korzen.addChild("C");
		ITreeNode<String> n3 = korzen.addChild("V");
		n1.addChild("Z");
		ITreeNode<String> n4 = n1.addChild("M");
		n1.addChild("P");
		n4.addChild("J");
		ITreeNode<String> n5 = n2.addChild("X");
		n5.addChild("H");
		n5.addChild("O");
		n3.addChild("B");
		ITreeNode<String> n6 = n3.addChild("S");
		n6.addChild("F");

		Tree<String> drzewo = new Tree<String>(korzen);

		System.out.print("Pre Order: ");
		drzewo.accept(new PreOrderTraverseTreeVisitor<>());
		//drzewo.preorderTraverseTree(korzen);
		System.out.print("\nPost Order: ");
		//drzewo.postOrderTraverseTree(korzen);
		drzewo.accept(new PostOrderTraverseTreeVisitor<>());
		
		//visitor.visit(korzen);
		System.out.print("\nIn Order: ");
		drzewo.inOrderTraverseTree(korzen);
		System.out.println();
	}
}
