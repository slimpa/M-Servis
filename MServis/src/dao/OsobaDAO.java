package dao;

import java.util.List;

import dto.OsobaDTO;

public interface OsobaDAO {

	/**
	 * 
	 * @param osoba
	 */
	boolean insert(OsobaDTO osoba);

	/**
	 * 
	 * @param osoba
	 */
	boolean update(OsobaDTO osoba);

	/**
	 * 
	 * @param osoba
	 */
	boolean delete(OsobaDTO osoba);

	List<OsobaDTO> selectAll();

	/**
	 * 
	 * @param osoba
	 */
	List<OsobaDTO> selectBy(OsobaDTO osoba);

}