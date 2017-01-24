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

interface MoneyInWords {

    String transformToWords(Money money);

}

/**
 *
 * @author andrzej.radziszewski
 */
class InWordstest {

    public enum MoneyInWords {

        MONEY_IN_PLN("PLN") {

            @Override
            public String transformToWords(Money money) {
                return "";
            }

        },
        MONEY_IN_GBP("GBP") {

            @Override
            public String transformToWords(Money money) {
                return "";
            }

        };

        public abstract String transformToWords(Money money);

        private final String langCode;

        //private final MoneyInWords transform;
        MoneyInWords(String langCode) {
            this.langCode = langCode;
        }

    ;

}

}

//public String inWords(Money number) {
//            return new MoneyInWordsPL(number).transformToWords();
//        static final class MoneyInWordsPLN implements MoneyInWords {
//
//            private Money money;
//
//            private final Function<Integer, Integer> jednostki = (value) -> {
//                final int val = ((1 == (value) / 10 % 10) ? 0 : (((value) * 10) % 100) / 10);
//                return (0 != val ? val : 0);
//            };
//
//            final Function<Integer, Integer> dziesiatki = (value) -> {
//                final int val = ((0 == (((value) % 100) % 10)
//                        ? (((value) % 100) / 10)
//                        : (1 != (((value) % 100) / 10)) ? (((value) % 100) / 10) : 0));
//                return (0 < val ? value / 10 % 10 : 0);
//            };
//
//            private final Function<Integer, Integer> nastki = (limit) -> {
//                final int val = ((0 == (limit % 10)) ? 0 : (limit % 100) / 10);
//                return (1 == val ? limit % 10 : 0);
//
//            };
//
//            private final Function<Integer, Integer> setki = (limit) -> {
//                final int val = limit / 100;
//                return (0 != val ? limit / 100 : 0);
//            };
//
//            private final HashMap<Integer, MoneyPartWords> accumulator;
//
//            public MoneyInWordsPL() {
//                this.accumulator = new HashMap<>();
//            }
//
//            @Override
//            public String transformToWords(Money money) {
//                this.money = money;
//                this.transform();
//                return null;
//            }
//
//            private void transform() {
//                int moneyValue = this.money.getAmount().intValue();
//                int mark = 1;
//
//                while (moneyValue > 0) {
//                    this.accumulator.put(mark, new MoneyPartWords(jednostki.apply(moneyValue), nastki.apply(moneyValue), dziesiatki.apply(moneyValue), setki.apply(moneyValue)));
//                    moneyValue /= 1e3;
//                    mark += 1;
//                }
//            }
//
//            private void detectSign() {
//                this.money.isMinus();
//            }
//
//        }
//
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//    public static void main(String[] args) {
//
//        Money money = new Money(BigDecimal.valueOf(1420930), Currency.getInstance(Locale.getDefault()));
//        MoneyInWords.MONEY_IN_PLN.inWords(money);
//    }
//
//    // -----------------------------------------------------------------------------------------------------------------
//}
//
///**
// * Klasa przechowuje obiekt Money w postaci zamiany na wyrażenie słownie.
// *
// * @author andrzej.radziszewski
// */
final class WordsOfMoneyPart {


    private final int jednostki;

    private final int nastki;

    private final int dzisiatki;

    private final int setki;

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Podstawowy konstruktor obiektu klasy.
     */
    public WordsOfMoneyPart() {

        this(0, 0, 0, 0);
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Podstawowy konstruktor obiektu klasy.
     *
     * @param units
     * @param teens
     * @param tens
     * @param hundreds
     */
    private WordsOfMoneyPart(int units, int teens, int tens, int hundreds) {
        this.jednostki = units;
        this.nastki = teens;
        this.dzisiatki = tens;
        this.setki = hundreds;
    }

    //------------------------------------------------------------------------------------------------------------------
}
