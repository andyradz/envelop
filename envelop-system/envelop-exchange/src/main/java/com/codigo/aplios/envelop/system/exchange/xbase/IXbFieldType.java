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
public interface IXbFieldType
{
    IXbFieldType assignFieldCode(char fieldCode);

    IXbFieldType assignFieldLength(int fieldLength);

    IXbFieldType assignFieldDecimalCount(int fieldDecimal);

    IXbFieldType create();
}
