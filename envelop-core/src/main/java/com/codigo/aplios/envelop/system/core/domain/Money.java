package com.codigo.aplios.envelop.system.core.domain;

import static java.math.BigDecimal.ZERO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Currency;
import java.util.Objects;

import com.codigo.aplios.envelop.system.core.numeric.CurrencyNew;

/**
 * Represent an amount of money in any currency.
 *
 * <p>
 * This class assumes <em>decimal currency</em>, without funky divisions like
 * 1/5 and so on. <tt>Money</tt> objects are immutable. Like {@link BigDecimal},
 * many operations return new <tt>Money</tt> objects. In addition, most
 * operations involving more than one <tt>Money</tt> object will throw a
 * <tt>MismatchedCurrencyException</tt> if the currencies don't match.
 * 
 * <h2>Decimal Places and Scale</h2> Monetary amounts can be stored in the
 * database in various ways. Let's take the example of dollars. It may appear in
 * the database in the following ways :
 * <ul>
 * <li>as <tt>123456.78</tt>, with the usual number of decimal places associated
 * with that currency.
 * <li>as <tt>123456</tt>, without any decimal places at all.
 * <li>as <tt>123</tt>, in units of thousands of dollars.
 * <li>in some other unit, such as millions or billions of dollars.
 * </ul>
 * 
 * <p>
 * The number of decimal places or style of units is referred to as the
 * <em>scale</em> by {@link java.math.BigDecimal}. This class's constructors
 * take a <tt>BigDecimal</tt>, so you need to understand it use of the idea of
 * scale.
 * 
 * <p>
 * The scale can be negative. Using the above examples :
 * <table border='1' cellspacing='0' cellpadding='3'>
 * <tr>
 * <th>Number</th>
 * <th>Scale</th>
 * </tr>
 * <tr>
 * <td>123456.78</th>
 * <th>2</th>
 * </tr>
 * <tr>
 * <td>123456</th>
 * <th>0</th>
 * </tr>
 * <tr>
 * <td>123 (thousands)</th>
 * <th>-3</th>
 * </tr>
 * </table>
 * 
 * <p>
 * Note that scale and rounding are two separate issues. In addition, rounding
 * is only necessary for multiplication and division operations. It doesn't
 * apply to addition and subtraction.
 * 
 * <h2>Operations and Scale</h2>
 * <P>
 * Operations can be performed on items having <em>different scale</em>. For
 * example, these operations are valid (using an <em>ad hoc</em> symbolic
 * notation):
 * 
 * <PRE>
* 10.plus(1.23) => 11.23
* 10.minus(1.23) => 8.77
* 10.gt(1.23) => true
* 10.eq(10.00) => true
 * </PRE>
 * 
 * This corresponds to typical user expectations. An important exception to this
 * rule is that {@link #equals(Object)} is sensitive to scale (while
 * {@link #eq(Money)} is not) . That is,
 * 
 * <PRE>
*   10.equals(10.00) => false
 * </PRE>
 * 
 * <h2>Multiplication, Division and Extra Decimal Places</h2>
 * <P>
 * Operations involving multiplication and division are different, since the
 * result can have a scale which exceeds that expected for the given currency.
 * For example
 * 
 * <PRE>
 * ($10.00).times(0.1256) => $1.256
 * </PRE>
 * 
 * which has more than two decimals. In such cases, <em>this class will always
 * round to the expected number of decimal places for that currency.</em> This
 * is the simplest policy, and likely conforms to the expectations of most end
 * users.
 * 
 * <P>
 * This class takes either an <tt>int</tt> or a {@link BigDecimal} for its
 * multiplication and division methods. It doesn't take <tt>float</tt> or
 * <tt>double</tt> for those methods, since those types don't interact well with
 * <tt>BigDecimal</tt>. Instead, the <tt>BigDecimal</tt> class must be used when
 * the factor or divisor is a non-integer.
 * 
 * <P>
 * <em>The {@link #init(CurrencyNew, RoundingMode)} method must be called at
 * least once before using the other members of this class.</em> It establishes
 * your desired defaults. Typically, it will be called once (and only once) upon
 * startup.
 * 
 * <P>
 * Various methods in this class have unusually terse names, such as {@link #lt}
 * and {@link #gt}. The intent is that such names will improve the legibility of
 * mathematical expressions. Example :
 * 
 * <PRE>
 * if (amount.lt(hundred)) {
 * 	cost = amount.times(price);
 * }
 * </PRE>
 */

public final class Money implements Comparable<Money>, Serializable {

	/**
	 * Thrown when a set of <tt>Money</tt> objects do not have matching
	 * currencies.
	 *
	 * <p>
	 * For example, adding together Euros and Dollars does not make any sense.</p>
	 */
	public static final class MismatchedCurrencyException extends RuntimeException {
		private static final long serialVersionUID = -1271759781507948994L;

