package com.codigo.aplios.envelop.system.core.xbase.structure;

import java.util.EnumSet;

interface IXbTableProperties {
	long maxRecordsPerTable();

	long maxFieldPerRecord();

	long maxDataFilesOpen();
}

public enum XbVersion {
	/**
	 * Znacznik określa
	 * 
	 * @author andrzej.radziszewski
	 *
	 */
	DBASE_3(XbTableFormats.XBASE3),
	DBASE_4(XbTableFormats.XBASE4),
	DBASE_5(XbTableFormats.XBASE5),
	CLIPPER_5(XbTableFormats.CLIPPER_5),
	FOXPRO_26(XbTableFormats.CLIPPER_5);

	public enum XbTableFormats implements IXbTableProperties {
		XBASE2 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
			}

			@Override
			public long maxRecordsPerTable() {

				return 1_000_000_000;
			}

			@Override
			public long maxFieldPerRecord() {

				return 128;
			}

		},
		XBASE3 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
			}

			@Override
			public long maxRecordsPerTable() {

				return 1_000_000_000;
			}

			@Override
			public long maxFieldPerRecord() {

				return 32;
			}
		},
		XBASE4 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
			}

			@Override
			public long maxRecordsPerTable() {

				return 1_000_000_000;
			}

			@Override
			public long maxFieldPerRecord() {

				return 128;
			}
		},
		XBASE5 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
			}

			@Override
			public long maxRecordsPerTable() {

				return 1_000_000_000;
			}

			@Override
			public long maxFieldPerRecord() {

				return 128;
			}
		},
		CLIPPER_5 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
			}

			@Override
			public long maxRecordsPerTable() {

				return 1_000_000_000;
			}

			@Override
			public long maxFieldPerRecord() {

				return 128;
			}
		},
		FOXPRO_5 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMBER);
			}

			@Override
			public long maxRecordsPerTable() {

				return 1_000_000_000;
			}

			@Override
			public long maxFieldPerRecord() {

				return 128;
			}
		};

		@Override
		public long maxDataFilesOpen() {

			return 255;
		}
		
		public IXbTableProperties properties()
		{
			return new XbTableProperties(this);
		}

		static {
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
		
		class XbTableProperties implements IXbTableProperties
		{
			public XbTableProperties(XbTableFormats format)
			{
				
			}
				
			@Override
			public long maxRecordsPerTable() {

				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long maxFieldPerRecord() {

				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long maxDataFilesOpen() {

				// TODO Auto-generated method stub
				return 0;
			}			
		}
	}

	XbVersion(XbVersion.XbTableFormats format) {
		this.types = format;
	}

	private XbVersion.XbTableFormats types;
}
