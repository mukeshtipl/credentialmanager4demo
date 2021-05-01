package com.mylearning.credentialmanager4demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "container")
public class Container {
	@Id
	@GeneratedValue
	private int id;
	private String name;

	// @ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "owner", referencedColumnName = "id")
	private User owner;

	public Container() {

	}

	public Container(String name, User owner) {
		super();
		this.name = name;
		this.owner = owner;
	}

	public Container(int id, String name, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return String.format("Container [id=%s, name=%s, owner=%s]", id, name, owner);
	}

}
