package dao;

import java.util.List;

import dto.ZaposleniDTO;

public interface ZaposleniDAO {

	/**
	 * 
	 * @param zaposleni
	 */
	boolean insert(ZaposleniDTO zaposleni);

	/**
	 * 
	 * @param zaposleni
	 */
	boolean update(ZaposleniDTO zaposleni);

	/**
	 * 
	 * @param zaposleni
	 */
	boolean delete(ZaposleniDTO zaposleni);

	List<ZaposleniDTO> selectAll();

	/**
	 * 
	 * @param zaposleni
	 */
	List<ZaposleniDTO> selectBy(ZaposleniDTO zaposleni);

}