package com.bsf.model;

import java.sql.Date;

/**
 * Corresponsd to table user.
 *
 * @author pkalashnikov
 *
 */
public class User {

    private int userId;
    private String userName;
    private String email;
    private String password;
    private Date creationTime;

    /**
     *
     * @param userId
     *            id.
     * @param userName
     *            name.
     * @param email
     *            email.
     * @param password
     *            password.
     * @param creationTime
     *            date time.
     */
    public User(final int userId, final String userName, final String email, final String password, final Date creationTime) {

        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.creationTime = creationTime;
    }

    /**
     *
     * @return user id.
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return creation date.
     */
    public Date getCreationTime() {
        return creationTime;
    }
}
