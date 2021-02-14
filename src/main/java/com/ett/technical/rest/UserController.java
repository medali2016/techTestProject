package com.ett.technical.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ett.technical.model.Account;
import com.ett.technical.model.User;
import com.ett.technical.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	public RequestMappingHandlerMapping requestMappingHandlerMapping;
	@GetMapping("")
    public ResponseEntity<List<User>> findall(){
        List<User> list = userService.getAllUsers();
        HttpHeaders h = new HttpHeaders();
        return  ResponseEntity.ok().headers(h).body(list);
    }
	 @PostMapping("/create")
    public ResponseEntity <User> save(@RequestBody User c) throws ParseException {

        User user = userService.addUser(c);
        if (user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.created(null).body(user);
    }
	 @PutMapping("/update/{index}")
	    public ResponseEntity <ArrayList<User> > update(@RequestBody User c,@PathVariable int index) throws ParseException {

	        ArrayList<User> user = userService.updateUser(c,index);
	        if (user == null)
	            return ResponseEntity.noContent().build();
	        return ResponseEntity.created(null).body(user);
	    }
	 @DeleteMapping("/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable int id) {
			ResponseEntity<String> Resultat;
			HttpHeaders header = new HttpHeaders();
			try {
				userService.delete(id);
				Resultat = ResponseEntity.ok().headers(header)
						.build();
			}catch(Exception e){
				return ResponseEntity.notFound().headers(header)
						.build();
			}
			return Resultat;
		}
	 @GetMapping("/{id}")
	public ResponseEntity<User> getuserByIndex(@PathVariable(value = "id") int id) throws URISyntaxException{
		 User s = userService.getByIndex(id);
		 HttpHeaders header = new HttpHeaders();
		return ResponseEntity
		.created(new URI("/api/user/" + id))
		.headers(header).body(s);
	}
	 @PutMapping("/addacount/touser/{index}")
	    public ResponseEntity <User > addAccountToUser(@RequestBody Account c,@PathVariable(value = "index") int index) throws ParseException {

	        User user = userService.addAccountToUser(c,index);
	        if (user == null)
	            return ResponseEntity.noContent().build();
	        return ResponseEntity.created(null).body(user);
	    }
	 @RequestMapping("/endpoints")
		public @ResponseBody
		Object showEndpointsAction() throws SQLException
		{
		        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream().map(t ->
		               (t.getMethodsCondition().getMethods().size() == 0 ? "GET" : t.getMethodsCondition().getMethods().toArray()[0]) + " " +                    
		               t.getPatternsCondition().getPatterns().toArray()[0]
		        ).toArray();
		 }
}
