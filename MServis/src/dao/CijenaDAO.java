package dao;

import dto.CijenaDTO;
import java.util.List;

public interface CijenaDAO {

	boolean insert(CijenaDTO cijena);
	boolean update(CijenaDTO cijena);
	boolean delete(CijenaDTO cijena);
	List<CijenaDTO> selectAll();
	List<CijenaDTO> selectBy(CijenaDTO cijena);

}