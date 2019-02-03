package dao;

import dto.StanjeTelefonaDTO;
import java.util.List;

public interface StanjeTelefonaDAO {

    boolean insert(StanjeTelefonaDTO stanjeTelefona);

    boolean update(StanjeTelefonaDTO stanjeTelefona);

    boolean delete(StanjeTelefonaDTO stanjeTelefona);

    List<StanjeTelefonaDTO> selectAll();

    List<StanjeTelefonaDTO> selectBy(StanjeTelefonaDTO stanjeTelefona);

    public StanjeTelefonaDTO selectById(StanjeTelefonaDTO stanjeTelefonaDTO);

}
