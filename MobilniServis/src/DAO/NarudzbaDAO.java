package DAO;

import DTO.NarudzbaDTO;

import java.util.List;

public interface NarudzbaDAO {

	/**
	 * 
	 * @param narudzba
	 */
	boolean insert(NarudzbaDTO narudzba);

	/**
	 * 
	 * @param narudzba
	 */
	boolean update(NarudzbaDTO narudzba);

	/**
	 * 
	 * @param narudzba
	 */
	boolean delete(NarudzbaDTO narudzba);

	List<NarudzbaDTO> selectAll();

	/**
	 * 
	 * @param narudzba
	 */
	List<NarudzbaDTO> selectBy(NarudzbaDTO narudzba);

}