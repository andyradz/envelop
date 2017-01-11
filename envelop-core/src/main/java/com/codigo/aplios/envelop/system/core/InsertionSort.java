package com.codigo.aplios.envelop.system.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Klasa realizuje mechanizm sortowania kolekcji danych korzystając z metody
 * sortowania przez wstawianie.
 * 
 * @author andrzej.radziszewski
 *
 * @param <T>
 * Typ danych dla klasy generycznej.
 */
final class InsertionSort<T> extends AbstractSort<T> {

	// -----------------------------------------------------------------------------------------------------------------

	public InsertionSort(Comparator<T> comparer) {
		super(comparer);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void sort(Collection<T> data) {
		T[] items = (T[]) data.toArray();

		for (int i = 1; i < items.length; i++) {
			// zapisujemy element w zmiennej
			T element = items[i];
			// oraz jego indeks w tablicy
			int j = i;
			// w pętli "robimy" dla niego miejsce w
			// posortowanej części tablicy
			while ((j > 0) && (0 < (comparer.compare(items[j - DEC1], element)))) {
				items[j] = items[j - DEC1];
				j--;
			}
			// wstawiamy go na odpowiednie miejsce
			items[j] = element;
		}
		data.clear();
		data.addAll(Arrays.asList(items));
	}

	// -----------------------------------------------------------------------------------------------------------------

	private static final int DEC1 = 1;

	// -----------------------------------------------------------------------------------------------------------------
}