		MismatchedCurrencyException(String aMessage) {
			super(aMessage);
		}

	}

	/**
	 * Set default values for currency and rounding style.
	 *
	 * <em>Your application must call this method upon startup</em>. This method
	 * should usually be called only once (upon startup).
	 *
	 * <P>
	 * The recommended rounding style is {@link RoundingMode#HALF_EVEN}, also
	 * called <em>banker's rounding</em>; this rounding style introduces the
	 * least bias.
	 *
	 * <P>
	 * Setting these defaults allow you to use the more terse constructors of
	 * this class, which are much more convenient.
	 *
	 * <P>
	 * (In a servlet environment, each app has its own classloader. Calling this
	 * method in one app will never affect the operation of a second app running
	 * in the same servlet container. They are independent.)
	 *
	 * @param defaultCurrency
	 * @param defaultRounding
	 */
	public static void init(Currency defaultCurrency, RoundingMode defaultRounding) {

		DEFAULT_CURRENCY = defaultCurrency;
		DEFAULT_ROUNDING = defaultRounding;
	}

	/**
	 * Full constructor.
	 *
	 * @param amount
	 * is required, can be positive or negative. The number of decimals in the
	 * amount cannot <em>exceed</em> the maximum number of decimals for the
	 * given {@link Currency}. It's possible to create a <tt>Money</tt> object
	 * in terms of 'thousands of dollars', for instance. Such an amount would
	 * have a scale of -3.
	 * @param currency
	 * is required.
	 * @param roundingStyle
	 * is required, must match a rounding style used by {@link BigDecimal}.
	 */
	public Money(BigDecimal amount, Currency currency, RoundingMode roundingStyle) {
		this.amount = amount;
		this.currency = currency;
		this.rounding = roundingStyle;
		validateState();
	}

	/**
	 * Constructor taking only the money amount.
	 *
	 * <P>
	 * The currency and rounding style both take default values.
	 *
	 * @param amount
	 * is required, can be positive or negative.
	 */
	public Money(BigDecimal amount) {
		this(amount, DEFAULT_CURRENCY, DEFAULT_ROUNDING);
	}

	/**
	 * Constructor taking the money amount and currency.
	 *
	 * <P>
	 * The rounding style takes a default value.
	 *
	 * @param amount
	 * is required, can be positive or negative.
	 * @param currency
	 * is required.
	 */
	public Money(BigDecimal amount, Currency currency) {
		this(amount, currency, DEFAULT_ROUNDING);
	}

	/**
	 * Metoda zwraca wartość waluty.
	 *
	 * @return Wartość numeryczna.
	 */
	public BigDecimal getAmount() {

		return amount;
	}

	/**
	 * Metoda zwraca symbol waluty.
	 *
	 * @return Wartość tekstowa.
	 */
	public Currency getCurrency() {

		return currency;
	}

	/**
	 * Return the rounding style passed to the constructor, or the default
	 * rounding style.
	 */
	public RoundingMode getRoundingStyle() {

		return rounding;
	}

	/**
	 * Return <tt>true</tt> only if <tt>aThat</tt> <tt>Money</tt> has the same
	 * currency as this <tt>Money</tt>.
	 */
	public boolean isSameCurrencyAs(Money money) {

		boolean result = false;
		if (money != null) {
			result = this.currency.equals(money.currency);
		}
		return result;
	}

	/**
	 * Return <tt>true</tt> only if the amount is positive.
	 */
	public boolean isPlus() {

		return amount.compareTo(ZERO) > 0;
	}

	/**
	 * Return <tt>true</tt> only if the amount is negative.
	 */
	public boolean isMinus() {

		return amount.compareTo(ZERO) < 0;
	}

	/**
	 * Return <tt>true</tt> only if the amount is zero.
	 */
	public boolean isZero() {

		return amount.compareTo(ZERO) == 0;
	}

	/**
	 * Add <tt>aThat</tt> <tt>Money</tt> to this <tt>Money</tt>. Currencies must
	 * match.
	 */
	public Money plus(Money money) {

		checkCurrenciesMatch(money);
		return new Money(amount.add(money.amount), currency, rounding);
	}

	/**
	 * Subtract <tt>aThat</tt> <tt>Money</tt> from this <tt>Money</tt>.
	 * Currencies must match.
	 */
	public Money minus(Money money) {

		checkCurrenciesMatch(money);
		return new Money(amount.subtract(money.amount), currency, rounding);
	}

