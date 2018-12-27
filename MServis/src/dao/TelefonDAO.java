package dao;

import java.util.List;

import dto.TelefonDTO;

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