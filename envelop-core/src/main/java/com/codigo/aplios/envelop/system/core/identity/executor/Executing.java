package com.codigo.aplios.envelop.system.core.identity.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.codigo.aplios.envelop.system.core.identity.executor.Sums.Sum;



public class Executing {
	public static void main(String[] args) throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(8);

		List<Future<Long>> results =
				executor.invokeAll(Arrays.asList(new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787),
						new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000),
						new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787),
						new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000),
						new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787),
						new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000),
						new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787),
						new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000),
						new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787),
						new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000),
						new Sum(10000, 100000023), new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023),
						new Sum(0, 10787), new Sum(100, 10000000), new Sum(10000, 100000023), new Sum(0, 10787),
						new Sum(100, 10000000), new Sum(10000, 100000023)));
		executor.shutdown();

		System.out.println("Pracuje");

		// for (Future<Long> result : results) {
		// System.out.println(result.get());
		// }
	}
}
