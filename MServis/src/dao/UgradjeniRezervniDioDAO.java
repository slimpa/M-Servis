package dao;

import dto.UgradjeniRezervniDioDTO;
import java.util.List;

public interface UgradjeniRezervniDioDAO {

	boolean insert(UgradjeniRezervniDioDTO ugradjeniRezerviDio);
	boolean update(UgradjeniRezervniDioDTO ugradjeniRezervniDio);
	boolean delete(UgradjeniRezervniDioDTO ugradjeniRezervniDio);
	List<UgradjeniRezervniDioDTO> selectAll();
	List<UgradjeniRezervniDioDTO> selectBy(UgradjeniRezervniDioDTO ugradjeniRezervniDio);

    public List<UgradjeniRezervniDioDTO> selectCijena(UgradjeniRezervniDioDTO ugradjeniRezervniDioDTO);

}