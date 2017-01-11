package com.codigo.aplios.envelop.system.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Klasa realizuje mechanizm sortowania sposobem BubbleSort.
 * 
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
final class BubbleSort<T> extends AbstractSort<T> {

	/**
	 * @param comparator
	 */
	public BubbleSort(Comparator<T> comparator) {
		super(comparator);
	}
	
	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void sort(Collection<T> data) {

		T[] items = (T[])data.toArray();

		T swap = null;
		for (int idx = 0; idx < items.length - DEC1; idx++)
			for (int jdx = 1; jdx < items.length - idx; jdx++)
				if (0 < (comparer.compare(items[jdx - DEC1], items[jdx]))) {
					swap = items[jdx - DEC1];
					items[jdx - DEC1] = items[jdx];
					items[jdx] = swap;
				}
		data.clear();
		data.addAll(Arrays.asList(items));
	}

	// -----------------------------------------------------------------------------------------------------------------
	
	private static final int DEC1 = 1;
}