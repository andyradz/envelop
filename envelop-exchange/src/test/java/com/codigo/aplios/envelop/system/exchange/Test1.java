/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.exchange;

import com.codigo.aplios.envelop.system.exchange.xbase.XbLogical;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Izabela Radziszewska
 */
public class Test1
{

    private static Validator validator;

    @BeforeClass
    public static void setUp()
    {
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//	validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull()
    {
//	XbLogical car = new XbLogical();
//
//	Set<ConstraintViolation<XbLogical>> constraintViolations
//		= validator.validate(car);

	//assertEquals(1, constraintViolations.size());
	//assertEquals(
	//	"may not be null",
	//	constraintViolations.iterator().next().getMessage()
	//);
    }
}
