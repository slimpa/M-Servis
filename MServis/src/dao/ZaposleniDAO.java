package dao;

import dto.ZaposleniDTO;
import java.util.List;

public interface ZaposleniDAO {

	boolean insert(ZaposleniDTO zaposleni);
	boolean update(ZaposleniDTO zaposleni);
	boolean delete(ZaposleniDTO zaposleni);
	List<ZaposleniDTO> selectAll();
	List<ZaposleniDTO> selectBy(ZaposleniDTO zaposleni);

}