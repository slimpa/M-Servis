package DBU;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

	private String jdbcURL;
	private String username;
	private String password;

	/**
	 * 
	 * @param c
	 * @param sql
	 * @param retGenKeys
	 * @param values
	 */
	public PreparedStatement prepareStatement(Connection c, String sql, boolean retGenKeys, Object values) {
		// TODO - implement DBUtil.prepareStatement
		throw new UnsupportedOperationException();
	}

	public Connection getConnection() {
		// TODO - implement DBUtil.getConnection
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param connection
	 */
	public void close(Connection connection) {
		// TODO - implement DBUtil.close
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param statement
	 */
	public void close(Statement statement) {
		// TODO - implement DBUtil.close
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param rs
	 */
	public void close(ResultSet rs) {
		// TODO - implement DBUtil.close
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param rs
	 * @param statement
	 * @param connection
	 */
	public void close(ResultSet rs, Statement statement, Connection connection) {
		// TODO - implement DBUtil.close
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param statement
	 * @param connection
	 */
	public void close(Statement statement, Connection connection) {
		// TODO - implement DBUtil.close
		throw new UnsupportedOperationException();
	}

}