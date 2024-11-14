package zona_fit;

import zona_fit.dao.ClienteDAO;
import zona_fit.dao.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        IClienteDAO clienteDAO = new ClienteDAO();
//        List<Cliente> clientes = clienteDAO.listarClientes();
//
//        clientes.forEach(System.out::println);

//        for (Cliente cl : clientes){
//            System.out.println(cl);
//        }

//        Cliente cliente = new Cliente(3);
//        System.out.println("Cliente antes de encontrarlo: " + cliente);
//
//        IClienteDAO clienteDAO = new ClienteDAO();
//        boolean exite = clienteDAO.findClienteById(cliente);
//        if(exite){
//            System.out.println("Cliente Encontrado: " + cliente);
//        }else{
//            System.out.println("Cliente NO Encontrado");
//        }

        IClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.findByIdNumber(2);
        if (cliente != null){
            System.out.println("Cliente Encontrado: " + cliente);
        }else {
            System.out.println("Cliente NO encontrado");
        }
    }
}