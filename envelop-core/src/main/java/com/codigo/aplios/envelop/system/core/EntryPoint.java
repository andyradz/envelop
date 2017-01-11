package com.codigo.aplios.envelop.system.core;

import java.util.concurrent.CountDownLatch;

//TODO
/*
 * cheklista
 * sprawdzić klasę cycleBarierr
 */

/**
 * Przykładowa klasa prezentująca użycie i możliwości mechanizmu
 * zaimplementowanego w klasie CountDownLatch.
 * 
 * @author andrzej.radziszewski
 * @version 1.0.0.1
 * @category standalone
 */
public class EntryPoint {

	/**
	 * Metoda realizuje operację uruchomienia programu standalone. Główna metoda
	 * programu.
	 * 
	 * @param args
	 * Lista parametrów wejściowych programu przekazywanych z lini poleceń.
	 */
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(3);
		Thread cacheService = new Thread(new Service("CacheService", 600000, latch));
		Thread alertService = new Thread(new Service("alertService", 400000, latch));
		Thread vaidService = new Thread(new Service("validService", 100000, latch));

		cacheService.start();
		alertService.start();
		vaidService.start();

		try {
			latch.await();
			System.out.println("All services are up, Application is starting now!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------

	static final class Service implements Runnable {
		/**
		 * Podstawowy konstruktor obiektu.
		 * 
		 * @param name
		 * Nazwa dla tworzonej usługi.
		 * @param timeToStart
		 * Czas pauzy.
		 * @param latch
		 * Zatrzask do synchronizacji wątków.
		 */
		Service(String name, int timeToStart, CountDownLatch latch) {
			this.name = name;
			this.timeToStart = timeToStart;
			this.latch = latch;
		}

		// -------------------------------------------------------------------------------------------------------------

		@Override
		public void run() {
			try {

				Thread.sleep(timeToStart);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.name + " is up");
			latch.countDown();
		}

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Atrybut obiektu zawiera informacje o nazwie usługi. Wartość tylko do
		 * odczytu.
		 */
		private String name;

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Aktrybut obiektu zawiera informacje o czasie pauzy podczas
		 * przetwarzania zadania. Wartość tylko do odczytu.
		 */
		private int timeToStart;

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Atrybut obiektu przedstawia zatrzask na bazie którego następuje
		 * synchronizacja między wąkami. Wartość tylko do odczytu.
		 */
		private CountDownLatch latch;
	}
}
