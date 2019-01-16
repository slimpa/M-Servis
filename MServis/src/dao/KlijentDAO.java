package DAO;

import DTO.KlijentDTO;

import java.util.List;

public interface KlijentDAO {

	/**
	 * 
	 * @param klijent
	 */
	boolean insert(KlijentDTO klijent);

	/**
	 * 
	 * @param klijent
	 */
	boolean update(KlijentDTO klijent);

	/**
	 * 
	 * @param klijent
	 */
	boolean delete(KlijentDTO klijent);

	List<KlijentDTO> selectAll();

	/**
	 * 
	 * @param klijent
	 */
	List<KlijentDTO> selectBy(KlijentDTO klijent);

}