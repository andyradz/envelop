package com.codigo.aplios.envelop.system.validation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PeselCheckTests {

    // -----------------------------------------------------------------------------------------------------------------
    private static Validator validator;

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void manufacturerIsNull() {
        final Pesel pesel = new Pesel("02345678903");
        Set<ConstraintViolation<Pesel>> constraintViolations = validator.validate(pesel);

        assertThat(0, is(constraintViolations.size()));
    }

    // -----------------------------------------------------------------------------------------------------------------
}
