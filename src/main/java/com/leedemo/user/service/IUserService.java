package com.leedemo.user.service;


import com.leedemo.user.model.User;

import java.util.List;

//TODO identify the exceptions that may be thrown by JpaRepository and add some proper error handling logic and throws clauses
public interface IUserService
{	
    /**
     * @param id user id; cannot be null;
     * @return an instance of {@code User} that corresponds to the given user id; null if not found
     */
	User findById(Long id);

	/**
     * @param name user name; cannot be null;
     * @return an instance of {@code User} that corresponds to the given user name; null if not found
     */
    User findByName(String name);

    /**
     * @param user an instance of {@code User}; cannot be null; User.id field will be ignored as it will be auto generated.
     * @return an instance of {@code User} that will now have the id field set with the auto generated value.
     */
    User saveUser(User user);
	
    /**
     * @param user an instance of {@code User}; cannot be null;  
     */
    void updateUser(User user);

    /**
     * @param id user id; cannot be null;
     */
    void deleteUserById(Long id);

    /**
     * @return a list of all the users in the system
     */	
    List<User> findAllUsers();
}