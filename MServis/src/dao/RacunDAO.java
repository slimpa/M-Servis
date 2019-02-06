package dao;

import dto.RacunDTO;
import java.util.List;

public interface RacunDAO {

	boolean insert(RacunDTO racun);
	boolean update(RacunDTO racun);
	boolean delete(RacunDTO racun);
	List<RacunDTO> selectAll();
	List<RacunDTO> selectBy(RacunDTO racun);
        int getId();

}