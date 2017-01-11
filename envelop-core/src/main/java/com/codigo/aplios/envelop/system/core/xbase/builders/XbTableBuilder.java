package com.codigo.aplios.envelop.system.core.xbase.builders;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.codigo.aplios.envelop.system.core.xbase.structure.XbFieldType;
import com.codigo.aplios.envelop.system.core.xbase.structure.XbVersion;



public class XbTableBuilder {

	interface IXbTableFormat {
		IXbTableName assignFormat(XbVersion.XbTableFormats format);
	}

	interface IXbTableName {
		IXbTableField assignName(String tableName);
	}

	interface IXbTableField {
		XbFieldType setFieldName(String name);

		IXbTableField setFieldType(String name);
	}

	interface IXbFieldCreate {
		void create();
	}

	interface IXbFieldKind {
		void setFieldKind();
	}

	public static XbTableBuilder init() {

		return new XbTableBuilder();
	}

	// http://mindprod.com/jgloss/bytebuffer.html
	// http://www.tek-tips.com/faqs.cfm?fid=3162
	// http://www.oocities.org/geoff_wass/dBASE/GaryWhite/dBASE/FAQ/qformt.htm
	public static void main(String[] args) throws IOException, InterruptedException {

		// EnumSet<?> x3 = XbTableBuilder.XbTableFormats.XBASE3.fieldTypes();
		// EnumSet<?> x2 = XbTableBuilder.XbTableFormats.XBASE2.fieldTypes();
		XbVersion.XbTableFormats.CLIPPER_5.fieldTypes();

		// 2 Foxbase
		// 3 Foxbase/Foxpro/dBaseIII/IV/V no memo
		// 48 Visual Foxpro
		// 67 dBase IV SQL table no memo
		// 99 dBase IV SQL system file no memo
		// 131 Foxbase/dBaseIII Plus with memo
		// 139 dBaseIV/V with memo
		// 203 dBaseIV SQL table with memo
		// 245 Foxpro 2.x with memo
		// 251 Foxbase
		// int bufPoz = 0, rem = 0;
		ByteBuffer simple = ByteBuffer.allocate(32);
		// simple.put((byte)0xff);
		// //1
		// bufPoz = simple.position();
		//
		// simple.position(5);
		// simple.put((byte)0xff);
		// //6
		// bufPoz = simple.position();
		//
		// //4
		// rem = simple.remaining();
		// simple.limit(7);
		//
		// simple.rewind();

		Path path = Paths.get("d://test-write.txt");
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
		// AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,
		// StandardOpenOption.WRITE);
		//
		// ByteBuffer buffer = ByteBuffer.allocate(5);
		// long position = 0;
		//
		// for (long idx = 0; idx < 1; idx++) {
		//
		// buffer.put("ABSDSDSDSDSDSDSSDSDSDDSSDSDDS".getBytes());
		// fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>()
		// {
		//
		// @Override
		// public void completed(Integer result, ByteBuffer attachment) {
		//
		// System.out.println("bytes written: " + result);
		// }
		//
		// @Override
		// public void failed(Throwable exc, ByteBuffer attachment) {
		//
		// System.out.println("Write failed");
		// exc.printStackTrace();
		// }
		// });
		// buffer.flip();
		// position++;
		// }
		//

		// Path path = Paths.get("d://dbase.dbf");
		//
		// AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path,
		// StandardOpenOption.READ);
		//
		// ByteBuffer buffer = ByteBuffer.allocate(1024);
		// long position = 0;
		//
		// Future<Integer> operation = fileChannel.read(buffer, position);
		//
		// while (!operation.isDone())
		// ;
		//
		// buffer.flip();
		// byte[] data = new byte[buffer.limit()];
		// buffer.get(data);
		// System.out.println(new String(data));
		// buffer.clear();

		/*
		 * Path path = Paths.get("data/test-write.txt");
		 * AsynchronousFileChannel fileChannel =
		 * AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
		 * 
		 * ByteBuffer buffer = ByteBuffer.allocate(1024);
		 * long position = 0;
		 * 
		 * buffer.put("test data".getBytes());
		 * buffer.flip();
		 * 
		 * Future<Integer> operation = fileChannel.write(buffer, position);
		 * buffer.clear();
		 * 
		 * while(!operation.isDone());
		 * 
		 * System.out.println("Write done");
		 */

		FileInputStream ios = null;
		DataInputStream dis = null;
		FileChannel chn = null;
		try {
			ios = new FileInputStream("d://dbase_03.dbf");
			dis = new DataInputStream(ios);
			chn = ios.getChannel();
			int position = 0;
			for (int idx = 0; idx < 32; idx++) {
				byte val = dis.readByte();
				simple.put(val);
				System.out.println(simple.get(position));
				// simple.flip();
				position++;

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		Charset c = Charset.forName("ISO-8859-1");
		c = Charset.forName("Cp1250");
		c = Charset.forName("Cp857");

		// XbTableBuilder.init()
		// .assignFormat(XbFormats.XBASE3)
		// .assignName("Table1")
		// .setFieldName("2323").CHARACTER.create();
		// .setFieldName("sdsd").CHARACTER.
		// .setFieldType("");

		// final IXbField xbChar = XbField.CHARACTER.assignFieldCode('C')
		// .assignFieldLength(254)
		// .assignFieldDecimal(2)
		// .create();
		//
		// final IXbField xbNum = XbField.NUMERIC.assignFieldCode('N')
		// .assignFieldLength(18)
		// .assignFieldDecimal(0);
		//
		// final IXbField xbDate = XbField.DATE.assignFieldCode('D')
		// .assignFieldLength(8)
		// .assignFieldDecimal(0);
		//
		// final IXbField xbLogic = XbField.LOGICAL.assignFieldCode('L')
		// .assignFieldLength(1)
		// .assignFieldDecimal(0);
		//
		// final IXbField xbFloat = XbField.FLOAT.assignFieldCode('F')
		// .assignFieldLength(20)
		// .assignFieldDecimal(0);
		//
		// EnumSet<XbField> fieldSet = EnumSet.of(XbField.CHARACTER, XbField.NUMERIC);

	}
}
