/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.core;

/**
 * @author andrzej.radziszewski
 *
 */
final class WordsOfMoneyParts {

	private final int sector;

	private final int unitCount;

	private final int teenCount;

	private final int tenCount;

	private final int hundCount;

	public static WordsMoneyPartBuilder builder(int sector) {

		return new WordsMoneyPartBuilder(sector);
	}

	public WordsOfMoneyParts(WordsMoneyPartBuilder partBuilder) {
		this.sector = partBuilder.sector;
		this.unitCount = partBuilder.unitCount;
		this.tenCount = partBuilder.tenCount;
		this.teenCount = partBuilder.teenCount;
		this.hundCount = partBuilder.hundCount;
	}

	public int getSector() {

		return this.sector;
	}

	public int countOfUnit() {

		return this.unitCount;
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * <p style=
	 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
	 * solid;'>
	 * Właściwość określa ilość dziesiątek w danym segmencie liczby.
	 * </p>
	 *
	 * @return Wartość numeryczna całkowita.
	 */
	public int countOfTen() {

		return this.tenCount;
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * <p style=
	 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
	 * solid;'>
	 * Właściwość określa ilość nastek w danym segmencie liczby.
	 * </p>
	 *
	 * @return Wartość numeryczna całkowita.
	 */
	public int countOfTeen() {

		return this.teenCount;
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * <p style=
	 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
	 * solid;'>
	 * Właściwość określa ilość setek w danym segmencie liczby.
	 * </p>
	 *
	 * @return Wartość numeryczna całkowita.
	 */
	public int countOfHundred() {

		return this.hundCount;
	}

	@Override
	public String toString() {

		final StringBuilder myBuilder = new StringBuilder();
		myBuilder.append("{");
		myBuilder.append(String.format("sector:10x'%d'", this.getSector()));
		myBuilder.append(String.format(",units:'%d'", this.countOfUnit()));
		myBuilder.append(String.format(",teens:'%d'", this.countOfTeen()));
		myBuilder.append(String.format(",tens:'%d'", this.countOfTen()));
		myBuilder.append(String.format(",hundreds:'%d'", this.countOfHundred()));
		myBuilder.append("}");
		return myBuilder.toString();
	}

	/**
	 * <p style=
	 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
	 * solid;font-size:1.2em'>
	 * Klasa realizuje mechanizm przechowywania wartości ilości jednostek danego
	 * sektora liczby<br>
	 * Store file : Slownie.java</br>
	 * Create date: 24.01.2017
	 * </p>
	 *
	 * @author andrzej.radziszewski
	 * @version 1.0.0.0
	 */
	static class WordsMoneyPartBuilder {

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Numer sektora w liczbie.
		 * </p>
		 */
		private int sector;

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Ilość jednostek w sektorze liczby.
		 * </p>
		 */
		private int unitCount;

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Ilość nastek w sektorze liczby.
		 * </p>
		 */
		private int teenCount;

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Ilość dziesiątek w sektorze liczby.
		 * </p>
		 */
		private int tenCount;

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Ilość setek w sektorze liczby
		 * </p>
		 */
		private int hundCount;

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Metoda weryfikuje wartość ilości jednostek przypisywanych do danego
		 * sektora liczby.</br>
		 * Wykonywana jest walidacja czy przypisywana wartość jest z zakresu
		 * [0-9].
		 * </p>
		 *
		 * @param assignValue
		 * Wartość ilości przypisywanej do sektora liczby.
		 *
		 * @throws IllegalArgumentException
		 * <p style=
		 * 'color:red;background:rgba(255,230,230,0.9);padding:12px;border-radius:4px;'>
		 * Wyjątek powstaje w przypadku gdy wartości <code>assignValue</code>
		 * jest poza zakresem dozwolonych wartości [0-9]
		 * </p>
		 */
		private void checkAssignValue(int assignValue) {

			if ((assignValue < 0) || (assignValue > 9))
				throw new IllegalArgumentException(
						"\nBłędna wartość parametru {assignValue}."
								+ " \nDozwolone wartości to liczby z zakresu [0-9]");
		}

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Podstawowy konstruktor obiektu klasy.
		 * </p>
		 *
		 * @param sector
		 * Numer sektora w liczbie. Numer sektora należy rozumieć jako kolejny
		 * tysięczny blok składowy liczby.
		 */
		private WordsMoneyPartBuilder(int sector) {
			this.sector = sector;
		}

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Właściwość ustawia atrybut <code>this.unitCount</code> określający
		 * ilość jednostek w danym sektorze liczby.
		 * </p>
		 *
		 * @param count
		 * Ilość jednostek w danym sektorze liczby.
		 *
		 * @return Obiekt typu <code>WordsMoneyPartBuilder</code>.
		 */
		public WordsMoneyPartBuilder assignUnitCount(int count) {

			checkAssignValue(count);
			this.unitCount = count;
			return this;
		}

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Właściwość ustawia atrybut <code>this.teenCount</code> określający
		 * ilość nastek w danym sektorze liczby.
		 * </p>
		 *
		 * @param count
		 * : Ilość nastek w danym sektorze liczby.
		 *
		 * @return Obiekt typu <code>WordsMoneyPartBuilder</code>.
		 */
		public WordsMoneyPartBuilder assignTeenCount(int count) {

			checkAssignValue(count);
			this.teenCount = count;
			return this;
		}

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
		 * solid;'>
		 * Właściwość ustawia atrybut <code>this.tenCount</code> określający
		 * ilość dziesiątek w danym sektorze liczby.
		 * </p>
		 *
		 * @param count
		 * Ilość dziesiątek w danym sektorze liczby.
		 *
		 * @return Obiekt typu <code>WordsMoneyPartBuilder</code>.
		 */
		public WordsMoneyPartBuilder assignTenCount(int count) {

			checkAssignValue(count);
			this.tenCount = count;
			return this;
		}

		// -------------------------------------------------------------------------------------------------------------
		/**
		 * <p style=
		 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:solid;'>
		 * Właściwość ustawia atrybut <code>this.hundCount</code> określający
		 * ilość setek w danym sektorze liczby.
		 * </p>
		 *
		 * @param count
		 * Ilość setek w danym sektorze liczby.
		 *
		 * @return Obiekt typu <code>WordsMoneyPartBuilder</code>.
		 */
		public WordsMoneyPartBuilder assignHundCount(int count) {

			checkAssignValue(count);
			this.hundCount = count;
			return this;
		}

		public WordsOfMoneyParts build() {

			return new WordsOfMoneyParts(this);
		}

	}

}

class Slownie {

	public static void main(String[] args) {

		System.out.println(
				WordsOfMoneyParts
						.builder(3)
						.assignUnitCount(5)
						.assignTeenCount(5)
						.assignTenCount(1)
						.assignHundCount(7)
						.build()
						.toString());
		/*
		 * for (int idx = 1; idx < 999; idx++) {
		 *
		 * final Consumer<Integer> jednostki = (value) -> { final int val = ((1
		 * == (value) / 10 % 10) ? 1 : (((value) * 10) % 100) / 10); if (0 !=
		 * val) System.out.print(String.format(" jednostki:%d ", val)); };
		 *
		 * // TODO: poprawić dzisiątkę final Consumer<Integer> dziesiatki =
		 * (value) -> { // final int val = ((0 == (value % 100)) ? (value / 10)
		 * : (1== ((value * 10) % 100) // / 10) ? -1 : (value / 10) % 100);
		 * final int val = ((0 == (value % 100)) ? (value % 100) : (1 == ((value
		 * * 10) % 100) / 10) ? ((value / 10) % 100) : 0); if (0 < val)
		 * System.out.print(String.format(" dziesiątki:%d ", (value / 10) %
		 * 10)); };
		 *
		 * final Consumer<Integer> nastki = (value) -> { final int val = ((0 ==
		 * (value % 10)) ? 0 : (value % 100) / 10); if (1 == val)
		 * System.out.print(String.format(" nastki:%d ", value % 10)); };
		 *
		 * final Consumer<Integer> setki = (value) -> { final int val = value /
		 * 100; if (0 != val) System.out.print(String.format(" setki:%d ",
		 * (value / 100))); };
		 *
		 * System.out.println(String.format("wartość:%d", idx));
		 * System.out.print("{");
		 *
		 * Consumer<Integer> inwords =
		 * setki.andThen(dziesiatki).andThen(nastki).andThen(jednostki).andThen(
		 * (e) -> { }).andThen((e) -> { });
		 *
		 * inwords.accept(idx);
		 *
		 * System.out.print("}"); System.out.println(" "); }
		 */
	}

}
