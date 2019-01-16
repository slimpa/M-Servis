package DAO;

import DTO.AdminDTO;

import java.util.List;

public interface AdminDAO {

	/**
	 * 
	 * @param admin
	 */
	boolean insert(AdminDTO admin);

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