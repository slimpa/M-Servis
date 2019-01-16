package DAO;

import DTO.RacunDTO;

import java.util.List;

public interface RacunDAO {

	/**
	 * 
	 * @param racun
	 */
	boolean insert(RacunDTO racun);

	/**
	 * 
	 * @param racun
	 */
	boolean update(RacunDTO racun);

	/**
	 * 
	 * @param racun
	 */
	boolean delete(RacunDTO racun);

	List<RacunDTO> selectAll();

	/**
	 * 
	 * @param racun
	 */
	List<RacunDTO> selectBy(RacunDTO racun);

}