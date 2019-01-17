package dao;

import java.util.List;

import dto.ServisTelefonaDTO;

public interface ServisTelefonaDAO {

	/**
	 * 
	 * @param servisTelefona
	 */
	boolean insert(ServisTelefonaDTO servisTelefona);

	/**
	 * 
	 * @param servisTelefona
	 */
	boolean delete(ServisTelefonaDTO servisTelefona);

	/**
	 * 
	 * @param servisTelefona
	 */
	boolean update(ServisTelefonaDTO servisTelefona);

	List<ServisTelefonaDTO> selectAll();

	/**
	 * 
	 * @param servisTelefona
	 */
	List<ServisTelefonaDTO> selectBy(ServisTelefonaDTO servisTelefona);

}