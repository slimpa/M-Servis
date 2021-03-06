package dao;

import dto.ServisTelefonaDTO;
import dto.StanjeTelefonaDTO;
import java.util.List;

public interface ServisTelefonaDAO {

    boolean insert(ServisTelefonaDTO servisTelefona);

    boolean delete(ServisTelefonaDTO servisTelefona);

    boolean update(ServisTelefonaDTO servisTelefona);

    List<ServisTelefonaDTO> selectBy(ServisTelefonaDTO servisTelefona);

    public List<ServisTelefonaDTO> selectAll();

    public boolean updateStanje(ServisTelefonaDTO servisTelefonaDTO);

    public List<ServisTelefonaDTO> selectById(ServisTelefonaDTO servisTelefonaDTO);

    public List<ServisTelefonaDTO> selectByStanje(ServisTelefonaDTO servisTelefonaDTO);

}