package MySQL;

import dto.ZaposleniDTO;

import java.util.List;

import dao.ZaposleniDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLZaposleniDAO implements ZaposleniDAO {

	/**
	 * 
	 * @param zaposleni
	 */
	public boolean insert(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposleni
	 */
	public boolean update(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposleni
	 */
	public boolean delete(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<ZaposleniDTO> selectAll() {
		// TODO - implement MySQLZaposleniDAO.selectAll
		throw new UnsupportedOperationException();
	}


	/**
	 * 
	 * @param zaposleni
	 */
	public List<ZaposleniDTO> selectBy(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.selectBy
		throw new UnsupportedOperationException();
	}

}