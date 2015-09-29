package com.bsf.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bsf.database.DaoInterface;
import com.bsf.i18n.LanguageCode;
import com.bsf.model.AnonymousUser;
import com.bsf.model.System;
import com.bsf.model.User;

/**
 * Class which allows to access data base objects via java function.
 *
 * @author pkalashnikov
 *
 */
public class DataAccessObject implements DaoInterface {

    private static final Connection CONN = BsfDB.getInstance().getConnection();

    private void closeResultSet(final ResultSet rs) {

        try {
            rs.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closeSqlStatement(final PreparedStatement ps) {

        try {
            ps.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAnonymousUser(final AnonymousUser user) {

        final String stmtDeleteUser = "DELETE FROM anonymoususers WHERE session_id=?";

        PreparedStatement ps = null;


        try {

            ps = CONN.prepareStatement(stmtDeleteUser);
            ps.setString(1, user.getSessionId());

            ps.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
        }
    }

    @Override
    public void deleteUser(final User user) {

        final String stmtDeleteUser = "DELETE FROM users WHERE email=?";

        PreparedStatement ps = null;

        try {

            ps = CONN.prepareStatement(stmtDeleteUser);
            ps.setString(1, user.getEmail());

            ps.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
        }
    }

    @Override
    public AnonymousUser getAnonymousUser(final String sessionId) {

        final String stmtGetAnonymousUser = "SELECT * FROM anonymoususers WHERE session_id=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        AnonymousUser user = null;

        try {

            ps = CONN.prepareStatement(stmtGetAnonymousUser);
            ps.setString(1, sessionId);
            rs = ps.executeQuery();

            while (rs.next()) {

                user = new AnonymousUser(rs.getString("session_id"),
                        LanguageCode.valueOf(rs.getString("language_code")));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
            closeResultSet(rs);
        }

        return user;
    }

    @Override
    public System getSystem() {

        final String stmtSelectSystem = "Select * FROM system";

        PreparedStatement ps = null;
        ResultSet rs = null;
        System system = null;

        try {

            ps = CONN.prepareStatement(stmtSelectSystem);
            rs = ps.executeQuery();

            while (rs.next()) {

                system = new System();
                system.setSessionTimeout(rs.getLong("session_timeout"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
        }

        return system;
    }



    @Override
    public User getUser(final String email) {

        final String stmtGetUser = "SELECT * FROM users WHERE email=?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {

            ps = CONN.prepareStatement(stmtGetUser);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {

                user = new User(rs.getString("user_name"), rs.getString("email"),
                        rs.getString("password"),
                        rs.getDate("create_time"),
                        LanguageCode.valueOf(rs.getString("language_code")));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
            closeResultSet(rs);
        }

        return user;
    }

    @Override
    public void setAnonymousUser(final AnonymousUser user) {

        final String stmtInsertAnonymousUser = "INSERT INTO anonymoususers (session_id, language_code, created, valid_until) "
                + "values (?, ?, ?, ?) ON duplicate key UPDATE language_code=VALUES(language_code)";

        PreparedStatement ps = null;

        try {

            final int dateCreated = 3;
            final int dateValid = 4;

            ps = CONN.prepareStatement(stmtInsertAnonymousUser);
            ps.setString(1, user.getSessionId());
            ps.setString(2, user.getLanguageCode().name());
            ps.setLong(dateCreated, user.getCreated());
            ps.setLong(dateValid, user.getValidUntil());

            ps.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
        }

    }

    @Override
    public void setUser(final User user) {

        final String stmtInsertUser = "INSERT INTO users (user_name, email, password, create_time) VALUES (?, ?, ?, NOW()) "
                + "ON DUPLICATE KEY UPDATE user_name=VALUES(user_name), email=VALUES(email), password=VALUES(password)";

        PreparedStatement ps = null;

        try {

            final int password = 3;

            ps = CONN.prepareStatement(stmtInsertUser);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(password, user.getPassword());

            ps.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
        }
    }

    @Override
    public void updateSystem(final System system) {

        final String stmtUpdateSystem = "UPDATE system SET session_timeout=? WHERE system_id=1";

        PreparedStatement ps = null;

        try {

            ps = CONN.prepareStatement(stmtUpdateSystem);
            ps.setLong(1, system.getSessionTimeout());

            ps.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            closeSqlStatement(ps);
        }
    }
}
