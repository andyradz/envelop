package com.codigo.aplios.envelop.system.core;

//TODO Procent z liczby
//TODO Liczba z procentu
//DONE: Zamiana procentu na ułamek
//DONE: Zamiana ułamka na procent
//TODO dodać jedną wspólną funkcje zaaokrągajaca
//TODO: ddowanie mnożenie dzielenie odejmowanie procentów i porównywanie
//TODO: dodać metode equals
//TODO: ddac metodę hashCode
public final class Percent {

	public static void main(String[] args) {
		Percent per = Percent.fromNumber(10);
		double pe = per.percentValue();
		System.out.println(per);

		pe = Percent.Operator.F1.compute(per, 10);
		System.out.println(pe);

		pe = Percent.Operator.F2.compute(per, 10);
		System.out.println(pe);
	}
	
	// *----------------------------------------------------------------------------------------------------------------

	public static enum Operator
	{
		F1//liczy procent z liczby
			{// TODO: zmienić nazwę operacji
				public double compute(Percent percent, double number) {
					return StrictMath.nextUp(number * percent.toDecimal());
				}
			},
		F2 //liczy liczbe z procentu
			{// TODO: zmienić nazwę operacji
				public double compute(Percent percent, double number) {
					return StrictMath.nextUp(number * percent.toDecimal() * PERCENT_MULTIPLER);
				}
			};
		public abstract double compute(Percent percent, double number);
	}
	
	// *----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda wyznacza procent z przekazanej wartości ułamkowej.
	 * 
	 * @param number
	 * Wartość liczbowa odpowiadającą postaci ułamkowej procentu.
	 * @return
	 */
	public static Percent fromDecimal(double number) {
		final long val = StrictMath.round(number * PERCENT_MULTIPLER);
		return new Percent(val);
	}

	// *----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda wyznacza procent z przekazanej liczby całkowitej.
	 * 
	 * @param number
	 * Wartość liczbowa odpowiadającą postaci całkowitej procentu.
	 * @return
	 */
	public static Percent fromNumber(long number) {
		final long val = StrictMath.round(number * PERCENT_MULTIPLER);
		return new Percent(val);
	}

	// *----------------------------------------------------------------------------------------------------------------

	/**
	 * Właściwość podaje wartość procentu
	 * 
	 * @return Wartość numeryczna z przecinkiem.
	 */
	public double percentValue() {
		return this.percent;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Właściwość podaje wartość podstawy procentu
	 * 
	 * @return Wartość numeryczna z przecinkiem
	 */
	public double numberValue() {
		return this.number;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda przekształca obiekt do postaci łańcucha znaków. (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.percent + " " + Percent.SYMBOL;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda wykonuje zamianę wartości procentowej na ułamek.
	 * 
	 * @return Wartość liczbowa z przecinkiem.
	 */
	public double toDecimal() {
		return (this.percent / PERCENT_DIVIDER);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy konstruktor obiektu klasy. Konstruktor prywatny.
	 * 
	 * @param number
	 * Wartość numeryczna, która zamieniana jest na procent.
	 */
	private Percent(double number) {
		this.number = number;
		this.percent = this.evalute();
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda wykonuje przeliczenie wartości numerycznej na reprezentacje
	 * procentową.
	 * 
	 * @return Wartość numeryczna z przecinkiem.
	 */
	private double evalute() {
		return (this.number / PERCENT_DIVIDER);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Wartość tekstowa określająca symbol procentu.
	 */
	public static final String SYMBOL = "%";

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Wartość liczbowa określająca podzielnik procentowy
	 */
	private static final double PERCENT_DIVIDER = 1E2;

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Wartość liczbowa określająca mnożnik procentowy
	 */
	private static final double PERCENT_MULTIPLER = 1E2;

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Atrybut numeryczny reprezentująca wartość procentową.
	 */
	private double percent;

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Atrybut numeryczny podstawy procentu.
	 */
	private double number;

	// -----------------------------------------------------------------------------------------------------------------
}
