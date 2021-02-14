package com.ett.technical.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ett.technical.model.Account;
import com.ett.technical.model.User;
@Service
public class AccountService {
	public static ArrayList<Account> accountList = new ArrayList<Account>();
	@Autowired
	UserService userSerice;
	public Account addAccount (Account account) {
		accountList.add(account);
		return account;
	}
	public ArrayList<Account> delete(int index) {
		if (index<accountList.size()) {
			accountList.remove(index);
		}	
		return accountList;
	}
	public Account updateAccount (Account account,int index) {
		accountList.add(account);
		return account;
	}
	public Account getByIndex(int index) {
		if (index<accountList.size()) {
			return accountList.get(index);
		}
		
		return null;
	}
	public ArrayList<Account> getAllAccounts () {
		return accountList;
	}
	public Account withdrawAccount (int  index,double value) {

		Account acc = null;
		if (index<accountList.size() ) {
			acc = accountList.get(index);
			if (acc.getCredit()>=value) {				
				acc.setDebit(acc.getDebit()+value);
				acc.setCredit(acc.getCredit()-value);;	
			} 
		}	
		return acc;
	}
	public Account depositAccount (int  index,double value) {
		Account acc = null;
		if (index<accountList.size()) {
		    acc = accountList.get(index);
			acc.setCredit(acc.getCredit()+value);	
		}	
		return acc;
	}
	public Set<Account> getAccountByUser(int userIndex){
		if (userIndex<userSerice.userList.size()) {
			return userSerice.userList.get(userIndex).getAccounts();
		}
		return null;
	}
	public double getBalanceByUser(int userIndex){
		double balance= 0;
		if (userIndex<userSerice.userList.size()) {
			Set<Account> set = userSerice.userList.get(userIndex).getAccounts();
			 for (Account acc : set) { 
				 balance  = balance + (acc.getCredit()-acc.getDebit()) ;
			 }
		}
		return balance;
	}
}
