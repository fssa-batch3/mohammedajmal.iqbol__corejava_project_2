package com.fssa.freshstocks.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import com.fssa.freshstocks.dao.exception.DAOException;
import com.fssa.freshstocks.model.*;

public class TestLogin {

	public static void main(String[] args) {

		User user1 = new User("hacker4@gmail.com","Kotie@123");
		UserService userService = new UserService();

		try {
			userService.loginUser(user1);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void TestValidUser() throws DAOException {
		User user1 = new User("hacker4@gmail.com","Kotie@123");
		try {
			assertTrue(UserService.loginUser(user1));
		} catch (com.fssa.freshstocks.services.exception.ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void TestInValidUser() throws com.fssa.freshstocks.services.exception.ServiceException, DAOException {
		User user1 = new User("hacker@gmail.com","Hacker@123");
		assertFalse(UserService.loginUser(user1));
	}
	
}