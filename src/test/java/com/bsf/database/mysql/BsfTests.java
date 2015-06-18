package com.bsf.database.mysql;

import static org.junit.Assert.*;

import org.junit.Test;

public class BsfTests {

	@Test
	public void testGetInstance() {
		assertNotNull("MySQL Instance created.", BsfDB.getInstance());
	}

	@Test
	public void testGetConnection() {
		
		final BsfDB bsfDB = BsfDB.getInstance();
		
		assertNotNull("MySQL Connection could be established.", bsfDB.getConnection());
	}

}
