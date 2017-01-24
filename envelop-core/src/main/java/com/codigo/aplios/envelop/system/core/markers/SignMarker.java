/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.core.markers;

/**
 * Klasa typu Enum określa wartość dla określenia znaku plusa używanego w potocznym znaczeniu lub w operacjach
 * arytmetycznych.
 *
 * File SignMarker.java Date 24.01.2017
 *
 * @author andrzej.radziszewski
 */
public enum SignMarker {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Marker wyznacza wartość dla określenia plus
     */
    MINUS {
        @Override
        public char getSymbol() {
            return '-';
        }

        @Override
        public String getChain() {
            return "minus";
        }

    },
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Marker wyznacza wartość dla określenia +
     */
    PLUS {
        @Override
        public char getSymbol() {
            return '+';
        }

        @Override
        public String getChain() {
            return "plus";
        }

    };

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Metoda zwraca reprezentację markera + w postaci symbolu.
     *
     * @return Wartość pojedyńczego znaku.
     */
    public abstract char getSymbol();

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Metoda zwraca reprezentację markera + w postaci tekstu.
     *
     * @return Wartość ciągu znaków.
     */
    public abstract String getChain();

    //------------------------------------------------------------------------------------------------------------------
}

interface Convertable<F, T> {
    T convert(F instance);

}

interface Transforable<T> {
    T transform(T instance);
}
