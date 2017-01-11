package com.codigo.aplios.envelop.system.core.identity.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;

class Sumowanie extends RecursiveTask<Long> {

	public static void main(String[] args) {
		Consumer<Long> pp = Sumowanie::printNames;

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		long suma = forkJoinPool.invoke(new Sumowanie(1, 1000000));
		suma = forkJoinPool.invoke(new Sumowanie(-1, 2000000));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(-1, -1000000));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(-1343434, -1000000));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(2, 2));
		pp.accept(suma);
		suma = forkJoinPool.invoke(new Sumowanie(2, 20000));
		pp.accept(suma);
	}

	public static void printNames(Long name) {
		System.out.println(name);
	}

	private final long start, koniec;

	Sumowanie(long start, long koniec) {
		this.start = start;
		this.koniec = koniec;
	}

	@Override
	public Long compute() {
		long suma = 0;
		final long Porcja = 1024;
		if (koniec
				- start > Porcja) {
			long polowa = (koniec
					- start)
					/ 2;
			Sumowanie s1 = new Sumowanie(start, start
					+ polowa);
			s1.fork();
			Sumowanie s2 = new Sumowanie(start
					+ polowa
					+ 1, koniec);
			suma = s2.compute()
					+ s1.join();
		} else {
			suma = sumujBezposrednio();
		}
		return suma;
	}

	// Małe sumy obliczaj sekwencyjnie
	public long sumujBezposrednio() {
		long suma = 0;
		for (long i = start; i <= koniec; ++i) {
			suma += i;
		}
		return suma;
	}
}
