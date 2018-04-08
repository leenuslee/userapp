package com.leedemo.user.service;

import java.util.List;

import com.leedemo.user.model.User;
import com.leedemo.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService implements IUserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Long id)
	{
		return userRepository.findOne(id);
	}

    @Override
	public User findByName(String name)
	{
        return userRepository.findByName(name);
    }

    @Override
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}

    @Override
	public void updateUser(User user)
	{
		saveUser(user);
	}

    @Override
	public void deleteUserById(Long id)
	{
		userRepository.delete(id);
	}

    @Override
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}
}
