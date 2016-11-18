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
public class XbTable
{

    private XbVersion version;

    public XbTable(XbVersion version)
    {
	this.version = version;
    }

    enum RecordFormat
    {

    }

    enum RecordData
    {

    }

    public static void main(String[] args)
    {

	XbTable tab = new XbTable(XbVersion.DBASE_4);

	long v = tab.version.properties().maxFieldPerRecord();
	System.err.println(v);

    }

}
