package DAO;

import DTO.DobavljacDTO;

import java.util.List;

public interface DobavljacDAO {

	/**
	 * 
	 * @param dobavljac
	 */
	boolean insert(DobavljacDTO dobavljac);

	/**
	 * 
	 * @param dobavljac
	 */
	boolean update(DobavljacDTO dobavljac);

	/**
	 * 
	 * @param dobavljac
	 */
	boolean delete(DobavljacDTO dobavljac);

	List<DobavljacDTO> selectAll();

	/**
	 * 
	 * @param dobavljac
	 */
	List<DobavljacDTO> selectBy(DobavljacDTO dobavljac);

}