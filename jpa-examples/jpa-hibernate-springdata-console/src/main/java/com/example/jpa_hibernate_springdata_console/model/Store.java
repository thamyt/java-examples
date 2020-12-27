package com.example.jpa_hibernate_springdata_console.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.jpa_hibernate_springdata_console.model.base.BaseModel;


/**
 * The persistent class for the store database table.
 * 
 */
//SELECT DISTINCT s FROM Store s JOIN FETCH s.address JOIN FETCH s.manager m JOIN FETCH m.address
/*
@NamedQuery(name="Store.findAll",
            query="SELECT s FROM Store s "
            		+ "INNER JOIN FETCH s.address "
            		+ "INNER JOIN FETCH s.manager "
            		+ "INNER JOIN FETCH s.manager.address")
 */
@Entity
@Table(name="store")
@NamedQuery(name="Store.findAll",
            query="SELECT s FROM Store s JOIN FETCH s.address JOIN FETCH s.manager mgr JOIN FETCH mgr.address")
public class Store extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_id", unique=true, nullable=false)
	private byte storeId;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="store", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Staff> staffs = new ArrayList<Staff>();

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="address_id", nullable=false)
	private Address address;

	//bi-directional many-to-one association to Staff
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="manager_staff_id", nullable=false)
	private Staff manager;

	public Store() {
	}

	public byte getStoreId() {
		return this.storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

	public List<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public Staff addStaff(Staff staff) {
		getStaffs().add(staff);
		staff.setStore(this);

		return staff;
	}

	public Staff removeStaff(Staff staff) {
		getStaffs().remove(staff);
		staff.setStore(null);

		return staff;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Staff getManager() {
		return this.manager;
	}

	public void setManager(Staff staff) {
		this.manager = staff;
	}

}