package dao;

import dto.RezervniDioDTO;
import java.util.List;

public interface RezervniDioDAO {

	boolean update(RezervniDioDTO rezervniDio);
	boolean delete(RezervniDioDTO rezervniDio);
	List<RezervniDioDTO> selectAll();
	List<RezervniDioDTO> selectBy(RezervniDioDTO rezervniDio);
	boolean insert(RezervniDioDTO rezervniDio);

}