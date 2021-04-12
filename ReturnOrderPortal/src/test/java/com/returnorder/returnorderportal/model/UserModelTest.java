package com.returnorder.returnorderportal.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.returnorder.returnorderportal.model.UserModel;

class UserModelTest {

	UserModel userModel = new UserModel("UID","Gaurav","Gaurav123");
	
	@Test
	void testGetUserId() {
		assertEquals("UID",userModel.getUserId());
		
	}

	@Test
	void testGetUsername() {
		assertEquals("Gaurav",userModel.getUsername());
	}

	@Test
	void testGetPassword() {
		assertEquals("Gaurav123",userModel.getPassword());
	}

	@Test
	void testSetUserId() {
		userModel.setUserId("UOP");
		assertEquals("UOP",userModel.getUserId());
	}

	@Test
	void testSetUsername() {
		userModel.setUsername("Darsh");
		assertEquals("Darsh",userModel.getUsername());
	}

	@Test
	void testSetPassword() {
		userModel.setPassword("Darsh123");
		assertEquals("Darsh123",userModel.getPassword());
	}

	@Test
	void testToString() {
		String s = "UserModel(userId=" + "UID" + ", username=" + "Gaurav" + ", password=" + "Gaurav123" + ")";
		assertEquals(s, userModel.toString());
	}

	@Test
	void testUserModel() {
		UserModel user = new UserModel();
		assertNotNull(user);
	}

	@Test
	void testUserModelStringStringString() {
		UserModel user = new UserModel("UIC","Shrey","Shrey123");
		assertNotNull(user);
	}

}
