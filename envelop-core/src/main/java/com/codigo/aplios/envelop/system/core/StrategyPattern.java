/**
 * @author andrzej.radziszewski
 *
 */
package com.codigo.aplios.envelop.system.core;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StrategyPattern {

	public static void main(String[] args) {

		Collection<Person> data = new ArrayList<Person>() {
			{
				add(new Person("Andrzej", "Radziszewski", (byte) 44));
				add(new Person("izabela", "Radziszewska", (byte) 33));
				add(new Person("Aleksandra", "Radziszewska", (byte) 2));
				add(new Person("Małgorzata", "Sudoł", (byte) 45));
				add(new Person("Tomasz", "Jachimek", (byte) 21));
				add(new Person("Damian", "Gliwiński", (byte) 25));
				add(new Person("Arkadiusz", "Król", (byte) 30));
				add(new Person("Artur", "Kotkowski", (byte) 34));
				add(new Person("Waldemar", "Rolski", (byte) 70));
				add(new Person("Jan", "Machukski", (byte) 101));
				add(new Person("Radosław", "Pilski", (byte) 45));
				add(new Person("Kamil", "Baczyński", (byte) 23));
			}
		};

		((List<Person>) data).sort(new PersonByNameComparator());

		// dodać kontekst, któr będzie trzymał dane i comparator
		// dodać sortowanie descend i ascend
		SortingContext<Person> ctx = new SortingContext<>(new QuickSort(new PersonBySurnameComparator()));
		ctx.sort(data);

		data.stream().forEach(System.out::println);
		/*
		System.out.println("----------------------------------------------------------------------------------------");

		SortingContext<Person> ctx0 = new SortingContext<Person>(new InsertionSort<Person>(new PersonByAgeComparator()));
		ctx0.sort(data);

		data.stream().forEach(System.out::println);

		System.out.println("----------------------------------------------------------------------------------------");

		Collection<Long> data1 = new ArrayList<Long>();
		for (long idx = 500; idx > 0; idx--) {
			data1.add(idx);
		}
		
		SortingContext ctx1 = new SortingContext(new BubbleSort(Collections.reverseOrder()));
		ctx1.sort(data1);*/
		//data1.stream().forEach(System.out::println);
	}

	public enum ObjectToSort
	{
		NumerAlbumuStudenta,
		NumerKartyMiejskiej,
		NazwiskoMieszkanca
	}

}

class Person {
	private String name;
	private String surname;
	private byte age;

	// -----------------------------------------------------------------------------------------------------------------

	public Person(String name, String surname, byte age) {
		this.setName(name);
		this.setSurname(surname);
		this.setAge(age);
	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getSurname() {

		return this.surname;

	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getName() {

		return name;
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void setName(String name) {

		this.name = name;
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void setSurname(String surname) {

		this.surname = surname;
	}

	public byte getAge() {
		return age;
	}

	private void setAge(byte age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "person"
				+ "{"
				+ "name:"
				+ "\""
				+ this.getName()
				+ "\""
				+ ","
				+ "surname:"
				+ "\""
				+ this.getSurname()
				+ "\""
				+ ","
				+ "age:"
				+ "\""
				+ this.getAge()
				+ "\""
				+ "}";
	}

	// -----------------------------------------------------------------------------------------------------------------
}

final class SortingContext<T> {
	ISortingStrategy<T> sorter;

	// -----------------------------------------------------------------------------------------------------------------

	public SortingContext(ISortingStrategy<T> sort) {
		this.sorter = sort;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public void sort(Collection<T> data) {

		this.sorter.sort(data);
	}

	// -----------------------------------------------------------------------------------------------------------------
}

/**
 * Interfejs definiujący API do wykonywania sortowania
 * 
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
interface ISortingStrategy<T> {
	void sort(Collection<T> data);
}



// -----------------------------------------------------------------------------------------------------------------



// -----------------------------------------------------------------------------------------------------------------

final class MergeSort<T> extends AbstractSort<T> {
	public MergeSort(Comparator<T> comp) {
		super(comp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sort(Collection<T> data) {

		// TODO Auto-generated method stub
		// Zastosować metodę dziel i zwyciężaj
	}
}

// -----------------------------------------------------------------------------------------------------------------

/**
 * Klasa realizuje mechanizm sortowania sposobem HeapSort.
 * 
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
final class HeapSort<T> extends AbstractSort<T> {

	public HeapSort(Comparator<T> comp) {
		super(comp);
	}

	@Override
	public void sort(Collection<T> data) {

		Collections.sort((List<T>) data, this.comparer);
	}
}

// -----------------------------------------------------------------------------------------------------------------

/**
 * Klasa realizuje porównywanie dwóch obiektów typu Person. Obiekt jest
 * porównywany po własciwości Imię.
 * 
 * @author andrzej.radziszewski
 */
final class PersonByNameComparator implements Comparator<Person> {
	@Override
	public int compare(Person person1, Person person2) {

		return person1.getName().compareToIgnoreCase(person2.getName());
	}
}

// -----------------------------------------------------------------------------------------------------------------

/**
 * Klasa realizuje porównywanie dwóch obiektów typu Person. Obiekt jest
 * porównywany po własciwości Nazwisko.
 * 
 * @author andrzej.radziszewski
 */
final class PersonBySurnameComparator implements Comparator<Person> {
	
	@Override
	public int compare(Person person1, Person person2) {
		return person1.getSurname().compareToIgnoreCase(person2.getSurname());
	}
}

// -----------------------------------------------------------------------------------------------------------------

/**
 * Klasa realizuje porównywanie dwóch obiektów typu Person. Obiekt jest
 * porównywany po własciwości Wiek.
 * 
 * @author andrzej.radziszewski
 */
final class PersonByAgeComparator implements Comparator<Person> {
	
	@Override
	public int compare(Person person1, Person person2) {
		return Integer.compare(person1.getAge(), person2.getAge());
	}
}
