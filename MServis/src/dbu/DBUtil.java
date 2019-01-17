package dbu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil{
	
	private static DBUtil instance;

	public static DBUtil getInstance() {
		if (instance == null)
			instance = new DBUtil();
		return instance;
	}

	private DBUtil() {
	}

	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(Connection conn, Statement s) {
		close(s);
		close(conn);
	}

	public void close(Connection conn, ResultSet rs) {
		close(rs);
		close(conn);
	}

	public void close(Statement s, ResultSet rs) {
		close(rs);
		close(s);
	}

	public void close(Connection conn, Statement s, ResultSet rs) {
		close(rs);
		close(s);
		close(conn);
	}
}


