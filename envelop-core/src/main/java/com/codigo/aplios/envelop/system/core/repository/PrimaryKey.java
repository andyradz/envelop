package com.codigo.aplios.envelop.system.core.repository;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PrimaryKey implements Serializable {

	private static final long serialVersionUID = -1528223225651711166L;
	private String mName;
	private Integer mId;

	// -----------------------------------------------------------------------------------------------------------------

	public PrimaryKey() {

	}

	// -----------------------------------------------------------------------------------------------------------------

	public String getName() {

		return this.mName;
	}

	// -----------------------------------------------------------------------------------------------------------------

	public Integer getId() {

		return this.mId;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public int hashCode() {

		return this.mName.hashCode() + this.mId;
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean equals(Object instance) {

		if (this == instance)
			return true;
		if ((Objects.isNull(instance)) || !(instance instanceof PrimaryKey))
			return false;

		final PrimaryKey myInstance = PrimaryKey.class.cast(instance);

		return (this.mId == myInstance.mId || this.mName.equals(myInstance.mName));
	}

	// -----------------------------------------------------------------------------------------------------------------
}
