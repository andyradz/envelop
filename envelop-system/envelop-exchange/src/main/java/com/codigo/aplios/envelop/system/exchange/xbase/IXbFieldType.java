/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.exchange.xbase;

/**
 * Interfejs reprezentuje zestaw metod odpowiadającyh za tworzenie struktury opisującej typ danych pliku XBase.
 *
 * @author Izabela Radziszewska
 */
public interface IXbFieldType
{
    /*
     Metoda odpowiada za przypisanie kodu pola struktury XBase.
     */
    IXbFieldType assignFieldCode(char fieldCode);

    /*
     Metoda odpowiada za przypisanie długości pola struktury XBase.
     */
    IXbFieldType assignFieldLength(int fieldLength);

    /*
     Metoda odpowiada za przypisanie długości po przecinku struktury XBase
     */
    IXbFieldType assignFieldDecimalCount(int fieldDecimal);

    /*
     Metoda odpowiada za utworzenie struktury pola XBase.
     */
    IXbFieldType create();
}
