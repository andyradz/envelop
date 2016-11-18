package com.codigo.aplios.envelop.system.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PeselCheckTests
{
    private static Validator validator;

    @BeforeClass
    public static void setUp()
    {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull()
    {
	final Pesel pesel = new Pesel(null);

	Set<ConstraintViolation<Pesel>> constraintViolations
		= validator.validate(pesel);

	assertEquals(0, constraintViolations.size());
	//assertEquals(
	//	"may not be null",
	//	constraintViolations.iterator().next().getMessage()
	//);
    }
}
