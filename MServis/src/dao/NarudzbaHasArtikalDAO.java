package dao;

import java.util.List;

import dto.NarudzbaHasArtikalDTO;

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