package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.AdminDTO;

public interface AdminDAO {
	
	
	/**
	 * 
	 * @param admin
	 */
	public boolean insert(AdminDTO admin) ;

	/**
	 * 
	 * @param admin
	 */
	boolean update(AdminDTO admin);

	/**
	 * 
	 * @param admin
	 */
	boolean delete(AdminDTO admin);

	List<AdminDTO> selectAll();

	/**
	 * 
	 * @param admin
	 */
	List<AdminDTO> selectBy(AdminDTO admin);

}