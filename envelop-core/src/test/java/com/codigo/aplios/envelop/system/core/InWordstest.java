/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.core;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.Function;

import com.codigo.aplios.envelop.system.core.domain.Money;

enum LangCodes
{
	PL,
	EN,
	NO
}

interface MoneyInWords {

	String transform(Money money);

}

/**
 *
 * @author andrzej.radziszewski
 */
class InWordstest {

	public static void main(String[] args) {

		Money money = new Money(BigDecimal.valueOf(1420930), Currency.getInstance(Locale.getDefault()));
		MoneyInWords.MONEY_IN_PLN.transform(money);
	}

	public static enum MoneyInWords
	{
		// dodać kody walut zamiast stringów
		MONEY_IN_PLN("PLN"),
		MONEY_IN_GBP("GBP");

		private final String langCode;

		private MoneyInWordPL builder;

		// private final MoneyInWords transform;
		MoneyInWords(String langCode) {
			this.langCode = langCode;
			this.builder = new MoneyInWordPL(this.langCode());
		}

		;

		public String langCode() {
			return this.langCode;
		}

		public String transform(Money money) {

			builder.buildSegments();
			builder.buildChain();
			return builder.toString();
		}

	}

	// produkt końcowy
	class MoneyInWord {

		private MoneySegment[] segments;
	}

	abstract class MoneyInWordBuilder {

		private MoneyInWord moneyInWord;

		private Money money;

		protected MoneyInWordBuilder(String lang) {

		}

		public void assignMoney(Money money) {
			this.money = money;
		}

		public abstract void buildSegments();

		public abstract void buildChain();

	}

	class MoneyInWordPL extends MoneyInWordBuilder {

		public MoneyInWordPL(String lang) {
			super(lang);
		}

		@Override
		public void buildSegments() {
			throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																			// choose Tools | Templates.
		}

		@Override
		public void buildChain() {
			throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																			// choose Tools | Templates.
		}

	}

	class MoneyInWordEN extends MoneyInWordBuilder {

		public MoneyInWordEN(String lang) {
			super(lang);
		}

		@Override
		public void buildSegments() {
			throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																			// choose Tools | Templates.
		}

		@Override
		public void buildChain() {
			throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																			// choose Tools | Templates.
		}

	}

	final class Builder {

		public String perform(Money money) {
			return new Builder(money).toString();
		}

		private final Money money;

		private Builder(Money money) {
			this.money = money;
		}

		@Override
		public String toString() {
			return MoneyWordChainBuilder.perform(MoneySegmentBuilder.perform(this.money).segments()).buildWordChain();
		}

		private class MoneySegmentBuilder {

			public static MoneySegmentBuilder perform(Money money) {
				return new MoneySegmentBuilder(money);
			}

			private MoneySegment[] segments;

			private Money money;

			private MoneySegmentBuilder(Money money) {
				this.money = money;
			}

			public MoneySegment[] segments() {
				return this.segments;
			}

			// @Override
			// public String toString() {
			// return MoneyWordChainBuilder.perform(this.moneySegment).toString();
			// }
			private void buildMoneySegment() {

			}

		}

		private static class MoneyWordChainBuilder {

			private MoneySegment[] moneySegment;

			public static MoneyWordChainBuilder perform(MoneySegment[] moneySegment) {
				return new MoneyWordChainBuilder(moneySegment);
			}

			private MoneyWordChainBuilder(MoneySegment[] moneySegment) {
				this.moneySegment = moneySegment;
			}

			private String buildWordChain() {
				return "";
			}

		}

	}

	/**
     * Klasa przechowuje obiekt Money w postaci zamiany na wyrażenie słownie.
     *
     * @author andrzej.radziszewski
     */
    final class MoneySegment {

        private final int segment;

        /**
         * @see MoneySegment#getUnits()
         */
        private final int units;

        /**
         * @see MoneySegment#getTeens()
         */
        private final int teens;

        /**
         * @see MoneySegment#getTens()
         */
        private final int tens;

        /**
         * @see MoneySegment#getHunds()
         */
        private final int hunds;

        /**
         * Podstawowy konstruktor obiektu klasy.
         */
        public MoneySegment() {

            this(0, 0, 0, 0, 0);
        }

        /**
         * <p style="color:white;background:teal;padding:4px;">
         * Podstawowy konstruktor obiektu klasy</p>
         *
         * @param units Ilość jedności w wartości liczby
         * @param teens Ilość nastek w wartości liczby
         * @param tens Ilość dziesiątek w wartości liczby
         * @param hunds Ilość setek w wartości liczby
         */
        private MoneySegment( int segment, int units, int teens, int tens, int hunds ) {
            this.segment = segment;
            this.units = units;
            this.teens = teens;
            this.tens = tens;
            this.hunds = hunds;
        }

        public int getSegment() {
            return this.segment;
        }

        /**
         * Właściwość określa liczbę jedności w wartości liczby
         *
         * @return Wartość numeryczna liczby całkowita
         */
        public int getUnits() {
            return this.units;
        }

        /**
         * Właściwość określa liczbę nastek w wartości liczby
         *
         * @return Wartość numeryczna liczby całkowita
         */
        public int getTeens() {
            return this.teens;
        }

        /**
         * Właściwość określa liczbę dziesiątek w wartości liczby
         *
         * @return Wartość numeryczna liczby całkowita
         */
        public int getTens() {
            return this.tens;
        }

        /**
         * Właściwość określa liczbę setek w wartości liczby
         *
         * @return Wartość numeryczna liczby całkowita
         */
        public int getHunds() {
            return this.hunds;
        }

    }

// public String inWords(Money number) {
// return new MoneyInWordsPL(number).transformToWords();
// static final class MoneyInWordsPLN implements MoneyInWords {
//
// private Money money;
//
// private final Function<Integer, Integer> jednostki = (value) -> {
// final int val = ((1 == (value) / 10 % 10) ? 0 : (((value) * 10) % 100) / 10);
// return (0 != val ? val : 0);
// };
//
// final Function<Integer, Integer> dziesiatki = (value) -> {
// final int val = ((0 == (((value) % 100) % 10)
// ? (((value) % 100) / 10)
// : (1 != (((value) % 100) / 10)) ? (((value) % 100) / 10) : 0));
// return (0 < val ? value / 10 % 10 : 0);
// };
//
// private final Function<Integer, Integer> nastki = (limit) -> {
// final int val = ((0 == (limit % 10)) ? 0 : (limit % 100) / 10);
// return (1 == val ? limit % 10 : 0);
//
// };
//
// private final Function<Integer, Integer> setki = (limit) -> {
// final int val = limit / 100;
// return (0 != val ? limit / 100 : 0);
// };
//
// private final HashMap<Integer, MoneyPartWords> accumulator;
//
// public MoneyInWordsPL() {
// this.accumulator = new HashMap<>();
// }
//
// @Override
// public String transformToWords(Money money) {
// this.money = money;
// this.transform();
// return null;
// }
//
// private void transform() {
// int moneyValue = this.money.getAmount().intValue();
// int mark = 1;
//
// while (moneyValue > 0) {
// this.accumulator.put(mark, new MoneyPartWords(jednostki.apply(moneyValue), nastki.apply(moneyValue),
// dziesiatki.apply(moneyValue), setki.apply(moneyValue)));
// moneyValue /= 1e3;
// mark += 1;
// }
// }
//
// private void detectSign() {
// this.money.isMinus();
// }
//
// }
//
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// // -----------------------------------------------------------------------------------------------------------------
// }
//
