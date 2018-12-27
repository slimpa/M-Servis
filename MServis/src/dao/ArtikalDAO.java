package dao;

import java.util.List;

import dto.ArtikalDTO;

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
	boolean update(ArtikalDTO artikal);

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