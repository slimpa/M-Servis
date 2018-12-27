package dao;

import java.util.List;

import dto.ServisTelefonaHasIzvjestajDTO;

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