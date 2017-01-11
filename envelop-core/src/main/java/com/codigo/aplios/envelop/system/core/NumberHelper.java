package com.codigo.aplios.envelop.system.core;

import java.util.function.Function;

/**
 * Klasa realizuje operacje na liczbach rzeczywistych. File name :
 * NumberHelper.java Create date : 2016-12-06
 * 
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 */
public class NumberHelper {

	/**
	 * Znacznik wyraża precyzję części umałkowej wartości liczby rzeczywistej,
	 * która będzie brana pod uwagę podczas operacji arytmetycznych.
	 */
	public static enum DecimalPrecision
	{
		/**
		 * Brak precyzji.
		 */
		PRECTO0(0D),

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Precyzja jednej pozycji.
		 */
		PRECTO1(1D),

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Precyzja dwóch pozycji.
		 */
		PRECTO2(2D),

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Precyzja trzech pozycji.
		 */
		PRECTO3(3D),

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Precyzja czterech pozycji.
		 */
		PRECTO4(4D),

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Precyzja pięciu pozycji.
		 */
		PRECTO5(5D),

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Precyzja sześciu pozycji.
		 */
		PRECTO6(6D);

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Domyślny konstruktor obiektu klasy.
		 * 
		 * @param precision
		 * Wartość liczbowa wyrażona w postaci liczby rzeczywsitej. Określa
		 * ilość pozycji części ułamka wartości rzeczywistej, która będzie brana
		 * pod uwagę podczas obliczeń arytmetycznych.
		 */
		DecimalPrecision(double precision) {
			this.precision = precision;
		}

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Właściwość obiektu określa precyzję ilości miejsc po przecinku, do
		 * której będzie sprowadzało się zaokrąglenie wartości liczby
		 * rzeczywistej.
		 * 
		 * @return Wartość liczbowa liczby rzeczywistej.
		 */
		public double value() {
			return precision;
		}

		//TODO: dodać wyspecyfikowną funcję toString()
		
		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Operator definiuje za pomocą znaczników parametry funkcji zaokrągleń.
		 */
		public enum Round
		{
			EVALTO1(DecimalPrecision.PRECTO1),
			EVALTO2(DecimalPrecision.PRECTO2),
			EVALTO3(DecimalPrecision.PRECTO3),
			EVALTO4(DecimalPrecision.PRECTO4),
			EVALTO5(DecimalPrecision.PRECTO5),
			EVALTO6(DecimalPrecision.PRECTO6);

			// ---------------------------------------------------------------------------------------------------------

			/**
			 * Metoda funktora dokonuje zaaokrąglenia wartosci liczby
			 * rzeczywistej do wskazanej precyzji.
			 */
			private final Function<Double, Double> doRound = (number) -> {
				if (!Double.isFinite(number))
					throw new IllegalArgumentException("\nWartość liczby rzeczywistej jest nieprawidłowa!");
				final double range = Math.pow(1E1, this.precision.value());
				return Math.round(number * range) / range;
			};

			// ---------------------------------------------------------------------------------------------------------

			/**
			 * Domyślny konstruktor obiektu klasy.
			 * 
			 * @param precision
			 * Precyzja ułamka liczby rzeczywistej.
			 */
			Round(DecimalPrecision precision) {
				this.precision = precision;
			}

			private DecimalPrecision precision;

			// ---------------------------------------------------------------------------------------------------------

			/**
			 * Metoda dokonuje wyznaczenia ułamka liczby rzeczywistej z
			 * uwzględnieniem precyzji zaokrąglenia.
			 * 
			 * @param number
			 * Wartość liczby rzeczywistej.
			 * @return Wartość numeryczna wyrażona liczbą rzeczywistą.
			 */
			public double evalue(double number) {
				return this.doRound.apply(number);
			}
			
			//TODO: dodać wyspecyfikowną funcję toString()
		}

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Atrybut obiektu określa precyzję ułamka wartości liczby rzeczywistej.
		 */
		private double precision;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Funkcja zwraca wartość ułamka liczby rzeczywistej z możliwością
	 * zastosowania zaaokrąglenia tej wartość do wskazanej dokładności.
	 * 
	 * @param number
	 * Wartość liczby rzeczywistej.
	 * @param precision
	 * Precyzja obliczania ilości miejsc po przecinku.
	 * @return Wartość numeryczna wyrażona liczbą rzeczywistą.
	 */
	public static double fraction(double number, DecimalPrecision precision) {
		if (!Double.isFinite(number))
			throw new IllegalArgumentException("\nWartość liczby rzeczywistej jest nieprawidłowa!");

		final double range = Math.pow(1E1, precision.value());
		return Math.round((number % 1E0) * range) / range;
	}

	// -----------------------------------------------------------------------------------------------------------------
}
