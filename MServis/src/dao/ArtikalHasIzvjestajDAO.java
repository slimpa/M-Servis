package dao;

import java.util.List;

import dto.ArtikalHasIzvjestajDTO;

public interface ArtikalHasIzvjestajDAO {

	/**
	 * 
	 * @param artikalIzvjestaj
	 */
	boolean insert(ArtikalHasIzvjestajDTO artikalIzvjestaj);

	/**
	 * 
	 * @param artikalIzvjestaj
	 */
	boolean update(ArtikalHasIzvjestajDTO artikalIzvjestaj);

	/**
	 * 
	 * @param artikalIzvjestaj
	 */
	boolean delete(ArtikalHasIzvjestajDTO artikalIzvjestaj);

	List<ArtikalHasIzvjestajDTO> selectAll();

	/**
	 * 
	 * @param artikalIzvjestaj
	 */
	List<ArtikalHasIzvjestajDTO> selectBy(ArtikalHasIzvjestajDTO artikalIzvjestaj);

}