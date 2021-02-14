package com.ett.technical.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ett.technical.model.Account;
import com.ett.technical.model.User;
import com.ett.technical.service.AccountService;
import com.ett.technical.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	AccountService accountService;
	@GetMapping("/")
    public ResponseEntity<List<Account>> findall(){
        List<Account> list = accountService.getAllAccounts();
        HttpHeaders h = new HttpHeaders();
        return  ResponseEntity.ok().headers(h).body(list);
    }
	 @PostMapping("/create")
    public ResponseEntity <Account> save(@RequestBody Account c) throws ParseException {
		 /*Date date=new SimpleDateFormat("dd-MM-yyyy").parse(c.getCreationDate().toString());
		 c.setCreationDate(date);*/
		 LocalDate localDate= LocalDate.parse(c.getCreationDate().toString());
	        c.setCreationDate(localDate);
        Account ac = accountService.addAccount(c);
        if (ac == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.created(null).body(ac);
    }
	 @PutMapping("/update/{index}")
	    public ResponseEntity<Account> update(@RequestBody Account c,@PathVariable int index) throws ParseException {

	        Account user = accountService.updateAccount(c,index);
	        if (user == null)
	            return ResponseEntity.noContent().build();
	        return ResponseEntity.created(null).body(user);
	    }
	 @PutMapping("/update/debit/{index}")
	    public ResponseEntity<Account> withdrawAccount(@RequestBody int value,@PathVariable int index) throws ParseException {

	        Account user = accountService.withdrawAccount(index,value);
	        if (user == null)
	            return ResponseEntity.noContent().build();
	        return ResponseEntity.created(null).body(user);
	    }
	 @PutMapping("/update/credit/{index}")
	    public ResponseEntity<Account> depositAccount(@RequestBody int value,@PathVariable int index) throws ParseException {

	        Account user = accountService.depositAccount(index,value);
	        if (user == null)
	            return ResponseEntity.noContent().build();
	        return ResponseEntity.created(null).body(user);
	    }
	 @DeleteMapping("/{id}")
		public ResponseEntity<String> deleteAccount(@PathVariable int id) {
			ResponseEntity<String> Resultat;
			HttpHeaders header = new HttpHeaders();
			try {
				accountService.delete(id);
				Resultat = ResponseEntity.ok().headers(header)
						.build();
			}catch(Exception e){
				return ResponseEntity.notFound().headers(header)
						.build();
			}
			return Resultat;
		}
	 
	 @GetMapping("/balance/{id}")
		public ResponseEntity<Double> getBalanceByUserIndex(@PathVariable(value = "id") int id) throws URISyntaxException{
			 double s = accountService.getBalanceByUser(id);
			 HttpHeaders header = new HttpHeaders();
			return ResponseEntity
			.created(new URI("/api/account/" + id))
			.headers(header).body(s);
		}
}
