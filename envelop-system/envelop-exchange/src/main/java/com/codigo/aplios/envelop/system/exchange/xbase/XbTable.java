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
interface IXbTableProperties
{

    long maxRecordsPerTable();

    long maxFieldPerRecord();

    long maxDataFilesOpen();
}

public class XbTable
{

    public enum RecordFormat
    {

    }

    enum RecordData
    {

    }

    // specyfikacja tabeli http://web.tiscali.it/SilvioPitti/
    public static void main(String[] args)
    {

	XbTableProperties.XBASE2.maxRecordsPerTable();
	XbTableProperties.XBASE3.maxFieldPerRecord();

    }

    enum XbTableProperties implements
	    IXbTableProperties
    {

	XBASE2
		{
		    @Override
		    public long maxRecordsPerTable()
		    {

			return 65_535;
		    }

		    @Override
		    public long maxFieldPerRecord()
		    {

			return 32;
		    }

		},
	XBASE3
		{
		    @Override
		    public long maxRecordsPerTable()
		    {

			return 1_000_000_000;
		    }

		    @Override
		    public long maxFieldPerRecord()
		    {

			return 32;
		    }

		},
	XBASE4
		{
		    @Override
		    public long maxRecordsPerTable()
		    {

			return 1_000_000_000;
		    }

		    @Override
		    public long maxFieldPerRecord()
		    {

			return 128;
		    }

		};

	@Override
	public long maxDataFilesOpen()
	{

	    return 255;
	}
    }
}
