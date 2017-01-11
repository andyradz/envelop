/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.exchange.xbase;

import java.util.EnumSet;

/**
 *
 * @author Izabela Radziszewska
 */
public enum XbVersion
{

    /**
     * Znacznik określa
     *
     * @author andrzej.radziszewski
     *
     */
    DBASE_2(
	    XbTableFormats.XBASE2, XbTableProperties.XBASE2),
    DBASE_3(
	    XbTableFormats.XBASE3, XbTableProperties.XBASE3),
    DBASE_4(
	    XbTableFormats.XBASE4, XbTableProperties.XBASE4),
    DBASE_5(
	    XbTableFormats.XBASE5, XbTableProperties.XBASE4),
    CLIPPER_5(
	    XbTableFormats.CLIPPER_5, XbTableProperties.XBASE4),
    FOXPRO_26(
	    XbTableFormats.CLIPPER_5, XbTableProperties.XBASE4);

    enum XbTableFormats
    {

	XBASE2
		{
		    @Override
		    public EnumSet<XbFieldType> fieldTypes()
		    {

			return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
		    }

		},
	XBASE3
		{
		    @Override
		    public EnumSet<XbFieldType> fieldTypes()
		    {

			return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
		    }
		},
	XBASE4
		{
		    @Override
		    public EnumSet<XbFieldType> fieldTypes()
		    {

			return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
		    }
		},
	XBASE5
		{
		    @Override
		    public EnumSet<XbFieldType> fieldTypes()
		    {

			return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
		    }
		},
	CLIPPER_5
		{
		    @Override
		    public EnumSet<XbFieldType> fieldTypes()
		    {

			return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
		    }
		},
	FOXPRO_5
		{
		    @Override
		    public EnumSet<XbFieldType> fieldTypes()
		    {

			return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
		    }
		};

	static
	{
	    XbFieldType.CHARACTER.assignFieldCode('C')
		    .assignFieldLength(254)
		    .assignFieldDecimalCount(0)
		    .create();

	    XbFieldType.NUMBER.assignFieldCode('N')
		    .assignFieldLength(10)
		    .assignFieldDecimalCount(0)
		    .create();

	    XbFieldType.FLOAT.assignFieldCode('F')
		    .assignFieldLength(10)
		    .assignFieldDecimalCount(0)
		    .create();

	    XbFieldType.LOGICAL.assignFieldCode('L')
		    .assignFieldLength(1)
		    .assignFieldDecimalCount(0)
		    .create();

	    XbFieldType.DATE.assignFieldCode('D')
		    .assignFieldLength(8)
		    .assignFieldDecimalCount(0)
		    .create();

	    XbFieldType.MEMO.assignFieldCode('M')
		    .assignFieldLength(10)
		    .assignFieldDecimalCount(0)
		    .create();
	}

	public abstract EnumSet<XbFieldType> fieldTypes();
    }

    enum XbTableProperties implements IXbTableProperties
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
		},
	XBASE5
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
	    return 1L;
	}
    }

    XbVersion(XbVersion.XbTableFormats format, XbVersion.XbTableProperties properties)
    {
	this.types = format;
	this.properties = properties;
    }

    public IXbTableProperties properties()
    {
	return this.properties;
    }

    public XbVersion.XbTableFormats types()
    {
	return this.types;
    }

    private final XbVersion.XbTableFormats types;
    private final XbVersion.XbTableProperties properties;
}

interface IXbTableProperties
{

    long maxRecordsPerTable();
    long maxFieldPerRecord();
    long maxDataFilesOpen();
}
