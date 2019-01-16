package DAO;

import DTO.RacunHasArtikalDTO;

import java.util.List;

public interface RacunHasArtikalDAO {

	/**
	 * 
	 * @param racunArtikal
	 */
	boolean insert(RacunHasArtikalDTO racunArtikal);

	/**
	 * 
	 * @param racunArtikal
	 */
	boolean update(RacunHasArtikalDTO racunArtikal);

	/**
	 * 
	 * @param racunArtikal
	 */
	boolean delete(RacunHasArtikalDTO racunArtikal);

	List<RacunHasArtikalDTO> selectAll();

	/**
	 * 
	 * @param racunArtikal
	 */
	List<RacunHasArtikalDTO> selectBy(RacunHasArtikalDTO racunArtikal);

}