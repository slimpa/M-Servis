package dao;

import dto.DodatnaOpremaDTO;
import java.util.List;

public interface DodatnaOpremaDAO {

	boolean insert(DodatnaOpremaDTO dodatnaOprema);
	boolean update(DodatnaOpremaDTO dodatnaOprema);
	boolean delete(DodatnaOpremaDTO dodatnaOprema);
	List<DodatnaOpremaDTO> selectAll();
	List<DodatnaOpremaDTO> selectBy(DodatnaOpremaDTO dodatnaOprema);

}