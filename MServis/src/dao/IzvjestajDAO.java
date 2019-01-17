package dao;

import java.util.List;

import dto.IzvjestajDTO;

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