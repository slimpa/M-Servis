package DAO;

import DTO.IzvjestajDTO;

import java.util.List;

public interface IzvjestajDAO {

	/**
	 * 
	 * @param izvjestaj
	 */
	boolean insert(IzvjestajDTO izvjestaj);

	/**
	 * 
	 * @param izvjestaj
	 */
	boolean update(IzvjestajDTO izvjestaj);

	/**
	 * 
	 * @param izvjestaj
	 */
	boolean delete(IzvjestajDTO izvjestaj);

	List<IzvjestajDTO> selectAll();

	/**
	 * 
	 * @param izvjestaj
	 */
	List<IzvjestajDTO> selectBy(IzvjestajDTO izvjestaj);

}