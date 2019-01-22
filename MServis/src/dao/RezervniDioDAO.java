package dao;

import dto.RezervniDioDTO;
import java.util.List;

public interface RezervniDioDAO {

	boolean update(RezervniDioDTO rezervniDio);
	boolean delete(RezervniDioDTO rezervniDio);
	List<RezervniDioDTO> selectAll();
	List<RezervniDioDTO> selectBy(String naziv);
	boolean insert(RezervniDioDTO rezervniDio);
        List<RezervniDioDTO> selectAllDetailed();
}