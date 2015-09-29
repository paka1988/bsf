package com.bsf.database.mysql;

import org.junit.Test;

import com.bsf.database.DaoInterface;
import com.bsf.i18n.LanguageCode;
import com.bsf.model.AnonymousUser;
import com.bsf.model.User;

import static org.junit.Assert.*;

public class DataAccessObjectTest {

    @Test
    public void setGetDeleteUserTest() {

        DaoInterface dao = new DataAccessObject();

        User pkalashnikov = new User("Pavlo Kalashnikov",
                "p.kalashnikov@epages.com",
                "$2a$08$alpinxEVDC8v7yjo.JTCuuPIA7lkpHuiyeyjm1UjKbcDgq14eUHPG",
                null,
                null);

        // set user
        dao.setUser(pkalashnikov);

        // get user
        pkalashnikov = null;
        pkalashnikov = dao.getUser("p.kalashnikov@epages.com");

        assertNotNull("User is not initialized.", pkalashnikov);
        assertEquals("Pavlo Kalashnikov", pkalashnikov.getUserName());

        // delete user
        dao.deleteUser(pkalashnikov);
        pkalashnikov = dao.getUser("p.kalashnikov@epages.com");
        assertNull("User should be null.", pkalashnikov);
    }

    @Test
    public void setGetDeleteAnonymousUserTest() {

        DaoInterface dao = new DataAccessObject();

        AnonymousUser user = new AnonymousUser("SomeSessionId", LanguageCode.en_GB);

        // set
        dao.setAnonymousUser(user);

        // get
        user = null;
        user = dao.getAnonymousUser("SomeSessionId");

        assertNotNull("User is not initialized.", user);
        assertEquals("SomeSessionId", user.getSessionId());

        // delete
        dao.deleteAnonymousUser(user);
        user = dao.getAnonymousUser("SomeSessionId");

        assertNull("User wasn't deleted", user);

    }
}