	/**
	 * Sum a collection of <tt>Money</tt> objects. Currencies must match. You
	 * are encouraged to use database summary functions whenever possible,
	 * instead of this method.
	 *
	 * @param moneys
	 * collection of <tt>Money</tt> objects, all of the same currency. If the
	 * collection is empty, then a zero value is returned.
	 * @param currencyIfEmpty
	 * is used only when <tt>aMoneys</tt> is empty; that way, this method can
	 * return a zero amount in the desired currency.
	 */
	public static Money sum(Collection<Money> moneys, Currency currencyIfEmpty) {

		Money sum = new Money(ZERO, currencyIfEmpty);
		for (Money money : moneys) {
			sum = sum.plus(money);
		}
		return sum;
	}

	/**
	 * Equals (insensitive to scale).
	 *
	 * <P>
	 * Return <tt>true</tt> only if the amounts are equal. Currencies must
	 * match. This method is <em>not</em> synonymous with the <tt>equals</tt>
	 * method.
	 *
	 * @param money
	 *
	 * @return
	 */
	public boolean eq(Money money) {

		checkCurrenciesMatch(money);
		return compareAmount(money) == 0;
	}

	/**
	 * Procedura wykonuje sprawdzenie logiczne czy przekazana wartość pieniężna
	 * jest większa od wartości pieniężnej bieżącego obiektu.
	 *
	 * @param money
	 * Obiekt Wartość pienieżna
	 *
	 * @return
	 */
	public boolean gt(Money money) {

		checkCurrenciesMatch(money);
		return compareAmount(money) > 0;
	}

	/**
	 * Greater than or equal to.
	 *
	 * <P>
	 * Return <tt>true</tt> only if 'this' amount is greater than or equal to
	 * 'that' amount. Currencies must match.
	 */
	public boolean gteq(Money money) {

		checkCurrenciesMatch(money);
		return compareAmount(money) >= 0;
	}

	/**
	 * Less than.
	 *
	 * <P>
	 * Return <tt>true</tt> only if 'this' amount is less than 'that' amount.
	 * Currencies must match.
	 */
	public boolean lt(Money aThat) {

		checkCurrenciesMatch(aThat);
		return compareAmount(aThat) < 0;
	}

	/**
	 * Less than or equal to.
	 *
	 * <P>
	 * Return <tt>true</tt> only if 'this' amount is less than or equal to
	 * 'that' amount. Currencies must match.
	 */
	public boolean lteq(Money money) {

		checkCurrenciesMatch(money);
		return compareAmount(money) <= 0;
	}

	/**
	 * Multiply this <tt>Money</tt> by an integral factor.
	 *
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this'
	 * <tt>Money</tt>.
	 */
	public Money times(int factor) {

		final BigDecimal newFactor = new BigDecimal(factor);
		final BigDecimal newAmount = amount.multiply(newFactor);
		return new Money(newAmount, currency, rounding);
	}

	/**
	 * Multiply this <tt>Money</tt> by an non-integral factor (having a decimal
	 * point).
	 *
	 * <P>
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this'
	 * <tt>Money</tt>.
	 */
	public Money times(double factor) {

		BigDecimal newAmount = amount.multiply(asBigDecimal(factor));
		newAmount = newAmount.setScale(getNumDecimalsForCurrency(), rounding);

		return new Money(newAmount, currency, rounding);
	}

	/**
	 * Divide this <tt>Money</tt> by an integral divisor.
	 *
	 * <P>
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this'
	 * <tt>Money</tt>.
	 */
	public Money div(int aDivisor) {

		BigDecimal divisor = new BigDecimal(aDivisor);
		BigDecimal newAmount = amount.divide(divisor, rounding);
		return new Money(newAmount, currency, rounding);
	}

	/**
	 * Divide this <tt>Money</tt> by an non-integral divisor.
	 *
	 * <P>
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this'
	 * <tt>Money</tt>.
	 */
	public Money div(double aDivisor) {

		BigDecimal newAmount = amount.divide(asBigDecimal(aDivisor), rounding);
		return new Money(newAmount, currency, rounding);
	}

	/**
	 * Return the absolute value of the amount.
	 */
	public Money abs() {

		return isPlus() ? this : times(-1);
	}

	/**
	 * Return the amount x (-1).
	 */
	public Money negate() {

		return times(-1);
	}

	/**
	 * Returns {@link #getAmount()}.getPlainString() + space +
	 * {@link #getCurrency()}.getSymbol().
	 *
	 * <P>
	 * The return value uses the runtime's <em>default locale</em>, and will not
	 * always be suitable for display to an end user.
	 */
	@Override
	public String toString() {

		return amount.toPlainString() + " " + currency.getSymbol();
	}

