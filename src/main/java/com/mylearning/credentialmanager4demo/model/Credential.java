package com.mylearning.credentialmanager4demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "credential")
public class Credential {
	@Id
	@GeneratedValue
	// @CsvBindByName
	private int id;

	// @CsvBindByName
	private String username;

	private String password;

	// @CsvBindByName(column = "password")
	@Transient
	private String encryptedPassword;

	@ManyToOne(optional = false)
	@JoinColumn(name = "container", referencedColumnName = "id")
	private Container container;

	public Credential() {

	}

	public Credential(String username, Container container, String password) {
		super();
		this.username = username;
		this.container = container;
		this.password = password;
	}

	public Credential(int id, String username, Container container, String password) {
		super();
		this.id = id;
		this.username = username;
		this.container = container;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the encryptedPassword
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * @param encryptedPassword the encryptedPassword to set
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
		this.setPassword(encryptedPassword.toUpperCase());
	}

	@Override
	public String toString() {
		return String.format("Credential [id=%s, username=%s, password=%s, container=%s]", id, username, password,
				container);
	}

}
