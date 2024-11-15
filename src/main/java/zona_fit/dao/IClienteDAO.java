package zona_fit.dao;

import zona_fit.dominio.Cliente;

import java.util.List;

public interface IClienteDAO {

    List<Cliente> findAll();
    boolean findById(Cliente cliente);
    boolean add(Cliente cliente);
    boolean update(Cliente cliente);
    boolean delete(Cliente cliente);
}