	/**
	 * Like {@link BigDecimal#equals(java.lang.Object)}, this <tt>equals</tt>
	 * method is also sensitive to scale.
	 *
	 * For example, <tt>10</tt> is <em>not</em> equal to <tt>10.00</tt> The
	 * {@link #eq(Money)} method, on the other hand, is <em>not</em> sensitive
	 * to scale.
	 */
	@Override
	public boolean equals(Object aThat) {

		if (this == aThat)
			return true;
		if (!(aThat instanceof Money))
			return false;
		Money that = (Money) aThat;
		// the object fields are never null :
		boolean result = (this.amount.equals(that.amount));
		result = result && (this.currency.equals(that.currency));
		result = result && (this.rounding == that.rounding);
		return result;
	}

	@Override
	public int hashCode() {

		if (hashCode == 0) {
			hashCode = HASH_SEED;
			hashCode = HASH_FACTOR * hashCode + amount.hashCode();
			hashCode = HASH_FACTOR * hashCode + currency.hashCode();
			hashCode = HASH_FACTOR * hashCode + rounding.hashCode();
		}
		return hashCode;
	}

	@Override
	public int compareTo(Money aThat) {

		final int EQUAL = 0;

		if (this == aThat)
			return EQUAL;

		// the object fields are never null
		int comparison = this.amount.compareTo(aThat.amount);
		if (comparison != EQUAL)
			return comparison;

		comparison = this.currency.getCurrencyCode().compareTo(aThat.currency.getCurrencyCode());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.rounding.compareTo(aThat.rounding);
		if (comparison != EQUAL)
			return comparison;

		return EQUAL;
	}

	/**
	 * <p style=
	 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
	 * solid;'>
	 * The money amount. Never null
	 * </p>
	 */
	private BigDecimal amount;

	/**
	 * <p style=
	 * 'color:white;background:rgba(1,113,113,0.5);padding:12px;border-radius:4px;border-style:
	 * solid;'>
	 * The currency of the money, such as US Dollars or Euros. Never null.
	 * </p>
	 */
	private final Currency currency;

	/**
	 * The rounding style to be used. See {@link BigDecimal}.
	 *
	 * @serial
	 */
	private final RoundingMode rounding;

	/**
	 * Pole klasy reprezentuje domyślną instancję obiektu klasy, z której
	 * korzystamy w momencie tworzenia obiektu z domyślnymi parametrami. Dla
	 * tego scenariusza tworzenia obiektu wywoływany jest domyslny konstruktor.
	 */
	private static Currency DEFAULT_CURRENCY;

	/**
	 * The default rounding style to be used if no currency is passed to the
	 * constructor. See {@link BigDecimal}.
	 */
	private static RoundingMode DEFAULT_ROUNDING;

	/**
	 * @serial
	 */
	private int hashCode;

	private static final int HASH_SEED = 23;

	private static final int HASH_FACTOR = 37;

	/**
	 * Determines if a deserialized file is compatible with this class.
	 *
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 *
	 * Not necessary to include in first version of the class, but included here
	 * as a reminder of its importance.
	 */
	private static final long serialVersionUID = 7526471155622776147L;

	/**
	 * Always treat de-serialization as a full-blown constructor, by validating
	 * the final state of the de-serialized object.
	 */
	private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

		// always perform the default de-serialization first
		inputStream.defaultReadObject();
		// defensive copy for mutable date field
		// BigDecimal is not technically immutable, since its non-final
		amount = new BigDecimal(amount.toPlainString());
		// ensure that object state has not been corrupted or tampered with
		// maliciously
		validateState();
	}

	private void writeObject(ObjectOutputStream outputStream) throws IOException {

		// perform the default serialization for all non-transient, non-static
		// fields
		outputStream.defaultWriteObject();
	}

	private void validateState() {

		if (Objects.isNull(amount))
			throw new IllegalArgumentException("Amount cannot be null");

		if (Objects.isNull(currency))
			throw new IllegalArgumentException("Currency cannot be null");

		if (amount.scale() > getNumDecimalsForCurrency())
			throw new IllegalArgumentException(
					"Number of decimals is "
							+ amount.scale()
							+ ", but currency only takes "
							+ getNumDecimalsForCurrency()
							+ " decimals.");
	}

	private int getNumDecimalsForCurrency() {

		return currency.getDefaultFractionDigits();
	}

	private void checkCurrenciesMatch(Money money) {

		if (!this.currency.equals(money.getCurrency())) {
			throw new MismatchedCurrencyException(
					money.getCurrency() + " doesn't match the expected currency : " + currency);
		}
	}

	/**
	 * Ignores scale: 0 same as 0.00
	 */
	private int compareAmount(Money money) {

		return this.amount.compareTo(money.amount);
	}

	/**
	 * @param decimal
	 * @return
	 */
	private BigDecimal asBigDecimal(double decimal) {

		final String asString = Double.toString(decimal);
		return new BigDecimal(asString);
	}
}
