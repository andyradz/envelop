/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author andrzej.radziszewski
 */
public class PeselValidator implements ConstraintValidator<PeselCheck, Pesel> {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void initialize(PeselCheck constraintAnnotation) {

    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isValid(Pesel value, ConstraintValidatorContext context) {
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
}
