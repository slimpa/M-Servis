package dao;

import dto.TipDodatneOpremeDTO;
import java.util.List;

public interface TipDodatneOpremeDAO {

	boolean insert(TipDodatneOpremeDTO tipDodatneOpreme);
	boolean update(TipDodatneOpremeDTO tipDodatneOpreme);
	boolean delete(TipDodatneOpremeDTO tipDodatneOpreme);
	List<TipDodatneOpremeDTO> selectAll();
	List<TipDodatneOpremeDTO> selectBy(TipDodatneOpremeDTO tipDodatneOpreme);

}