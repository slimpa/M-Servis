package dao;

import dto.DodatnaOpremaDTO;
import dto.RezervniDioDTO;
import java.util.List;

public interface DodatnaOpremaDAO {

	boolean insert(DodatnaOpremaDTO dodatnaOprema);
	boolean update(DodatnaOpremaDTO dodatnaOprema);
	boolean delete(DodatnaOpremaDTO dodatnaOprema);
	List<DodatnaOpremaDTO> selectAll();
	List<DodatnaOpremaDTO> selectBy(String naziv);
        List<DodatnaOpremaDTO> selectByTip(String naziv);
        List<DodatnaOpremaDTO> selectByModel(String naziv);
       
}