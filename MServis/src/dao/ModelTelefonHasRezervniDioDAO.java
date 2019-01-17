package dao;

import java.util.List;

import dto.ModelTelefonaHasRezervniDioDTO;

public interface ModelTelefonHasRezervniDioDAO {

	/**
	 * 
	 * @param modelTelefonaRezervniDio
	 */
	boolean insert(ModelTelefonaHasRezervniDioDTO modelTelefonaRezervniDio);

	/**
	 * 
	 * @param modelTelefonaRezervniDio
	 */
	boolean update(ModelTelefonaHasRezervniDioDTO modelTelefonaRezervniDio);

	/**
	 * 
	 * @param modelTelefonaRezervniDio
	 */
	boolean delete(ModelTelefonaHasRezervniDioDTO modelTelefonaRezervniDio);

	List<ModelTelefonaHasRezervniDioDTO> selectAll();

	/**
	 * 
	 * @param modelTelefona
	 */
	List<ModelTelefonaHasRezervniDioDTO> selectBy(ModelTelefonaHasRezervniDioDTO modelTelefona);

}