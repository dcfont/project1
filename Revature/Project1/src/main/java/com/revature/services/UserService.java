package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAOImpl();
	}

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public User validateCredentials(String username, String password) {
		User userFromDB = this.userDAO.getUserUsername(username);

		if (userFromDB == null)
			return null;

		if (!password.equals(userFromDB.getPassword()))
			return null;

		return userFromDB;
	}

	public Boolean createEmployee(User user) {
		User usernameFromDb = userDAO.getUserUsername(user.getUsername());
		User useremailFromDb = userDAO.getUserEmail(user.getEmail());
		if (usernameFromDb != null) {
			return Boolean.FALSE;
		}
		if (useremailFromDb != null) {
			return Boolean.FALSE;
		}
		this.userDAO.createEmployee(user);
		return Boolean.TRUE;
	}

	public Boolean createManager(User user) {
		User usernameFromDb = userDAO.getUserUsername(user.getUsername());
		User useremailFromDb = userDAO.getUserEmail(user.getEmail());

		if (usernameFromDb != null) {
			return Boolean.FALSE;
		}
		if (useremailFromDb!= null) {
			return Boolean.FALSE;
		}

		this.userDAO.createManager(user);
		return Boolean.TRUE;

	}

}

