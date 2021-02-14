package com.ett.technical.model;

import java.time.LocalDate;
import java.util.Date;

public class Account {
	private LocalDate creationDate;
	private double credit;
	private double debit;
public Account() {
	super();
	// TODO Auto-generated constructor stub
}

public Account(LocalDate creationDate, double credit, double debit) {
	super();
	this.creationDate = creationDate;
	this.credit = credit;
	this.debit = debit;
}

public LocalDate getCreationDate() {
	return creationDate;
}
public void setCreationDate(LocalDate creationDate) {
	this.creationDate = creationDate;
}
public double getCredit() {
	return credit;
}
public void setCredit(double credit) {
	this.credit = credit;
}
public double getDebit() {
	return debit;
}
public void setDebit(double debit) {
	this.debit = debit;
}


}
