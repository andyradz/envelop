package com.codigo.aplios.envelop.explorer.tools;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.codigo.aplios.envelop.explorer.tools.Checksum.HashDigit;

/**
 * Klasa realizuje testy wyznacznia sumy kontrolnej z wykorzystaniem dostępnych algorytmów.
 * 
 * @author andrzej.radziszewski
 * @version 1.0.0.0 beta
 * @since 1.0.0.0
 *
 * Code file  : ChecksumTests.java
 * Create date: 29.04.2017
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChecksumTests {

	/**
	 * Atrybut klasy reprezenuje ścieżkę bezwzględną do pliku na dysku.
	 */
	private final static Path filePath =
			Paths.get("d:/codigo.warehouse/aplikacje/wndows/netbeans/netbeans-8.2-windows.exe");

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 *  Delegat realizuje drukowanie nagłówka sumy kontrolnej.
	 */
	private final static Consumer<String> printInfo = (sequence) -> {
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out
				.println("Generowanie skrótu hashcode algorytmem " + sequence + " dla elementu " + filePath.toString());
		System.out.println("-----------------------------------------------------------------------------------------");
	};

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 *  Delegat realizuje drukowanie sumy kontrolnej.  
	 */
	private final static Consumer<String> printChecksum = (checkusm) -> {
		System.out.println(checkusm);
	};

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura drukuje nagłówek związany z wykonanym wyznaczaniem sumy kontrolnej. 
	 * 
	 * @param info Sekwencja znaków opisująca nagłówek sumy kontrolnej
	 * @param checksum Sekwencja znaków sumy kontrolnej
	 */
	private static final void printResult(String info, String checksum) {
		printInfo.accept(info);
		printChecksum.accept(checksum);
		System.out.println('\n');
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura przeprwoadza test wyznaczenia sumy kontrolnej z użyciem algorytmu SHA512.
	 * Wykonywany test sprawdza czy wyznaczony hash code jest nie pusty. 
	 */
	@Test
	public void testChecksumMD2() {
		final String checksum = HashDigit.MD2.evalute(filePath);
		assertTrue(!checksum.isEmpty());
		printResult(Checksum.HashDigit.MD2.algorithmName(), HashDigit.MD2.evalute(filePath));

	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura przeprwoadza test wyznaczenia sumy kontrolnej z użyciem algorytmu MD5.
	 * Wykonywany test sprawdza czy wyznaczony hash code jest nie pusty. 
	 */
	@Test
	public void testChecksumMD5() {
		final String checksum = HashDigit.MD5.evalute(filePath);
		assertTrue(!checksum.isEmpty());
		printResult(Checksum.HashDigit.MD5.algorithmName(), HashDigit.MD5.evalute(filePath));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura przeprwoadza test wyznaczenia sumy kontrolnej z użyciem algorytmu SHA1.
	 * Wykonywany test sprawdza czy wyznaczony hash code jest nie pusty. 
	 */
	@Test
	public void testChecksumSHA1() {
		final String checksum = HashDigit.SHA1.evalute(filePath);
		assertTrue(!checksum.isEmpty());
		printResult(Checksum.HashDigit.SHA1.algorithmName(), HashDigit.SHA1.evalute(filePath));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura przeprwoadza test wyznaczenia sumy kontrolnej z użyciem algorytmu SHA256.
	 * Wykonywany test sprawdza czy wyznaczony hash code jest nie pusty. 
	 */
	@Test
	public void testChecksumSHA256() {
		final String checksum = HashDigit.SHA256.evalute(filePath);
		assertTrue(!checksum.isEmpty());
		printResult(Checksum.HashDigit.SHA256.algorithmName(), HashDigit.SHA256.evalute(filePath));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura przeprwoadza test wyznaczenia sumy kontrolnej z użyciem algorytmu SHA384.
	 * Wykonywany test sprawdza czy wyznaczony hash code jest nie pusty. 
	 */
	@Test
	public void testChecksumSHA384() {
		final String checksum = HashDigit.SHA384.evalute(filePath);
		assertTrue(!checksum.isEmpty());
		printResult(Checksum.HashDigit.SHA384.algorithmName(), HashDigit.SHA384.evalute(filePath));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura przeprwoadza test wyznaczenia sumy kontrolnej z użyciem algorytmu SHA512.
	 * Wykonywany test sprawdza czy wyznaczony hash code jest nie pusty. 
	 */
	@Test
	public void testChecksumSHA512() {
		final String checksum = HashDigit.SHA512.evalute(filePath);
		assertTrue(!checksum.isEmpty());
		printResult(Checksum.HashDigit.SHA512.algorithmName(), checksum);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
