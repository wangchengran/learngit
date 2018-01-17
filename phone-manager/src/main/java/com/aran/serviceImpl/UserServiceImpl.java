package com.aran.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.dao.UserDAO;
import com.aran.entity.User;
import com.aran.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User checkUser(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public int addUser(User user) {
		return userDAO.add(user);
	}

	
}
