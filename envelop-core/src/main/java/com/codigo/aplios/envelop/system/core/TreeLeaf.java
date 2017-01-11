package com.codigo.aplios.envelop.system.core;

import java.util.LinkedList;
import java.util.UUID;

public class TreeLeaf<T> implements ITreeNode<T> {

	@Override
	public ITreeNode<T> getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(ITreeNode<T> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public T getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ITreeNode<T> getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITreeNode<T> addChild(T child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeNode<T> removeChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChildren() {
		// TODO Auto-generated method stub

	}

	@Override
	public ITreeNode<T> getLeftMostChild() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<ITreeNode<T>> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeNode<T> getRightSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeNode<T> addChild(ITreeNode<T> child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}

}
