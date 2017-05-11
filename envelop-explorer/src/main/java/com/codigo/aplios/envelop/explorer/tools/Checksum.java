
package com.codigo.aplios.envelop.explorer.tools;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Klasa realizuje generowanie hashowanego sktótu dla ciagu bajtów.
 * 
 * @author andrzej.radziszewski
 * @since 2017
 * @version 1.0 alfa
 *
 * Code file  : Checksum.java
 * Create date: 25.04.2017
 *	
 * Najczęściej wykorzystwanym przypadkiem będzie generowanie skrótu z pliku lub ciągu bajtów.
 */
public class Checksum {

	/**
	 *  Znacznik określa rodzaj stosowanego algorytmu haszującego. 
	 */
	public static enum HashDigit
	{

		MD2("MD2"),
		MD5("MD5"),
		SHA1("SHA-1"),
		SHA256("SHA-256"),
		SHA384("SHA-384"),
		SHA512("SHA-512");

		// -------------------------------------------------------------------------------------------------------------

		/**
		 *  Nazwa algorytmu generującego hash code.
		 */
		private final String algorithm;

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Podstawowy konstruktor obiektu.
		 *
		 * @param algorithm
		 *        Typ algorytmu wskazany do generowania hash code.
		 */
		HashDigit(String algorithm) {
			if (Objects.isNull(algorithm) || algorithm.isEmpty())
				throw new NullPointerException("Wartość parametru algorithm nie może być pusta!");
			this.algorithm = algorithm;
		}

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Procedura wykonuje obliczenie hash code z przekaznych danych.
		 * Wyliczenie hash code odbywa się dla ciągu znaków.
		 *
		 * @param sequence
		 *        Sekwencja znaków
		 * @return Wartość typu <code>String</code>
		 */

		public String evalute(String sequence) {

			Supplier<String> hashDigit = () -> {
				MessageDigest md = null;
				byte byteData[] = null;
				try {
					md = MessageDigest.getInstance(this.algorithm);
					md.update(sequence.getBytes());
					byteData = md.digest();
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				// convert the byte to hex format
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				return sb.toString();
			};
			return hashDigit.get();
		}

		// -------------------------------------------------------------------------------------------------------------

		/**
		* Procedura wykonuje obliczenie skrótu z przekaznych danych.
		*
		* @param file
		         Plik danych
		* @return Wartość typu <code>String</code>
		* @throws FileNotFoundException
		*/
		public String evalute(Path filePath) {

			Supplier<String> hashDigit = () -> {
				MessageDigest digest = null;
				byte[] arrayByte = null;
				try {
					digest = MessageDigest.getInstance(this.algorithm);
					RandomAccessFile inFile = new RandomAccessFile(filePath.toFile(), "r");
					FileChannel fileChannel = inFile.getChannel();
					ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
					while (fileChannel.read(buffer) > 0) {
						buffer.flip();
						digest.update(buffer.array(), 0, buffer.limit());
						buffer.clear(); // do something with the data and clear/compact it.
					}
					arrayByte = digest.digest();
					fileChannel.close();
					inFile.close();

				}
				catch (Exception ex) {
					System.out.println(ex);
				}
				// convert the byte to hex format method 1
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < arrayByte.length; i++) {
					sb.append(Integer.toString((arrayByte[i] & 0xff) + 0x100, 16).substring(1));
				}
				return sb.toString().toUpperCase();
			};
			return hashDigit.get();
		}

		// -------------------------------------------------------------------------------------------------------------

		/**
		 * Metoda zwraca nazwę zastosowanego algorytmu haszującego.
		 * 
		 * @return Sekwencja <cod>String</code> identyfikująca algorytm haszujący
		 */
		public String algorithmName() {
			return this.algorithm;
		}

		// -------------------------------------------------------------------------------------------------------------

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return null;
		}

	}

	// -----------------------------------------------------------------------------------------------------------------
}
