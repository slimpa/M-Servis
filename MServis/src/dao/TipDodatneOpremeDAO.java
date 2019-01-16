package DAO;

import DTO.TipDodatneOpremeDTO;

import java.util.List;

public interface TipDodatneOpremeDAO {

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	boolean insert(TipDodatneOpremeDTO tipDodatneOpreme);

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	boolean update(TipDodatneOpremeDTO tipDodatneOpreme);

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	boolean delete(TipDodatneOpremeDTO tipDodatneOpreme);

	List<TipDodatneOpremeDTO> selectAll();

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	List<TipDodatneOpremeDTO> selectBy(TipDodatneOpremeDTO tipDodatneOpreme);

}