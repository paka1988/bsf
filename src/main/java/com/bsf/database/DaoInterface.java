package com.bsf.database;

import com.bsf.model.User;

public interface DaoInterface {

	User getUser(final String email);
}
