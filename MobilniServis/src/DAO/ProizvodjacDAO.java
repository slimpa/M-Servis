package DAO;

import DTO.ProizvodjacDTO;

import java.util.List;

public interface ProizvodjacDAO {

	/**
	 * 
	 * @param proizvodjac
	 */
	boolean insert(ProizvodjacDTO proizvodjac);

	/**
	 * 
	 * @param proizvodjac
	 */
	boolean update(ProizvodjacDTO proizvodjac);

	/**
	 * 
	 * @param proizvodjac
	 */
	boolean delete(ProizvodjacDTO proizvodjac);

	List<ProizvodjacDTO> selectAll();

	/**
	 * 
	 * @param proizvodjac
	 */
	List<ProizvodjacDTO> selectBy(ProizvodjacDTO proizvodjac);

}