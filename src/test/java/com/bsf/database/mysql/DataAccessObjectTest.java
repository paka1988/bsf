package com.bsf.database.mysql;

import org.junit.Test;

import com.bsf.database.DaoInterface;
import com.bsf.model.User;

import static org.junit.Assert.*;

public class DataAccessObjectTest {

	@Test
	public void getUserTest() {
		
		DaoInterface dao = new DataAccessObject();
		User pkalashnikov = dao.getUser("p.kalashnikov@epages.com");
		
		assertNotNull("User is not initialized.", pkalashnikov);
		assertEquals("Pavlo Kalashnikov", pkalashnikov.getUserName());
	}
}
