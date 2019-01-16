package DAO;

import DTO.DodatnaOpremaDTO;

import java.util.List;

public interface DodatnaOpremaDAO {

	/**
	 * 
	 * @param dodatnaOprema
	 */
	boolean insert(DodatnaOpremaDTO dodatnaOprema);

	/**
	 * 
	 * @param dodatnaOprema
	 */
	boolean update(DodatnaOpremaDTO dodatnaOprema);

	/**
	 * 
	 * @param dodatnaOprema
	 */
	boolean delete(DodatnaOpremaDTO dodatnaOprema);

	List<DodatnaOpremaDTO> selectAll();

	/**
	 * 
	 * @param dodatnaOprema
	 */
	List<DodatnaOpremaDTO> selectBy(DodatnaOpremaDTO dodatnaOprema);

}