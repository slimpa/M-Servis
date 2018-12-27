package DAO;

import DTO.NarudzbaHasArtikalDTO;

import java.util.List;

public interface NarudzbaHasArtikalDAO {

	/**
	 * 
	 * @param narudzbaArtikal
	 */
	boolean insert(NarudzbaHasArtikalDTO narudzbaArtikal);

	/**
	 * 
	 * @param narudzbaArtikal
	 */
	boolean update(NarudzbaHasArtikalDTO narudzbaArtikal);

	/**
	 * 
	 * @param narudzbaArtikal
	 */
	boolean delete(NarudzbaHasArtikalDTO narudzbaArtikal);

	List<NarudzbaHasArtikalDTO> selectAll();

	/**
	 * 
	 * @param narudzbaArtikal
	 */
	List<NarudzbaHasArtikalDTO> selectBy(NarudzbaHasArtikalDTO narudzbaArtikal);

}