package com.codigo.aplios.envelop.system.core.xbase.structure;

public enum XbFieldType implements
		IXbFieldType {

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
	public IXbFieldType assignFieldCode(char fieldCode) {

		this.fldCode = fieldCode;
		return this;
	}

	@Override
	public IXbFieldType assignFieldLength(int fieldLength) {

		this.fldLength = fieldLength;
		return this;
	}

	@Override
	public IXbFieldType assignFieldDecimalCount(int fieldDecimal) {

		this.fldDecimal = fieldDecimal;
		return this;
	}

	@Override
	public IXbFieldType create() {

		return this;
	}

	private char fldCode;
	private int fldLength;
	private int fldDecimal;

}
