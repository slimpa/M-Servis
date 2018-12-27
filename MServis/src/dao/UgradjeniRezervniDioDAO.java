package dao;

import java.util.List;

import dto.UgradjeniRezervniDioDTO;

public interface UgradjeniRezervniDioDAO {

	/**
	 * 
	 * @param ugradjeniRezervniDio
	 */
	boolean insert(UgradjeniRezervniDioDTO ugradjeniRezervniDio);

	/**
	 * 
	 * @param ugradjeniRezervniDio
	 */
	boolean update(UgradjeniRezervniDioDTO ugradjeniRezervniDio);

	/**
	 * 
	 * @param ugradjeniRezervniDio
	 */
	boolean delete(UgradjeniRezervniDioDTO ugradjeniRezervniDio);

	List<UgradjeniRezervniDioDTO> selectAll();

	/**
	 * 
	 * @param ugradjeniRezervniDio
	 */
	List<UgradjeniRezervniDioDTO> selectBy(UgradjeniRezervniDioDTO ugradjeniRezervniDio);

}