package com.codigo.aplios.envelop.system.core.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.codigo.aplios.envelop.system.core.identity.PeselIdentity;
import com.codigo.aplios.envelop.system.core.test.inf.Repeat;
import com.codigo.aplios.envelop.system.core.test.inf.RepeatRule;



public class PeselValidatorTest {

	private static Validator validator;
	

	@BeforeClass
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@Repeat(times = 100000)
	public void testEmailExistsIncorrect() throws NullPointerException, IllegalArgumentException, Exception {
		
		PeselIdentity pesel = new PeselIdentity("79062601652");
				
		Set<ConstraintViolation<PeselIdentity>> violations = validator.validate(pesel);

		assertEquals(0, violations.size());
	}

	@Rule
	public RepeatRule rule = new RepeatRule();

	// @Test
	// @Repeat(times = 100000)
	// public void shouldBeValidTrue() throws Exception {
	//
	// final PeselValidator val = new PeselValidator("95101399913");
	// assertTrue(val.);
	// }
	//
	// @Test
	// @Repeat(times = 100000)
	// public void shouldBeValidFalse() throws Exception {
	//
	// final PeselValidator val = new PeselValidator("95101399910");
	// assertFalse(val.isValid());
	// }
	//
	// @Test()
	// @Repeat(times = 100000)
	// public void shouldThrowNullPointerException() throws NullPointerException {
	//
	// Throwable thw = null;
	// try {
	// new PeselValidator(null);
	// } catch (Throwable ex) {
	// thw = ex;
	// }
	//
	// assertNotNull(thw);
	// assertEquals(NullPointerException.class, thw.getClass());
	// }
	//
	// @Test()
	// @Repeat(times = 100000)
	// public void sholudThrowException() throws IllegalArgumentException {
	//
	// Throwable thw = null;
	// try {
	// new PeselValidator("23");
	// } catch (Throwable ex) {
	// thw = ex;
	// }
	//
	// assertNotNull(thw);
	// assertEquals(IllegalArgumentException.class, thw.getClass());
	// }
}
