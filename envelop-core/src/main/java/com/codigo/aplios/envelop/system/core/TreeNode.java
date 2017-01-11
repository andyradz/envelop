package com.codigo.aplios.envelop.system.core;

import java.util.LinkedList;
import java.util.UUID;

class TreeNode<T> implements ITreeNode<T> {
	private T data; // dane
	private ITreeNode<T> parent; // referencja do rodzica
	private LinkedList<ITreeNode<T>> children; // lista dzieci

	public TreeNode() { // konstruktor domyślny
		parent = null;
		children = new LinkedList<ITreeNode<T>>();
	}

	public TreeNode(ITreeNode<T> parent) { // konstruktor jednoparametrowy
		this();
		this.parent = parent;
	}

	public TreeNode(ITreeNode<T> parent, T data) { // konstruktor dwuparametrowy
		this(parent);
		this.data = data;
	}

	@Override
	public ITreeNode<T> getParent() {

		return parent;
	}

	@Override
	public void setParent(ITreeNode<T> parent) {

		this.parent = parent;
	}

	@Override
	public T getData() {

		return data;
	}

	@Override
	public void setData(T data) {

		this.data = data;
	}

	@Override
	public int getDegree() {

		return children.size();
	}

	@Override
	public ITreeNode<T> getChild(int i) {

		return children.get(i);
	}

	@Override
	public boolean isLeaf() {

		return children.isEmpty();
	}

	@Override
	public ITreeNode<T> addChild(ITreeNode<T> child) {

		child.setParent(this);
		children.add(child);
		return child;
	}

	@Override
	public ITreeNode<T> addChild(T data) {

		TreeNode<T> child = new TreeNode<T>(this, data);
		children.add(child);
		return child;
	}

	@Override
	public ITreeNode<T> removeChild(int i) {

		return children.remove(i);
	}

	@Override
	public void removeChildren() {

		children.clear();
	}

	@Override
	public ITreeNode<T> getLeftMostChild() {

		if (children.isEmpty())
			return null;
		return children.get(0);
	}

	@Override
	public LinkedList<ITreeNode<T>> getChildren() {

		if (children.isEmpty())
			return null;
		return children;
	}

	@Override
	public ITreeNode<T> getRightSibling() {

		if (parent != null) {
			LinkedList<ITreeNode<T>> childrenParent = parent.getChildren();
			int pozycja = childrenParent.indexOf(this);
			if (childrenParent.size() > pozycja + 1)
				return childrenParent.get(pozycja + 1);
		}
		return null;
	}

	@Override
	public String toString() {

		return data.toString();
	}

	@Override
	public UUID getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}
}
