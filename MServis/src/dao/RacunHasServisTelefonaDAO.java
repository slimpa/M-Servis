package DAO;

import DTO.RacunHasServisTelefonaDTO;

import java.util.List;

public interface RacunHasServisTelefonaDAO {

	/**
	 * 
	 * @param racunServisTelefona
	 */
	boolean insert(RacunHasServisTelefonaDTO racunServisTelefona);

	/**
	 * 
	 * @param racunServisTelefona
	 */
	boolean update(RacunHasServisTelefonaDTO racunServisTelefona);

	/**
	 * 
	 * @param racunServisTelefona
	 */
	boolean delete(RacunHasServisTelefonaDTO racunServisTelefona);

	List<RacunHasServisTelefonaDTO> selectAll();

	/**
	 * 
	 * @param racunServisTelefona
	 */
	List<RacunHasServisTelefonaDTO> selectBy(RacunHasServisTelefonaDTO racunServisTelefona);

}