package zona_fit.dao;

import zona_fit.dominio.Cliente;

import java.util.List;

public interface IClienteDAO {

    List<Cliente> listarClientes();
    boolean findClienteById(Cliente cliente);
    boolean addCliente(Cliente cliente);
    boolean updateCliente(Cliente cliente);
    boolean deleteCliente(Cliente cliente);
    Cliente findByIdNumber(int id);
}
