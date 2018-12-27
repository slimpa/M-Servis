package DAO;

import DTO.ServisTelefonaHasIzvjestajDTO;

import java.util.List;

public interface ServisTelefonaHasIzvjestajDAO {

	/**
	 * 
	 * @param servisIzvjestaj
	 */
	boolean insert(ServisTelefonaHasIzvjestajDTO servisIzvjestaj);

	/**
	 * 
	 * @param servisIzvjestaj
	 */
	boolean update(ServisTelefonaHasIzvjestajDTO servisIzvjestaj);

	/**
	 * 
	 * @param servisIzvjestaj
	 */
	boolean delete(ServisTelefonaHasIzvjestajDTO servisIzvjestaj);

	List<ServisTelefonaHasIzvjestajDTO> selectAll();

	/**
	 * 
	 * @param servisIzvjestaj
	 */
	List<ServisTelefonaHasIzvjestajDTO> selectBy(ServisTelefonaHasIzvjestajDTO servisIzvjestaj);

}