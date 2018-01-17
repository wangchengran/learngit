package com.aran.service;

import com.aran.entity.User;

public interface UserService {

	User checkUser(String username);

	int addUser(User user);

}
