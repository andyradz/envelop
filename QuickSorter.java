package com.codigo.aplios.explorer.data.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Klasa realizuje mechanizm sortowania zgodny a algorytmem sortowania QuickSort.
 *
 * @author andrzej.radziszewski
 * @category data
 * @version 1.0.0.0
 * @since 2017
 *
 * @param <T>
 *        Generyczny typ danych
 */
public final class QuickSorter<T> extends AbstractSorter<T> {

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy konstruktor obiektu klasy
	 *
	 * @param comparator
	 *        Mechanizm porównymania obiektów
	 */
	public QuickSorter(@NonNull Comparator<T> comparator) {
		super(comparator);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy konstruktor obiektu klasy
	 *
	 * @param comparator
	 *        Mechanizm porównymania obiektów
	 * @param sortingMode
	 *        Kolejność sortowania elementów
	 */
	public QuickSorter(@NonNull Comparator<T> comparator, @NonNull SortingMode sortingMode) {
		super(comparator,
			sortingMode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.allmarks.system.sort.AbstractSort#sort(java.util.List)
	 */
	@Override
	public void sort(List<T> data) {
		// TODO: implementacj

	}

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.allmarks.system.sort.ISortable#sort(java.util.stream.Stream)
	 */
	@Override
	public void sort(Stream<T> data) {

		this.sort(data.collect(Collectors.toList()));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.allmarks.system.sort.ISortable#sort(java.lang.Object[])
	 */
	@Override
	public void sort(T[] data) {

		if (data.length < 2) return;

		this.sort(data, 0, data.length - 1);
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void sort(T[] data, int lbound, int ubound) {

		int i = (lbound + ubound) / 2, j = lbound;

		final T pivot = data[i];
		data[i] = data[ubound];

		for (i = lbound; i < ubound; i++)
			if (!sortMode.apply(data[i], pivot)) {
				swap(data, j, i);
				j++;
			}

		data[ubound] = data[j];
		data[j] = pivot;

		if (lbound < j - 1) this.sort(data, lbound, j - 1);

		if (j + 1 < ubound) this.sort(data, j + 1, ubound);
	}

	// -----------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
		final Comparator<String> cmp = (o1, o2) -> {
			return Integer.valueOf(o1)
				.compareTo(Integer.valueOf(o2));
		};

		final String[] strArray
				= { "7", "2", "4", "7", "3", "1", "4", "6", "5", "8", "3", "9", "2", "6", "7", "6", "3", "0","22" };
		new QuickSorter<>(cmp,
			SortingMode.Ascending).sort(strArray);
		Arrays.stream(strArray)
			.forEach(System.out::print);

		System.out.println();

		final Integer[] numArray = { 7, 2, 4, 7, 3, 1, 4, 6, 5, 8, 3, 9, 2, 6, 7, 6, 3, 0,22 };
		new QuickSorter<>(Integer::compare,
			SortingMode.Descending).sort(numArray);
		Arrays.stream(numArray)
			.forEach(System.out::print);

	}
}
