package dao;

import dto.ModelTelefonaDTO;
import java.util.List;

public interface ModelTelefonaDAO {

	boolean insert(ModelTelefonaDTO modelTelefona);
	boolean update(ModelTelefonaDTO modelTelefona);
	boolean delete(ModelTelefonaDTO modelTelefona);
	List<ModelTelefonaDTO> selectAll();
	List<ModelTelefonaDTO> selectBy(ModelTelefonaDTO modelTelefona);

}