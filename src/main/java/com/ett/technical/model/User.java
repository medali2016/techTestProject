package com.ett.technical.model;

import java.util.ArrayList;
import java.util.Set;

public class User {
	private String lastName;
	private String firstName;
	private String address;
	private String phone;
    private Set<Account> accounts;

public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String lastName, String firstName, String address, String phone,Set<Account> accounts) {
	super();
	this.lastName = lastName;
	this.firstName = firstName;
	this.address = address;
	this.phone = phone;
	this.accounts = accounts;
}

public Set<Account> getAccounts() {
	return accounts;
}
public void setAccounts(Set<Account> accounts) {
	this.accounts = accounts;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

}
