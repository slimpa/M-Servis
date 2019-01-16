package DAO;

import DTO.StanjeTelefonaDTO;

import java.util.List;

public interface StanjeTelefonaDAO {

	/**
	 * 
	 * @param stanjeTelefona
	 */
	boolean insert(StanjeTelefonaDTO stanjeTelefona);

	/**
	 * 
	 * @param stanjeTelefona
	 */
	boolean update(StanjeTelefonaDTO stanjeTelefona);

	/**
	 * 
	 * @param stanjeTelefona
	 */
	boolean delete(StanjeTelefonaDTO stanjeTelefona);

	List<StanjeTelefonaDTO> selectAll();

	/**
	 * 
	 * @param stanjeTelefona
	 */
	List<StanjeTelefonaDTO> selectBy(StanjeTelefonaDTO stanjeTelefona);

}