package dao;

import java.util.List;

import dto.KlijentDTO;

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