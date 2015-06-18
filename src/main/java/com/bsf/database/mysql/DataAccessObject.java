package com.bsf.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bsf.database.DaoInterface;
import com.bsf.model.User;

public class DataAccessObject implements DaoInterface{

	private static final Connection conn = BsfDB.getInstance().getConnection();
	
	@Override
	public User getUser(final String email) {

		final String stmtGetUser = "SELECT * FROM users WHERE email=?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			
			ps = conn.prepareStatement(stmtGetUser);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = new User(rs.getInt("user_id"),
								rs.getString("user_name"),
								rs.getString("email"),
								rs.getString("password"),
								rs.getDate("create_time"));
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

	private void closeSqlStatement(final PreparedStatement ps) {
		
		try {
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void closeResultSet(final ResultSet rs) {
		
		try {
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
