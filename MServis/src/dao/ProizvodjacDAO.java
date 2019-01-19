package dao;

import dto.ProizvodjacDTO;
import java.util.List;

public interface ProizvodjacDAO {

	boolean insert(ProizvodjacDTO proizvodjac);
	boolean update(ProizvodjacDTO proizvodjac);
	boolean delete(ProizvodjacDTO proizvodjac);
	List<ProizvodjacDTO> selectAll();
	List<ProizvodjacDTO> selectBy(ProizvodjacDTO proizvodjac);
       

}