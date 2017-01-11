package com.codigo.aplios.envelop.system.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

//http://www.algorytm.edu.pl/algorytmy-maturalne/quick-sort.html

/**
 * Klasa realizuje mechanizm sortowania sposobem BubbleSort.
 * 
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
final class QuickSort<T> extends AbstractSort<T> {

	/**
	 * @param comparator
	 */
	public QuickSort(Comparator<T> comparator) {
		super(comparator);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	@SuppressWarnings("unchecked")
	public void sort(Collection<T> data) {
		T[] items = (T[]) data.toArray();

		quickSort(items, 0, items.length - DEC1);

		data.clear();
		data.addAll(Arrays.asList(items));
	}

	private final void quickSort(T[] array, int lewy, int prawy) {
		int i = lewy - DEC1, j = prawy + INC1;
		T pivot = (T) array[(lewy + prawy) / 2];

		while (true) {
			// szukam elementu wiekszego lub rownego piwot stojacego
			// po prawej stronie wartosci pivot

			while (this.comparer.compare(pivot, (T) array[++i]) > 0)
				;

			// szukam elementu mniejszego lub rownego pivot stojacego
			// po lewej stronie wartosci pivot
			while (this.comparer.compare(pivot, (T) array[--j]) < 0)
				;

			if (i <= j) {
				final T swap = array[i];
				array[i] = array[j];
				array[j] = swap;
			}
			else
				break;

		}

		if (j > lewy)
			quickSort(array, lewy, j);

		if (i < prawy)
			quickSort(array, i, prawy);
	}

	// -----------------------------------------------------------------------------------------------------------------

	private static final int DEC1 = 1, INC1 = 1;
}
