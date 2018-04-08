package com.leedemo.user.controller;

import com.leedemo.user.model.User;
import com.leedemo.user.service.IUserService;
import com.leedemo.user.util.CustomErrorType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class RestApiController 
{
	public static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		LOGGER.info("Fetching User with id {}", id);
		
        return performOperation(id, (userCurrent)-> {
            return new ResponseEntity<User>(userCurrent, HttpStatus.OK);
        }, "Get");
	}


	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating User : {}", user);

		if (userService.findByName(user.getName()) != null) 
		{
			LOGGER.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name '" + 
			user.getName() + "' already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		LOGGER.info("Updating User with id {}", id);

		return performOperation(id, (userCurrent)-> {
		    userCurrent.setName(user.getName());
		    userCurrent.setEmail(user.getEmail());		    
	        userService.updateUser(userCurrent);
	        return new ResponseEntity<User>(userCurrent, HttpStatus.OK);
	    }, "Update");
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		LOGGER.info("Fetching & Deleting User with id {}", id);

		return performOperation(id, (userCurrent)-> {
            userService.deleteUserById(id);             
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }, "Delete");		
	}
	
	interface OperationLamda {
	    ResponseEntity<?> invoke(User user);
	}
	
	private ResponseEntity<?> performOperation(Long id, OperationLamda operation, String operationName)
	{
        User currentUser = userService.findById(id);

        if (currentUser == null)
        {
            String errorMsg = String.format("Unable find user with id {%s}.", id); 
            LOGGER.error(errorMsg);
            return new ResponseEntity(new CustomErrorType(errorMsg), HttpStatus.NOT_FOUND);
        }
        try
        {
            return operation.invoke(currentUser);
        }
        catch (Exception exception)
        {
            String errorMsg = String.format("Exception while performing [%s] operation  : %s", operationName, exception.getMessage());
            LOGGER.error(errorMsg);
            return new ResponseEntity( new CustomErrorType(errorMsg), HttpStatus.INTERNAL_SERVER_ERROR);            
        }
	}
}