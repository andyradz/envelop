package com.codigo.aplios.envelop.system.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @author andrzej.radziszewski
 */
@PeselCheck
public class Pesel {

	// -----------------------------------------------------------------------------------------------------------------

	public Pesel(String pesel) {
		this.pesel = pesel;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getPesel() {
		return this.pesel;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@NotNull
	@Length(max = 11, min = 11, message = "długość")
	@Pattern(regexp = "\\d+")
	private final String pesel;

	// -----------------------------------------------------------------------------------------------------------------
}
