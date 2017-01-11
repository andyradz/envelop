package com.codigo.aplios.envelop.system.core;

import java.util.LinkedList;
import java.util.UUID;

//TODO: dodać klasę abstrakcyjną dla węzła którra implmementuje ten interfejs

interface ITreeNode<T> {
	
	public UUID getUniqueId();
	public ITreeNode<T> getParent(); // zwraca referencje rodzica

	public void setParent(ITreeNode<T> parent); // ustawia rodzica dla węzła

	public T getData(); // zwraca przechowywane dane

	public void setData(T data); // ustawia dane w węźle

	public int getDegree(); // zwraca stopień węzła

	public ITreeNode<T> getChild(int i); // zwraca referencje do i-tego dziecka

	public boolean isLeaf(); // sprawdza czy węzeł jest liściem

	public ITreeNode<T> addChild(ITreeNode<T> child); // dodaje do węzła dziecko (inny
											// węzeł)

	public ITreeNode<T> addChild(T data); // tworzy i dodaje do węzła dziecko z
										// danymi

	public ITreeNode<T> removeChild(int i); // usuwa i-te dziecko węzła

	public void removeChildren(); // usuwa wszystkie dzieci węzła

	public ITreeNode<T> getLeftMostChild(); // zwraca pierwsze dziecko węzła (z
										// lewej)

	public LinkedList<ITreeNode<T>> getChildren(); // zwraca listę dzieci

	public ITreeNode<T> getRightSibling(); // zwraca kolejny element siostrzany węzła

	@Override
	public String toString(); // wyświetla węzeł (najczęściej dane)
}
