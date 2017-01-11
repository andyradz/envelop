package com.codigo.aplios.envelop.system.core.domain;

import java.io.Serializable;

import javax.persistence.Version;

public abstract class Domain<PK extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Version
	// @Column(name = "VERSION", nullable = false)
	private Long version;

	public Domain(PK id, Long version) {
		this.version = version;
	}

	public Domain() {
	}

	public abstract PK getId();

	public abstract void setId(PK id);

	public Long getVersion() {

		return version;
	}

	public void setVersion(Long version) {

		this.version = version;
	}

	@Override
	public String toString() {

		return "Bean [id="
				+ getId()
				+ ", version="
				+ version
				+ "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((getId() == null) ? 0 : getId().hashCode());
		result = prime
				* result
				+ ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domain<PK> other = Domain.class.cast(obj);
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
