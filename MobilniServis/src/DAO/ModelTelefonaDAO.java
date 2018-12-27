package DAO;

import DTO.ModelTelefonaDTO;

import java.util.List;

public interface ModelTelefonaDAO {

	/**
	 * 
	 * @param modelTelefona
	 */
	boolean insert(ModelTelefonaDTO modelTelefona);

	/**
	 * 
	 * @param tmodelTelefona
	 */
	boolean update(ModelTelefonaDTO tmodelTelefona);

	/**
	 * 
	 * @param modelTelefona
	 */
	boolean delete(ModelTelefonaDTO modelTelefona);

	List<ModelTelefonaDTO> selectAll();

	/**
	 * 
	 * @param modelTelefona
	 */
	List<ModelTelefonaDTO> selectBy(ModelTelefonaDTO modelTelefona);

}