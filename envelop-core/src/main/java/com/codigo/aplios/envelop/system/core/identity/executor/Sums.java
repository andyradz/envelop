package com.codigo.aplios.envelop.system.core.identity.executor;

import java.util.concurrent.Callable;

public class Sums {
	static class Sum implements Callable<Long> {
		private final long from;
		private final long to;

		Sum(long from, long to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public Long call() {
			long acc = 0;
			for (long i = from; i <= to; i++) {
				acc = acc + i;
			}
			return acc;
		}
	}
}