package com.codigo.aplios.envelop.system.core.domain;

import com.codigo.aplios.envelop.system.core.identity.PeselIdentity;

public class PeselData {
	
	private PeselIdentity pesel;
	
	public void setPesel(PeselIdentity pesel)
	{
		this.pesel = pesel;
	}
	
	public PeselIdentity getPesel()
	{
		return this.pesel;
	}
}
