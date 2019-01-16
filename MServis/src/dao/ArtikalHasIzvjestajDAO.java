package DAO;

import DTO.ArtikalHasIzvjestajDTO;

import java.util.List;

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