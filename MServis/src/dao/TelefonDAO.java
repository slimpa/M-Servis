package DAO;

import DTO.TelefonDTO;

import java.util.List;

public interface TelefonDAO {

	/**
	 * 
	 * @param telefon
	 */
	boolean insert(TelefonDTO telefon);

	/**
	 * 
	 * @param telefon
	 */
	boolean update(TelefonDTO telefon);

	/**
	 * 
	 * @param telefon
	 */
	boolean delete(TelefonDTO telefon);

	List<TelefonDTO> selectAll();

	/**
	 * 
	 * @param telefon
	 */
	List<TelefonDTO> selectBy(TelefonDTO telefon);

}