package DAO;

import DTO.CjenovnikUslugaDTO;

import java.util.List;

public interface CjenovnikUslugaDAO {

	/**
	 * 
	 * @param cjenovnikUsluga
	 */
	boolean insert(CjenovnikUslugaDTO cjenovnikUsluga);

	/**
	 * 
	 * @param cjenovnikUsluga
	 */
	boolean update(CjenovnikUslugaDTO cjenovnikUsluga);

	/**
	 * 
	 * @param cjenovnikUsluga
	 */
	boolean delete(CjenovnikUslugaDTO cjenovnikUsluga);

	List<CjenovnikUslugaDTO> selectAll();

	/**
	 * 
	 * @param cjenovnikUsluga
	 */
	List<CjenovnikUslugaDTO> selectBy(CjenovnikUslugaDTO cjenovnikUsluga);

}