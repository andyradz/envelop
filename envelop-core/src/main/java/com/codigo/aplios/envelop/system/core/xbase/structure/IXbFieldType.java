package com.codigo.aplios.envelop.system.core.xbase.structure;

public interface IXbFieldType {
	IXbFieldType assignFieldCode(char fieldCode);

	IXbFieldType assignFieldLength(int fieldLength);

	IXbFieldType assignFieldDecimalCount(int fieldDecimal);

	IXbFieldType create();
}
