package dao;

import java.util.List;

import dto.TipDodatneOpremeDTO;

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