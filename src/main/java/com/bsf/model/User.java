package com.bsf.model;

import java.sql.Date;

import com.bsf.i18n.LanguageCode;

/**
 * Corresponsd to table user.
 *
 * @author pkalashnikov
 *
 */
public class User extends AbstractUser {

    private String userName;
    private String email;
    private String password;
    private Date creationTime;
    private LanguageCode lc;

    /**
     * @param userName
     *            name.
     * @param email
     *            email.
     * @param password
     *            password.
     * @param creationTime
     *            date time.
     * @param lc
     *            language code.
     */
    public User(final String userName, final String email,
            final String password, final Date creationTime, final LanguageCode lc) {

        this.userName = userName;
        this.email = email;
        this.password = password;
        this.creationTime = creationTime;
        this.lc = lc;
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

    @Override
    public LanguageCode getLanguageCode() {

        return lc;
    }
}
