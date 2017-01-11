/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.exchange.xbase;

/**
 * Struktura odpowiada za utrzymywanie konfiguracji dostępnych typów danych występująych w standardzie XBase.
 *
 * @author Izabela Radziszewska
 */
public enum XbFieldType implements
	IXbFieldType
{

    CHARACTER,
    NUMBER,
    LOGICAL,
    INTEGER,
    DOUBLE,
    FLOAT,
    MEMO,
    GENERAL,
    PICTURE,
    BINARY,
    DATE;

    @Override
    public IXbFieldType assignFieldCode(char fieldCode)
    {

	this.fldCode = fieldCode;
	return this;
    }

    @Override
    public IXbFieldType assignFieldLength(int fieldLength)
    {

	this.fldLength = fieldLength;
	return this;
    }

    @Override
    public IXbFieldType assignFieldDecimalCount(int fieldDecimal)
    {

	this.fldDecimal = fieldDecimal;
	return this;
    }

    @Override
    public IXbFieldType create()
    {

	return this;
    }

    /*
     Atrybut struktury odpowiada za kod przypisany do typu pola.
     */
    private char fldCode;

    /*
     Atrybut struktury odpowiada za długość pola typu danych.
     */
    private int fldLength;

    /*
     Atrybut struktury odpowiada za długość po przecinku pola typu danych.
     */
    private int fldDecimal;

}
