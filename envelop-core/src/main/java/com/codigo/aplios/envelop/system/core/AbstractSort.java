package com.codigo.aplios.envelop.system.core;

import java.util.Collection;
import java.util.Comparator;

/**
 * Klasa bazowa dla klas realizujących specyficzne mechanizmy sortujące kolekcje
 * danych.
 * 
 * @author andrzej.radziszewski
 *
 * @param <T>
 * Typ danych dla klasy generycznej.
 */
abstract class AbstractSort<T> implements ISortingStrategy<T> {

	// -----------------------------------------------------------------------------------------------------------------

	public AbstractSort(Comparator<T> comparer) {
		this.comparer = comparer;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	abstract public void sort(Collection<T> data);

	// -----------------------------------------------------------------------------------------------------------------

	protected Comparator<T> comparer;

	// -----------------------------------------------------------------------------------------------------------------
}