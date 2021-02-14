package com.ett.technical;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ett.technical.model.Account;
import com.ett.technical.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TechnicalTestApplicationTests 
{
    @LocalServerPort
    int randomServerPort;
     
     
    @SuppressWarnings("deprecation")
	@Test
    public void testAddUserMissingHeader() throws URISyntaxException 
    {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080"+"/api/user/create";
        URI uri = new URI(baseUrl);
        Set<Account> acc = new HashSet<Account>();
        User user = new User("mastouri", "Adam", "sfax", "123564",acc);
         
        HttpHeaders headers = new HttpHeaders();
     
        HttpEntity<User> request = new HttpEntity<>(user, headers);
         
        try
        {
        	ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            //Assert.fail();
            assertEquals(201, result.getStatusCodeValue());
        }
        catch(HttpClientErrorException ex) 
        {
            //Verify bad request and missing header
            Assert.assertEquals(400, ex.getRawStatusCode());
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
        }
    }
    
    @SuppressWarnings("deprecation")
 	@Test
     public void testGetUserListSuccess() throws URISyntaxException 
     {
         RestTemplate restTemplate = new RestTemplate();
          
         final String baseUrl = "http://localhost:8080"  + "/api/user/";
         URI uri = new URI(baseUrl);
      
         ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
         assertEquals(200, result.getStatusCodeValue());
     }
    @SuppressWarnings("deprecation")
	@Test
    public void testAddAccountMissingHeader() throws URISyntaxException 
    {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080"+"/api/account/create";
        URI uri = new URI(baseUrl);
        LocalDate creationDate = LocalDate.now();
        Account acc = new Account(creationDate,0,0);
         
        HttpHeaders headers = new HttpHeaders();
     
        HttpEntity<Account> request = new HttpEntity<>(acc, headers);
         
        try
        {
        	ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            //Assert.fail();
            assertEquals(201, result.getStatusCodeValue());
        }
        catch(HttpClientErrorException ex) 
        {
            //Verify bad request and missing header
            Assert.assertEquals(400, ex.getRawStatusCode());
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
        }
    }
    @SuppressWarnings("deprecation")
   	@Test
       public void testGetAccountListSuccess() throws URISyntaxException 
       {
           RestTemplate restTemplate = new RestTemplate();
            
           final String baseUrl = "http://localhost:8080"  + "/api/account/";
           URI uri = new URI(baseUrl);
        
           ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

           assertEquals(200, result.getStatusCodeValue());

       }
}
