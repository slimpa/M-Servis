package DAO;

import DTO.OsobaDTO;
import java.util.List;

public interface OsobaDAO {

	boolean insert(OsobaDTO osoba);
	boolean update(OsobaDTO osoba);
	boolean delete(OsobaDTO osoba);
	List<OsobaDTO> selectAll();
	List<OsobaDTO> selectBy(OsobaDTO osoba);

}