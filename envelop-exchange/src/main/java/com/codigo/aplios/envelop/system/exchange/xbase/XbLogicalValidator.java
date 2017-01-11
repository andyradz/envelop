/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.exchange.xbase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Izabela Radziszewska
 */
public class XbLogicalValidator implements ConstraintValidator<XbLogicalCheck, String>
{

    @Override
    public void initialize(XbLogicalCheck constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
	return false;
    }

}
