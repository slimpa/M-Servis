package DAO;

import DTO.ServisTelefonaHasCjenovnikUslugaDTO;
import java.util.List;

public interface ServisTelefonaHasCjenovnikUslugaDAO {

	boolean insert(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik);
	boolean update(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik);
	boolean delete(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik);
	List<ServisTelefonaHasCjenovnikUslugaDTO> selectAll();
	List<ServisTelefonaHasCjenovnikUslugaDTO> selectBy(ServisTelefonaHasCjenovnikUslugaDTO servisCjenobnik);

}