package com.codigo.aplios.envelop.system.core;

public interface ITreeVisitor<T> {
	void visit(ITreeNode<T> tree);
}

final class PostOrderTraverseTreeVisitor<T> implements ITreeVisitor<T> {

	@Override
	public void visit(ITreeNode<T> tree) {

		ITreeNode<T> temp = tree.getLeftMostChild();
		while (temp != null) {
			visit(temp);
			temp = temp.getRightSibling();
		}
		System.out.print(tree + " ");
	}
}

final class PreOrderTraverseTreeVisitor<T> implements ITreeVisitor<T> {

	@Override
	public void visit(ITreeNode<T> tree) {
		
		System.out.print(tree + " ");
		ITreeNode<T> temp = tree.getLeftMostChild();
		while (temp != null) {
			visit(temp);
			temp = temp.getRightSibling();
		}
	}
}