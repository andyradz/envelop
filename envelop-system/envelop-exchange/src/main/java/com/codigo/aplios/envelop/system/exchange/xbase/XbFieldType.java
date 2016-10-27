/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.exchange.xbase;

/**
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

    private char fldCode;
    private int fldLength;
    private int fldDecimal;

}
