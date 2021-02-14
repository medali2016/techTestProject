package com.ett.technical.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ett.technical.model.Account;
import com.ett.technical.model.User;
import com.ett.technical.model.UserAccount;
@Service
public class UserService {
	@Autowired
	AccountService accountSerice;
	public static ArrayList<User> userList = new ArrayList<User>();
	public User addUser (User user) {
		userList.add(user);
		return user;
	}
	public ArrayList<User> delete(int index) {
		if (index<userList.size()) {
			userList.remove(index);
		}
		
		return userList;
	}
	public User getByIndex(int index) {
		if (index<userList.size()) {
			return userList.get(index);
		}
		
		return null;
	}
	public ArrayList<User> updateUser (User user,int index) {
		if (index<userList.size()) {
			userList.remove(index);
			userList.add(user);
		}	
		return userList;
	}
	public ArrayList<User> getAllUsers () {
		return userList;
	}

	public void addAccountToUser (int userIndex,int accountIndex) {
		if (userIndex<userList.size() && accountIndex<accountSerice.accountList.size()) {
			User user = userList.get(userIndex);
			Set<Account> setAcount = user.getAccounts();
			setAcount.add(accountSerice.accountList.get(accountIndex));
			user.setAccounts(setAcount);
		}
	
	}
	public User addAccountToUser (Account c,int userIndex) {
		User user = null;
		if (userIndex<userList.size() ) {
			user = userList.get(userIndex);
			Set<Account> setAcount = user.getAccounts();
			setAcount.add(c);
			user.setAccounts(setAcount);
		}
		return user;
	
	}
	
}

