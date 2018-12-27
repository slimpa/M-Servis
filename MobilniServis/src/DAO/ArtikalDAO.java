package DAO;

import DTO.ArtikalDTO;

import java.util.List;

public interface ArtikalDAO {

	/**
	 * 
	 * @param artikal
	 */
	boolean insert(ArtikalDTO artikal);

	/**
	 * 
	 * @param artikal
	 */
	boolean update(ArtikalDAO artikal);

	/**
	 * 
	 * @param artikal
	 */
	boolean delete(ArtikalDTO artikal);

	List<ArtikalDTO> selectAll();

	/**
	 * 
	 * @param artikal
	 */
	List<ArtikalDTO> selectBy(ArtikalDTO artikal);

}