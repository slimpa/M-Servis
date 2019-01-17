package dao;

import dto.RacunHasServisTelefonaDTO;
import java.util.List;

public interface RacunHasServisTelefonaDAO {

	boolean insert(RacunHasServisTelefonaDTO racunServisTelefona);
	boolean update(RacunHasServisTelefonaDTO racunServisTelefona);
	boolean delete(RacunHasServisTelefonaDTO racunServisTelefona);
	List<RacunHasServisTelefonaDTO> selectAll();
	List<RacunHasServisTelefonaDTO> selectBy(RacunHasServisTelefonaDTO racunServisTelefona);

}