/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.system.validation;

/**
 *
 * @author Izabela Radziszewska
 */
@PeselCheck
public class Pesel
{

    public Pesel(String pesel)
    {
	this.pesel = pesel;
    }

    public String getPesel()
    {
	return this.pesel;
    }

    private String pesel;
}
