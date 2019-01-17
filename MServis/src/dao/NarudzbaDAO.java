package dao;

import java.util.List;

import dto.NarudzbaDTO;

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