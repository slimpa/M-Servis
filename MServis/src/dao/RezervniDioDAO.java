package dao;

import java.util.List;

import dto.RezervniDioDTO;

public interface RezervniDioDAO {

	/**
	 *
	 * @param rezervniDio
	 */
	boolean insert(RezervniDioDTO rezervniDio);

	/**
	 * 
	 * @param rezervniDio
	 */
	boolean update(RezervniDioDTO rezervniDio);

	/**
	 * 
	 * @param rezervniDio
	 */
	boolean delete(RezervniDioDTO rezervniDio);

	List<RezervniDioDTO> selectAll();

	/**
	 * 
	 * @param rezervniDio
	 */
	List<RezervniDioDTO> selectBy(RezervniDioDTO rezervniDio);



}