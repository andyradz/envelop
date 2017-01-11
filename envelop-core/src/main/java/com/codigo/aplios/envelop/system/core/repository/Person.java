package com.codigo.aplios.envelop.system.core.repository;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
public class Person {
	private PrimaryKey mPrimatyKey;

	public void setPrimaryKey(PrimaryKey primaryKey) {

		// ...begin method
		this.mPrimatyKey = primaryKey;
	}

	@EmbeddedId
	public PrimaryKey getPrimaryKey() {

		// ...begin method
		return this.mPrimatyKey;
	}
}

@IdClass(PrimaryKey.class)
@Entity
class Employee {
	@Id
	String empName;
	@Id
	LocalDate birthDay;
}

// ..table generator id
@Entity
@Table(name = "EJB_ADDRESS")
class Address implements Serializable {
	@TableGenerator(name = "ADDRESS_TABLE_GENERATOR", table = "ADDRESS_GENERATOR_TABLE", pkColumnValue = "ADDRESS_SEQ")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ADDRESS_TABLE_GENERATOR")
	@Column(name = "ADDRESS_ID")
	public Integer getId() {

		return 12;
	}
}

// ..sequence generator
@Entity
@Table(name = "EJB_ADDRESS")
class Address1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "ADDRESS_SEQUENCE_GENERATOR", sequenceName = "ADDRESS_SEQ")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQUENCE_GENERATOR")
	@Column(name = "ADDRESS_ID")
	public Integer getId() {

		return 12;
	}
}

// ...identity generator
@Entity
@Table(name = "EJB_ADDRESS")
class Address2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -131959213600790831L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {

		return 12;
	}

	// @Serialized(fetch)
	@Column(name = "PICTURE")
	public Byte[] getPicture() {

		return null;
	}
}
