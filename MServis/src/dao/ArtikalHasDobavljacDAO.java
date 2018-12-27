package dao;

import java.util.List;

import dto.ArtikalHasDobavljacDTO;

public interface ArtikalHasDobavljacDAO {

	/**
	 * 
	 * @param artikalDobavljac
	 */
	boolean insert(ArtikalHasDobavljacDTO artikalDobavljac);

	/**
	 * 
	 * @param artikalDobavljac
	 */
	boolean update(ArtikalHasDobavljacDTO artikalDobavljac);

	/**
	 * 
	 * @param artikalDobavljac
	 */
	boolean delete(ArtikalHasDobavljacDTO artikalDobavljac);

	List<ArtikalHasDobavljacDTO> selectAll();

	/**
	 * 
	 * @param artikalDobavljac
	 */
	List<ArtikalHasDobavljacDTO> selectBy(ArtikalHasDobavljacDTO artikalDobavljac);

}